package com.vferneda.minhaprevisaodotempo.model.dto.openweather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherListDTO {

    private Integer dt;
    private String dt_txt;
    private OpenWeatherListMainDTO main;
    private List<OpenWeatherListWeatherDTO> weather;
    private OpenWeatherListCloudsDTO clouds;
    private OpenWeatherListWindDTO wind;
    private OpenWeatherListVolumeSnowRainDTO rain;
    private OpenWeatherListVolumeSnowRainDTO snow;
    private OpenWeatherListSysDTO sys;

}
