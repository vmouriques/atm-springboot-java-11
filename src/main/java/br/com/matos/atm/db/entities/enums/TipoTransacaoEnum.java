package br.com.matos.atm.db.entities.enums;

public enum TipoTransacaoEnum {

    DEPOSITO(1, "DEPOSITO"),
    SAQUE(2, "SAQUE");

    private Integer id;
    private String nome;

    TipoTransacaoEnum(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId(){return id;}

    public String getNome(){return nome;}

}
