package com.vferneda.minhaprevisaodotempo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityListDTO {

    private Long id;
    private String name;
    private String country;
    private CoordDTO coord;
}
