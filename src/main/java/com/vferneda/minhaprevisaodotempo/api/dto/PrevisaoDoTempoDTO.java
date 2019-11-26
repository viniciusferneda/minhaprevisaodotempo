package com.vferneda.minhaprevisaodotempo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrevisaoDoTempoDTO {

    private String dia;
    private String tempo;
    private String icon;
    private Integer temperatura;
    private Integer temperaturaMinima;
    private Integer temperaturaMaxima;
    private Integer umidade;
    private Integer vento;
    private Double volumeChuva;
    private Double volumeNeve;

}
