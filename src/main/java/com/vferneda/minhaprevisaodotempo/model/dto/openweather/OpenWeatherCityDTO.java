package com.vferneda.minhaprevisaodotempo.model.dto.openweather;

import com.vferneda.minhaprevisaodotempo.model.dto.CoordDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherCityDTO {

    private Long id;
    private String name;
    private CoordDTO coord;
    private String country;
    private Integer sunrise;
    private Integer sunset;

}
