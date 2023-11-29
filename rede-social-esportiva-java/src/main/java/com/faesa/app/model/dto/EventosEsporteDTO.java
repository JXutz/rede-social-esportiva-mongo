package com.faesa.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventosEsporteDTO
{
    private String uidEsporte;
    private String nomeEsporte;
    private int qtdEventos;
}
