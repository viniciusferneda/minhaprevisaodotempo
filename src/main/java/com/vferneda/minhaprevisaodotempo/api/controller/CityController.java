package com.vferneda.minhaprevisaodotempo.api.controller;

import com.vferneda.minhaprevisaodotempo.api.dto.CityDTO;
import com.vferneda.minhaprevisaodotempo.exceptions.ValidationException;
import com.vferneda.minhaprevisaodotempo.model.entity.City;
import com.vferneda.minhaprevisaodotempo.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService service;

    @GetMapping
    public ResponseEntity search(@RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "country", required = false) String country,
                                 @RequestParam(value = "lon", required = false) Double lon,
                                 @RequestParam(value = "lat", required = false) Double lat,
                                 @RequestParam("id") Long id) {
        final City cityFilter = new City();
        cityFilter.setId(id);
        cityFilter.setName(name);
        cityFilter.setCountry(country);
        cityFilter.setLon(lon);
        cityFilter.setLat(lat);
        List<City> lCities = service.search(cityFilter);
        return ResponseEntity.ok(lCities);
    }

    @GetMapping("{id}")
    public ResponseEntity getCityById(@PathVariable("id") Long id) {
        return service.getById(id)//
                .map(city -> new ResponseEntity(converter(city), HttpStatus.OK))//
                .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CityDTO dto) {
        try {
            final City city = converter(dto);
            final City citySalvo = service.save(city);
            return new ResponseEntity(citySalvo, HttpStatus.CREATED);
        } catch (ValidationException exc) {
            return ResponseEntity.badRequest().body(exc.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody CityDTO dto) {
        try {
            final City city = converter(dto);
            service.delete(city);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ValidationException exc) {
            return ResponseEntity.badRequest().body(exc.getMessage());
        }
    }

    private CityDTO converter(City city) {
        return CityDTO.builder()//
                .id(city.getId())//
                .name(city.getName())//
                .country(city.getCountry())//
                .lon(city.getLon())//
                .lat(city.getLat())//
                .build();
    }

    private City converter(CityDTO dto) {
        final City city = new City();

        city.setId(dto.getId());
        city.setName(dto.getName());
        city.setCountry(dto.getCountry());
        city.setLon(dto.getLon());
        city.setLat(dto.getLat());

        return city;
    }

}
