package com.vferneda.minhaprevisaodotempo.repository;

import com.vferneda.minhaprevisaodotempo.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByName(String name);
    Optional<City> findByNameAndCountry(String name, String country);
}
