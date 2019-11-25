package com.vferneda.minhaprevisaodotempo.service.impl;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.vferneda.minhaprevisaodotempo.exceptions.ValidationException;
import com.vferneda.minhaprevisaodotempo.model.dto.CityListDTO;
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

}
