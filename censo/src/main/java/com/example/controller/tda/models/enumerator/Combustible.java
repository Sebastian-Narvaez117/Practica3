package com.example.controller.tda.models.enumerator;


public enum Combustible {
    GASOLINA("Gasolina"),DISEL("Disel");

    private String combustible;

    Combustible(String combustible){
        this.combustible = combustible;
    }

    public String getCombustible(){
        return combustible;
    }
    
}

