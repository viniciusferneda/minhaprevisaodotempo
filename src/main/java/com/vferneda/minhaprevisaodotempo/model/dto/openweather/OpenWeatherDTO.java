package com.vferneda.minhaprevisaodotempo.model.dto.openweather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherDTO {

    private Integer cod;
    private String message;
    private Integer cnt;

}
