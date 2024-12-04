package com.example.controller.tda.models;


import com.example.controller.tda.models.enumerator.Combustible;

public class Generador {
    private Integer Id;
    private float costo;
    private float consumo;
    private float energiagenerada_KW;
    private Combustible combustible;
    private String uso;
    private Integer Id_familia;

    
    public Generador(){

    }
   
    public Combustible getCombustible() {
        return combustible;
    }
    public void setCombustible(Combustible combustible) {
        this.combustible = combustible;
    }
    public Integer getId() {
        return Id;
    }
    public void setId(Integer Id) {
        this.Id = Id;
    }
    public float getCosto() {
        return costo;
    }
    public void setCosto(float costo) {
        this.costo = costo;
    }
    public float getConsumo() {
        return consumo;
    }
    public void setConsumo(float consumo) {
        this.consumo = consumo;
    }
   
    public float getEnergiagenerada_KW() {
        return energiagenerada_KW;
    }

    public void setEnergiagenerada_KW(float energiagenerada_KW) {
        this.energiagenerada_KW = energiagenerada_KW;
    }
   

    public String getUso() {
        return uso;
    }
    public void setUso(String uso) {
        this.uso = uso;
    }

    public Integer getId_familia() {
        return Id_familia;
    }

    public void setId_familia(Integer id_familia) {
        Id_familia = id_familia;
    }
    

    
}
