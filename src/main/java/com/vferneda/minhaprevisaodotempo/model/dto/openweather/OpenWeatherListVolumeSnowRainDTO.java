package com.vferneda.minhaprevisaodotempo.model.dto.openweather;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class OpenWeatherListVolumeSnowRainDTO {

    private Double last3hours;

    @JsonGetter("3h")
    public Double getLast3hours() {
        return last3hours;
    }

    @JsonSetter("3h")
    public void setLast3hours(Double last3hours) {
        this.last3hours = last3hours;
    }
}
