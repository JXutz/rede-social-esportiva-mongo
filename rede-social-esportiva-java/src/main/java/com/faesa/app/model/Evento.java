package com.faesa.app.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Evento
{
    // Colunas da tabela
    //private Long id;
    private String uid;
    //private Long idEsporte;
    private String uidEsporte;
    private String nome;
    private String descricao;
    private Date data;
    private String local;
    private Date dtInsert;
    
    // Atributos auxiliares
    private Esporte esporte;

    public Evento(String uid, String uidEsporte, String nome, String descricao, Date data, String local, Date dtInsert) {
        this.uid = uid;
        this.uidEsporte = uidEsporte;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.local = local;
        this.dtInsert = dtInsert;
    }
    
    /*public void setId(Integer id) {
    	this.id = id.longValue();
    }*/
}
