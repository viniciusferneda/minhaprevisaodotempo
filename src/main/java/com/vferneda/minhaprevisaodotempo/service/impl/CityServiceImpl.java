package com.vferneda.minhaprevisaodotempo.service.impl;

import com.vferneda.minhaprevisaodotempo.exceptions.ValidationException;
import com.vferneda.minhaprevisaodotempo.model.entity.City;
import com.vferneda.minhaprevisaodotempo.repository.CityRepository;
import com.vferneda.minhaprevisaodotempo.service.CityService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return repository.save(city);
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

        if (city.getLon() == null) {
            throw new ValidationException("Informe uma Longitude.");
        }

        if (city.getLat() == null) {
            throw new ValidationException("Informe uma Latitude.");
        }
    }

    @Override
    public Optional<City> getById(Long id) {
        return repository.findById(id);
    }

}
