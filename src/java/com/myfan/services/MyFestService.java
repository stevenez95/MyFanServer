/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.services;

import com.google.gson.Gson;
import com.myfan.dto.Evento;
import com.myfan.dto.Genero;
import com.myfan.model.Facade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author steve_000
 */
@Path("myfest")
public class MyFestService {
    
    /**
     * Crea un nuevo genero musical
     * @param genero Genero a crear
     * @return confirmacion
     */
    @POST
    @Path("new")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newGenero(Genero genero){
        System.out.println("szdc");
        System.out.println(genero.getNombre());
        try {
            Facade pm = new Facade();
            pm.crearGenero(genero);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    /**
     * Obtiene la cantidad de eventos totales de una banda
     * @param idBanda id de la banda
     * @return La cantidad de eventos
     * @throws Exception en caso de error
     */
    @GET
    @Path("getCantidadEventos/{idBanda}")
    public Response getCantidadEventos(@PathParam("idBanda")int idBanda) throws Exception{
        Facade pm = new  Facade();
        return Response.ok(pm.getCantidadEventos(idBanda)).build();
    }
    
    /**
     * Obtiene la cantidad de eventos en los ultimos 6 meses de una banda
     * @param idBanda id de la banda
     * @return Cantidad de eventos en los ultimos seis meses
     * @throws Exception en error
     */
    @GET
    @Path("getUltimosEventos/{idBanda}")
    public Response getUltimosEventos(@PathParam("idBanda")int idBanda) throws Exception{
        Facade pm = new  Facade();
        ArrayList<Evento> eventos = new ArrayList<>();
        Gson g = new Gson();
        eventos = pm.getUltimosEventos(idBanda);
        String json = g.toJson(eventos);
        return Response.ok(json).build();
    }
    
    /**
     * Obtiene la calificacion promedio de todos los discos de una banda
     * @param idBanda id de la banda
     * @return Calificacion promedio de los discos
     */
    @GET
    @Path("getAllDiscRate/{idBanda}")
    public Response getAllDiscRate(@PathParam("idBanda")int idBanda){
        try {
            Facade pm = new  Facade();
            return Response.ok(pm.getAllDiscRate(idBanda)).build();
        } catch (Exception ex) {
            Logger.getLogger(MyFestService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }
    
    
    /**
     * Obtiene la calificacion promedio de todos los conciertos de una banda
     * @param idBanda id de la banda
     * @return Calificacion promedio de los conciertos
     */
    @GET
    @Path("getConcertRate/{idBanda}")
    public Response getConcertRate(@PathParam("idBanda")int idBanda) throws Exception{
        Facade pm = new  Facade();
        return Response.ok(pm.getConcertRate(idBanda)).build();
    }
    
    @GET
    @Path("top")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopBands(){
        try {
            Facade f = new Facade();
            return Response.ok(f.getTopBands()).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
        
    }
    
}
