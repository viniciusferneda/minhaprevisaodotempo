package com.vferneda.minhaprevisaodotempo.model.dto.openweather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherListDTO {

    private Integer dt;
    private OpenWeatherListMainDTO main;
    private OpenWeatherListWeatherDTO weather;
    private OpenWeatherListCloudsDTO clouds;
    private OpenWeatherListWindDTO wind;
    private OpenWeatherListSnowDTO snow;
    private OpenWeatherListSysDTO sys;
    private String dt_txt;

}
