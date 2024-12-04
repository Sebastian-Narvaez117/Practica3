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
import com.example.controller.dao.services.Generadorservices;



import java.util.HashMap;

@Path("generator")
public class GeneradorApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        HashMap<String, Object> map = new HashMap<>();
        Generadorservices ps = new Generadorservices();
        map.put("msg", "OK");
        System.out.println("List size: " + ps.listAll().getSize()); 

        try {
            map.put("data", ps.listShowAll());
            if (ps.listAll().isEmpty()) {
                map.put("data", new Object[] {});
                return Response.status(Status.BAD_REQUEST).entity(map).build();
            }

        } catch (Exception e) {

        }
        return Response.ok(map).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGeneradors(@PathParam("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        Generadorservices ps = new Generadorservices();
        try {

            ps.setGenerador(ps.get(id));
        } catch (Exception e) {

        }
        map.put("msg", "OK");
        map.put("data", ps.getGenerador());

        if (ps.getGenerador().getId() == null) {
            map.put("data", "Generador no encontrada o no existe");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }

        return Response.ok(map).build();
    }

    @Path("/listCombustible")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType() {
        HashMap<String, Object> map = new HashMap<>();
        Generadorservices ps = new Generadorservices();
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
        
        

        try {

            if (map.get("family") != null) {
                Familiaservices familiaservices = new Familiaservices();
                familiaservices.setFamilia(familiaservices.get(Integer.parseInt(map.get("family").toString())));
                if (familiaservices.getFamilia().getId() != null) {
                    Generadorservices ps = new Generadorservices();
                    ps.getGenerador().setUso(map.get("uso").toString());
                    ps.getGenerador().setCombustible(ps.getCombustibles(map.get("combustible").toString()));
                    ps.getGenerador().setConsumo((Float.parseFloat(map.get("consumo").toString())));
                    ps.getGenerador().setCosto((Float.parseFloat(map.get("costo").toString())));
                    ps.getGenerador().setEnergiagenerada_KW((Float.parseFloat(map.get("energia").toString())));
                    ps.getGenerador().setId_familia(familiaservices.getFamilia().getId());
                    ps.save();
                    res.put("msg", "OK");
                    res.put("data", "Generador  guardada correctamente");
                    return Response.ok(res).build();
                } else {
                    res.put("msg", "ERROR");
                    res.put("data", "La familia no existe");
                    return Response.status(Status.BAD_REQUEST).entity(res).build();
                }

            } else {
                res.put("msg", "ERROR");
                res.put("data", "No se puede guardar el generador");
                return Response.status(Status.BAD_REQUEST).entity(res).build();

            }

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
            Generadorservices ps = new Generadorservices();
            ps.getGenerador().setId(Integer.parseInt(map.get("id").toString()));
            if(ps.getGenerador().getId() == null){
                res.put("msg", "ERROR");
                res.put("data", "El generador no existe");
                return Response.status(Status.BAD_REQUEST).entity(res).build();
            } else {
                if(map.get("censo") != null){
                    Familiaservices fs = new Familiaservices();
                    fs.setFamilia(fs.get(Integer.parseInt(map.get("censo").toString())));
                    if(fs.getFamilia().getId() != null){
                        ps.getGenerador().setUso(map.get("uso").toString());
                        ps.getGenerador().setCombustible(ps.getCombustibles(map.get("combustible").toString()));
                        ps.getGenerador().setConsumo((Float.parseFloat(map.get("consumo").toString())));
                        ps.getGenerador().setCosto((Float.parseFloat(map.get("costo").toString())));
                        ps.getGenerador().setEnergiagenerada_KW((Float.parseFloat(map.get("energia").toString())));
            
                        ps.update();
                        res.put("msg", "OK");
                        res.put("data", "Generador  guardado correctamente");
                        return Response.ok(res).build();
                    }else{
                        res.put("msg", "ERROR");
                        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();

                    }
                } else {
                    res.put("msg", "ERROR");
                    res.put("data", "No se puede guardar el generador");
                    return Response.status(Status.BAD_REQUEST).entity(res).build();
                }

            }
           

        } catch (Exception e) {
            System.out.println("ERROR: " + e.toString());
            res.put("msg", "ERROR");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }

    }

}
