package com.faesa.app.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Esporte
{
    //private Long id;
    private String uid;
    private String nome;
    private Date dtInsert;
}
