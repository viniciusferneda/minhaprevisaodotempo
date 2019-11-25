package com.vferneda.minhaprevisaodotempo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

    private Long id;
    private String name;
    private String country;
    private Double lon;
    private Double lat;

}
