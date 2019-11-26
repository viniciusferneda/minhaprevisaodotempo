package com.vferneda.minhaprevisaodotempo.service;

import com.vferneda.minhaprevisaodotempo.api.dto.CityDTO;
import com.vferneda.minhaprevisaodotempo.api.dto.PrevisaoDoTempoDTO;
import com.vferneda.minhaprevisaodotempo.model.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityService {

    City save(City city);

    void delete(City city);

    List<City> search(City cityFiltro);

    void validation(City city);

    Optional<City> getById(Long id);

    List<PrevisaoDoTempoDTO> detalharClimaDaCidade(Long id);

}
