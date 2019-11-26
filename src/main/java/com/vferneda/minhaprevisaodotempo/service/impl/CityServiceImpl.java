package com.vferneda.minhaprevisaodotempo.service.impl;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.vferneda.minhaprevisaodotempo.api.dto.PrevisaoDoTempoDTO;
import com.vferneda.minhaprevisaodotempo.exceptions.ValidationException;
import com.vferneda.minhaprevisaodotempo.model.dto.CityListDTO;
import com.vferneda.minhaprevisaodotempo.model.dto.openweather.OpenWeatherDTO;
import com.vferneda.minhaprevisaodotempo.model.dto.openweather.OpenWeatherListDTO;
import com.vferneda.minhaprevisaodotempo.model.entity.City;
import com.vferneda.minhaprevisaodotempo.repository.CityRepository;
import com.vferneda.minhaprevisaodotempo.service.CityService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository repository;

    public CityServiceImpl(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public City save(City city) {
        validation(city);
        loadCity(city);
        return repository.save(city);
    }

    private void loadCity(City city) {
        try {
            final JsonReader reader = new JsonReader(new FileReader(new File(getClass().getClassLoader().getResource("city.list.json").getFile())));
            final List<CityListDTO> lCities = Arrays.asList(new Gson().fromJson(reader, CityListDTO[].class));
            final CityListDTO cityListDTO = lCities.stream().filter(cityDto -> cityDto.getName().equals(city.getName()) && cityDto.getCountry().equals(city.getCountry())).findFirst().orElseGet(null);
            if (cityListDTO == null) {
                throw new ValidationException("Cidade não encontrada, informe uma cidade válida!");
            }
            city.setId(cityListDTO.getId());
            city.setLat(cityListDTO.getCoord().getLat());
            city.setLon(cityListDTO.getCoord().getLon());
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            throw new ValidationException("Ocorreu um erro inesperado!");
        }
    }

    @Override
    public void delete(City city) {
        Objects.requireNonNull(city.getId());
        repository.delete(city);
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> search(City cityFiltro) {
        final Example example = Example.of(cityFiltro,//
                ExampleMatcher.matching().//
                        withIgnoreCase().//
                        withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return repository.findAll(example);
    }

    @Override
    public void validation(City city) {
        if (city.getName() == null || city.getName().trim().equals("")) {
            throw new ValidationException("Informe uma Cidade válida.");
        }
        if (city.getCountry() == null || city.getCountry().trim().equals("")) {
            throw new ValidationException("Informe um País válido.");
        }
    }

    @Override
    public Optional<City> getById(Long id) {
        return repository.findById(id);
    }

    public List<PrevisaoDoTempoDTO> detalharClimaDaCidade(Long id) {
        final List<PrevisaoDoTempoDTO> lPrevisaoDoTempoDTO = new ArrayList<>();
        try {
            String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?id=" + id + "&lang=pt&units=metric&appid=b84d4dbf55f9df78bcf85fbcb94c7ea9&mode=json";
            final URL url = new URL(apiUrl);
            final URLConnection request = url.openConnection();
            request.connect();

            // Convert to a JSON object to print data
            final JsonReader reader = new JsonReader(new InputStreamReader((InputStream) request.getContent()));
            final OpenWeatherDTO openWeatherDTO = new Gson().fromJson(reader, OpenWeatherDTO.class);

            if (openWeatherDTO != null && openWeatherDTO.getList() != null && !openWeatherDTO.getList().isEmpty()) {
                for (OpenWeatherListDTO dto : openWeatherDTO.getList()) {
                    final PrevisaoDoTempoDTO previsaoDoTempoDTO = new PrevisaoDoTempoDTO();
                    previsaoDoTempoDTO.setDia(dto.getDt_txt());
                    previsaoDoTempoDTO.setTempo(dto.getWeather().get(0).getDescription());
                    previsaoDoTempoDTO.setIcon(dto.getWeather().get(0).getIcon());
                    previsaoDoTempoDTO.setTemperatura(dto.getMain().getTemp().intValue());
                    previsaoDoTempoDTO.setTemperaturaMinima(dto.getMain().getTemp_min().intValue());
                    previsaoDoTempoDTO.setTemperaturaMaxima(dto.getMain().getTemp_max().intValue());
                    previsaoDoTempoDTO.setUmidade(dto.getMain().getHumidity());
                    previsaoDoTempoDTO.setVento(dto.getWind().getSpeed().intValue());
                    previsaoDoTempoDTO.setVolumeChuva(dto.getRain() == null || dto.getRain().getLast3hours() == null ? 0 : dto.getRain().getLast3hours());
                    previsaoDoTempoDTO.setVolumeNeve(dto.getSnow() == null || dto.getSnow().getLast3hours() == null ? 0 : dto.getSnow().getLast3hours());
                    lPrevisaoDoTempoDTO.add(previsaoDoTempoDTO);
                }
            }

        } catch (IOException exc) {
            exc.printStackTrace();
            throw new ValidationException("Ocorreu um erro inesperado!");
        }
        return lPrevisaoDoTempoDTO;
    }

}
