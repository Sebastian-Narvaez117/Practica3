package com.example.controller.dao.services;


import com.example.controller.dao.FamiliaDao;
import com.example.controller.tda.list.LinkedList;
import com.example.controller.tda.models.Familia;
import com.example.controller.tda.models.enumerator.Combustible;

public class Familiaservices {

    private FamiliaDao obj;

    public Familiaservices() {
        obj = new FamiliaDao();
    }

    public Boolean  save() throws Exception{
        return obj.save();
    }

    public Boolean update() throws Exception{
        return obj.update();
    }

    public LinkedList<Familia> listAll(){
        return obj.getlistAll();    
    }

    public Familia getFamilia(){
        return obj.getFamilia();
    }

    public void setFamilia(Familia familia){
        obj.setFamilia(familia);
    }

    public Combustible getCombustibles(String tipo){
        return obj.getCombustibles(tipo);
    }

    public Combustible[] getCombustibles(){
        return obj.getCombustibles();
    }

    public Familia get(Integer id) throws Exception{
        return obj.get(id);
    }

    @SuppressWarnings("rawtypes")
    public LinkedList order(Integer type_order, String atributo){ 
        return obj.order(type_order, atributo);
    }

    public LinkedList<Familia> order_object(Integer type , String atributo )throws Exception{
        return obj.listAll().order(atributo, type);
    }


    public LinkedList<Familia> buscar_apellidos(String texto ){
        return obj.buscar_apellidos(texto);

    }

    public Familia buscar_telefono(String texto ){
        return obj.buscar_telefono(texto);
    }

    public LinkedList<Familia> orderQuicksort(Integer typeOrder, String attribute) {
        return obj.orderQuicksort(typeOrder, attribute); 
    }

    public LinkedList<Familia> orderMergeSort(Integer typeOrder, String attribute) {
        return obj.orderMergesort(typeOrder, attribute); 
    }

    public LinkedList<Familia> orderShellSort(Integer typeOrder, String attribute) {
        return obj.orderShellsort(typeOrder, attribute); 
    }
   
    public LinkedList<Familia> Busqueda_binaria(String attribute) {
        return obj.buscarPorApellido_binaria(attribute);
    }
    
    public Familia Busqueda_Binaria_telefono(String attribute) {
        return obj.buscarPorTelefono_binaria(attribute);
    }

    public LinkedList<Familia> Busqueda_Lineal(String prefix) {
        return obj.buscarPorApellido_lineal(prefix);
    }
    
    public Familia Busqueda_Lineal_telefono(String telefono) {
        return obj.buscarPorTelefono_lineal(telefono);
    }
    
    
}

