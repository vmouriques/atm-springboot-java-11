package br.com.matos.atm.db.entities.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoTransacaoEnum {

    DEPOSITO(1, "DEPOSITO"),
    SAQUE(2, "SAQUE");

    private Integer id;
    private String nome;

    public Integer getId(){
        return id;
    }

    @JsonValue
    public String getNome(){
        return nome;
    }

    TipoTransacaoEnum(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
