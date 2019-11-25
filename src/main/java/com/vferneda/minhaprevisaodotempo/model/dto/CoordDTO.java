package com.vferneda.minhaprevisaodotempo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoordDTO {

    private Double lon;
    private Double lat;

}
