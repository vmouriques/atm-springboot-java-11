package br.com.matos.atm.db.entities.enums;

public enum TipoCedulaEnum {

    CEDULA10(10),
    CEDULA20(20),
    CEDULA50(50),
    CEDULA100(100);

    private Integer valorCedula;

    TipoCedulaEnum(Integer valorCedula) {
        this.valorCedula = valorCedula;
    }

    public Integer getValorCedula() {
        return valorCedula;
    }
}
