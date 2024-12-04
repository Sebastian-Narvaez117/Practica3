package com.example.controller.dao;
import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.controller.tda.models.Generador;
import com.example.controller.tda.models.enumerator.Combustible;

public class GeneradorDao extends AdapterDao<Generador> {
    private Generador Generador;
    private LinkedList<Generador> listAll;


    public GeneradorDao() {
        super(Generador.class);
        
    }

    public Generador getGenerador() {
        if(Generador == null){
            Generador = new Generador();
        }
        return this.Generador;
    }

    public void setGenerador(Generador Generador) {
        this.Generador = Generador;
    }

    public LinkedList<Generador> getlistAll() {
        if(listAll == null){
            listAll = new LinkedList<>();
        }
        return listAll();
    }

    public  Boolean update() throws Exception{
        this.merge(getGenerador(), getGenerador().getId()-1);
        this.listAll = listAll();
        return true;
    }


    public Boolean save() throws Exception{
        Integer id = listAll().getSize() + 1;
        Generador.setId(id); 
        this.persist(this.Generador);
        this.listAll = listAll();
        return true;
    }


    public Combustible getCombustibles(String tipo){
        return Combustible.valueOf(tipo);
    }


    public Combustible[] getCombustibles(){
        return Combustible.values();
    }

 


}

