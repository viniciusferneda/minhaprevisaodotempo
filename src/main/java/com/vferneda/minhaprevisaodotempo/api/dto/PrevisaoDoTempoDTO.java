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
    private String temperatura;
    private String temperaturaMinima;
    private String temperaturaMaxima;
    private String umidade;
    private String vento;

}
