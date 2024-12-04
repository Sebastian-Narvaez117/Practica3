package com.example.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.controller.dao.services.Familiaservices;
import com.example.controller.tda.list.LinkedList;
import com.example.controller.tda.models.Familia;
import com.google.gson.Gson;

import java.util.HashMap;

@Path("family")
public class FamiliaApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCenso() {
        HashMap<String, Object> map = new HashMap<>();
        Familiaservices ps = new Familiaservices();
        map.put("msg", "OK");
        map.put("data", ps.listAll().toArray());
        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[] {});
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }
        return Response.ok(map).build();
    }

    

    @Path("/list/search/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchFamily(@PathParam("texto") String texto) {
        HashMap<String, Object> map = new HashMap<>();
        Familiaservices ps = new Familiaservices();
        System.out.println("List size: " + ps.listAll().getSize()); // Imprimir tamaño de la lista

        map.put("msg", "OK");
        @SuppressWarnings("rawtypes")
        LinkedList lista = ps.buscar_apellidos(texto);
        map.put("data", lista.toArray());

        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[] {});
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }

        return Response.ok(map).build();
    }








    @Path("/list/searchbinary/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response search_binary(@PathParam("texto") String texto) {
        HashMap<String, Object> map = new HashMap<>();
        Familiaservices ps = new Familiaservices();

        System.out.println("List size: " + ps.listAll().getSize()); // Imprimir tamaño de la lista

        LinkedList<Familia> lista = ps.Busqueda_binaria(texto);

        if (lista.isEmpty()) {
            map.put("msg", "No se encontraron resultados");
            map.put("data", new Object[] {}); // Enviar un arreglo vacío como data
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }

        map.put("msg", "OK");
        map.put("data", lista.toArray()); // Convertir LinkedList a un arreglo
        return Response.ok(map).build();
    }



    @Path("/list/searchbinary/telefono/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchbinary_phone(@PathParam("texto") String texto) {
        HashMap<String, Object> map = new HashMap<>();
        Familiaservices ps = new Familiaservices();
        System.out.println("List size: " + ps.listAll().getSize()); // Imprimir tamaño de la lista

        map.put("msg", "OK");
        ps.setFamilia(ps.Busqueda_Binaria_telefono(texto));
        map.put("data", ps.getFamilia());
        if (ps.getFamilia().getTelefono() == null) {
            map.put("data", "No existe la familia con ese telefono");
            return Response.status(Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").entity(map).build();
        }
        return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();

    }




    @Path("/list/searchlineal/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response search_lineal(@PathParam("texto") String texto) {
        HashMap<String, Object> map = new HashMap<>();
        Familiaservices ps = new Familiaservices();

        System.out.println("List size: " + ps.listAll().getSize()); // Imprimir tamaño de la lista

        LinkedList<Familia> lista = ps.Busqueda_Lineal(texto);

        if (lista.isEmpty()) {
            map.put("msg", "No se encontraron resultados");
            map.put("data", new Object[] {}); // Enviar un arreglo vacío como data
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }

        map.put("msg", "OK");
        map.put("data", lista.toArray()); // Convertir LinkedList a un arreglo
        return Response.ok(map).build();
    }





    @Path("/list/searchlineal/telefono/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchlineal_phone(@PathParam("texto") String texto) {
        HashMap<String, Object> map = new HashMap<>();
        Familiaservices ps = new Familiaservices();
        System.out.println("List size: " + ps.listAll().getSize()); // Imprimir tamaño de la lista

        map.put("msg", "OK");
        ps.setFamilia(ps.Busqueda_Lineal_telefono(texto));
        map.put("data", ps.getFamilia());
        if (ps.getFamilia().getTelefono() == null) {
            map.put("data", "No existe la familia con ese telefono");
            return Response.status(Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").entity(map).build();
        }
        return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();

    }




    @Path("/list/order/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response order(@PathParam("attribute") String attribute, @PathParam("type") Integer type) {
        HashMap<String, Object> map = new HashMap<>();
        Familiaservices ps = new Familiaservices();
        map.put("msg", "OK");
        try {

            @SuppressWarnings("rawtypes")
            LinkedList lsita = ps.order_object(type, attribute);
            map.put("data", lsita.toArray());
            if (lsita.isEmpty()) {
                map.put("data", new Object[] {});
            }

        } catch (Exception e) {

            return Response.status(Status.BAD_REQUEST).entity(map).build();

        }

        return Response.ok(map).build();
    }


    @Path("/list/orderquicksort/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response quicksort(@PathParam("attribute") String attribute, @PathParam("type") Integer type) {
        HashMap<String, Object> map = new HashMap<>();
        Familiaservices ps = new Familiaservices();
        map.put("msg", "OK");
        try {

            @SuppressWarnings("rawtypes")
            LinkedList lsita = ps.orderQuicksort(type, attribute);
            map.put("data", lsita.toArray());
            if (lsita.isEmpty()) {
                map.put("data", new Object[] {});
            }

        } catch (Exception e) {
            map.put("data", "No hay como ordenar");
            return Response.status(Status.BAD_REQUEST).entity(map).build();

        }

        return Response.ok(map).build();
    }

    @Path("/list/ordermergesort/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response mergesort(@PathParam("attribute") String attribute, @PathParam("type") Integer type) {
        HashMap<String, Object> map = new HashMap<>();
        Familiaservices ps = new Familiaservices();
        map.put("msg", "OK");
        try {

            @SuppressWarnings("rawtypes")
            LinkedList lsita = ps.orderMergeSort(type, attribute);
            map.put("data", lsita.toArray());
            if (lsita.isEmpty()) {
                map.put("data", new Object[] {});
            }

        } catch (Exception e) {
            map.put("data", "No hay como ordenar");
            return Response.status(Status.BAD_REQUEST).entity(map).build();

        }

        return Response.ok(map).build();
    }

    @Path("/list/ordershellsort/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response shellsort(@PathParam("attribute") String attribute, @PathParam("type") Integer type) {
        HashMap<String, Object> map = new HashMap<>();
        Familiaservices ps = new Familiaservices();
        map.put("msg", "OK");
        try {

            @SuppressWarnings("rawtypes")
            LinkedList lsita = ps.orderMergeSort(type, attribute);
            map.put("data", lsita.toArray());
            if (lsita.isEmpty()) {
                map.put("data", new Object[] {});
            }

        } catch (Exception e) {
            map.put("data", "No hay como ordenar");
            return Response.status(Status.BAD_REQUEST).entity(map).build();

        }

        return Response.ok(map).build();
    }


    @Path("/list/search/telefono/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchPhone(@PathParam("texto") String texto) {
        HashMap<String, Object> map = new HashMap<>();
        Familiaservices ps = new Familiaservices();
        System.out.println("List size: " + ps.listAll().getSize()); // Imprimir tamaño de la lista

        map.put("msg", "OK");
        ps.setFamilia(ps.buscar_telefono(texto));
        map.put("data", ps.getFamilia());
        if (ps.getFamilia().getTelefono() == null) {
            map.put("data", "No existe la familia con ese telefono");
            return Response.status(Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").entity(map).build();
        }
        return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();

    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamilias(@PathParam("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        Familiaservices ps = new Familiaservices();
        try {

            ps.setFamilia(ps.get(id));
        } catch (Exception e) {

        }
        map.put("msg", "OK");
        map.put("data", ps.getFamilia());

        if (ps.getFamilia().getId() == null) {
            map.put("data", "Familia no encontrada o no existe");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }

        return Response.ok(map).build();
    }

    @Path("/listCombustible")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType() {
        HashMap<String, Object> map = new HashMap<>();
        Familiaservices ps = new Familiaservices();
        map.put("msg", "OK");
        map.put("data", ps.getCombustibles());
        return Response.ok(map).build();

    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap<String, Object> map) {
        // todo
        // VALIDATIONS -- bad request
        HashMap<String, Object> res = new HashMap<>();
        Gson g = new Gson();
        String a = g.toJson(map);
        System.out.println("**********" + a);

        try {
            Familiaservices ps = new Familiaservices();
            ps.getFamilia().setApellido(map.get("apellido").toString());
            ps.getFamilia().setDireccion(map.get("direccion").toString());
            ps.getFamilia().setTelefono(map.get("telefono").toString());

            ps.save();
            res.put("msg", "OK");
            res.put("data", "Familia  guardada correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("ERROR: " + e.toString());
            res.put("msg", "ERROR");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }

    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap<String, Object> map) {
        // todo
        // VALIDATIONS -- bad request
        HashMap<String, Object> res = new HashMap<>();

        try {
            Familiaservices ps = new Familiaservices();
            ps.getFamilia().setId(Integer.parseInt(map.get("id").toString()));
            ps.getFamilia().setApellido(map.get("apellido").toString());
            ps.getFamilia().setDireccion(map.get("direccion").toString());
            ps.getFamilia().setTelefono(map.get("telefono").toString());

            ps.update();
            res.put("msg", "OK");
            res.put("data", "Familia  guardada correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("ERROR: " + e.toString());
            res.put("msg", "ERROR");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }

    }

}
