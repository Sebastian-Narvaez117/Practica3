package com.example.controller.dao.implement;

import java.io.FileWriter;
import java.lang.reflect.Method;

import com.example.controller.tda.list.LinkedList;
import java.util.Scanner;
import java.io.FileReader;
import com.google.gson.Gson;


public class AdapterDao<T> implements InterfazDao<T> {
    
    private Class<T> clazz;
    private Gson g;
    public static String URL = "media/";

    public AdapterDao(Class<T> clazz){
        this.clazz = clazz;
        this.g = new Gson();
    }

    public T get(Integer id) throws Exception {
        LinkedList<T> list = listAll();
        if (!list.isEmpty()) {
            T[] matriz = list.toArray();
            for (int i = 0; i < matriz.length; i++) {
                if (getIdent(matriz[i]).intValue() == id.intValue()) {
                    return matriz[i];
                }
            }
            // Method method = ;

        }
        return null;
    }

    public Integer getIdent(T obj) {
        try {
            Method method = null;
            for (Method m : clazz.getMethods()) {
                if (m.getName().equalsIgnoreCase("getId")) {
                    method = m;
                    break;
                }
            }
            if (method == null) {
                for (Method m : clazz.getSuperclass().getMethods()) {
                    if (m.getName().equalsIgnoreCase("getId")) {
                        method = m;
                        break;
                    }
                }

            }
            if (method != null)
                return (Integer) method.invoke(obj);
        } catch (Exception e) {
            return -1;
        }
        return -1;

    }


    public LinkedList<T> listAll() {
        LinkedList<T> list = new LinkedList<>();
        try {
            String data = readFile();
            if (data == null || data.trim().isEmpty()) {
                System.out.println("Archivo vacío o no encontrado.");
                return list;
            }
            @SuppressWarnings("unchecked")
            T[] matriz = (T[]) g.fromJson(data, java.lang.reflect.Array.newInstance(clazz, 0).getClass());
            for (T item : matriz) {
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public void merge(T object, Integer index) throws Exception {
        LinkedList<T> list = listAll();
        list.update(object, index);
        String info = g.toJson(list.toArray());
        saveFile(info);
    }


    public void persist(T object) throws Exception {
        LinkedList<T> list = listAll();
        list.add(object);
        String info = g.toJson(list.toArray());
        saveFile(info);

    }

    private String readFile() throws Exception {
        Scanner in = new Scanner(new FileReader(URL + clazz.getSimpleName() + ".json"));
        StringBuilder sb = new StringBuilder();
        while (in.hasNext()){
            sb.append(in.next());
        }
        in.close();
        return sb.toString();
    }

    private void saveFile(String data) throws Exception{
        FileWriter f = new FileWriter(URL + clazz.getSimpleName() + ".json");
        f.write(data);
        f.flush();
        f.close();
    }

    
    

}