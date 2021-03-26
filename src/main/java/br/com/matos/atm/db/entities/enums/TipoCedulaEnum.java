package br.com.matos.atm.db.entities.enums;

public enum TipoCedulaEnum {

    CEDULA100(100),
    CEDULA50(50),
    CEDULA20(20),
    CEDULA10(10);

    private Integer valorCedula;

    TipoCedulaEnum(Integer valorCedula) {
        this.valorCedula = valorCedula;
    }

    public Integer getValorCedula() {
        return valorCedula;
    }
}
