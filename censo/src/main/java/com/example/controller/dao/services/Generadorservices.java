package com.example.controller.dao.services;


import java.util.HashMap;

import com.example.controller.dao.GeneradorDao;
import com.example.controller.tda.list.LinkedList;
import com.example.controller.tda.models.Familia;
import com.example.controller.tda.models.Generador;
import com.example.controller.tda.models.enumerator.Combustible;

public class Generadorservices {

     @SuppressWarnings("unchecked")
    public Object [] listShowAll() throws Exception{
        if(!obj.getlistAll().isEmpty()){
            Generador [] lista = (Generador[]) obj.getlistAll().toArray();
            Object [] respuesta = new Object[lista.length];
            for(int i = 0; i < lista.length;i++){
                Familia p = new Familiaservices().get(lista[i].getId_familia());
                @SuppressWarnings("rawtypes")
                HashMap mapa = new HashMap();
                mapa.put("id", lista[i].getId());
                mapa.put("uso", lista[i].getUso());
                mapa.put("consumo", lista[i].getConsumo());
                mapa.put("combustible", lista[i].getCombustible());
                mapa.put("energia", lista[i].getEnergiagenerada_KW());
                mapa.put("costo", lista[i].getConsumo());
                mapa.put("family",p);
                respuesta[i] = mapa;

            }
            return respuesta;
            
        }
        return new Object[]{};
    }

    private GeneradorDao obj;

    public Generadorservices() {
        obj = new GeneradorDao();
    }

    public Boolean  save() throws Exception{
        return obj.save();
    }

    public Boolean update() throws Exception{
        return obj.update();
    }

    public LinkedList<Generador> listAll(){
        return obj.getlistAll();    
    }

    public Generador getGenerador(){
        return obj.getGenerador();
    }

    public void setGenerador(Generador Generador){
        obj.setGenerador(Generador);
    }

    public Combustible getCombustibles(String tipo){
        return obj.getCombustibles(tipo);
    }

    public Combustible[] getCombustibles(){
        return obj.getCombustibles();
    }

    public Generador get(Integer id) throws Exception{
        return obj.get(id);
    }

  /*  public LinkedList order(Integer type_order, String atributo){ 
        return obj.order(type_order, atributo);
    }/* */

    public LinkedList<Generador> order_object(Integer type , String atributo )throws Exception{
        return obj.listAll().order(atributo, type);
    }


    /*public LinkedList<Generador> buscar_apellidos(String texto ){
        return obj.buscar_apellidos(texto);

    }

    public Generador buscar_telefono(String texto ){
        return obj.buscar_telefono(texto);
    }*/ 


   

   

    
}

