package com.vferneda.minhaprevisaodotempo.model.dto.openweather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherListWeatherDTO {

    private Long id;
    private String main;
    private String description;
    private String icon;

}
