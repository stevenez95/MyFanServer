/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

import com.google.gson.Gson;
import com.myfan.dto.Evento;
import com.myfan.model.Facade;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author steve_000
 */
@Path("evento")
public class EventService {
    
    /**
     * Retorna los eventos a un fan
     * @param idFan
     * @return 
     */
    @GET
    @Path("/fan/{idFan}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFanEvents(@PathParam("idFan")int idFan){
        try {
            Facade pm = new Facade();
            Gson gson = new Gson();
            return Response.ok(gson.toJson(pm.getEventosFan(idFan))).build();
        } catch (SQLException ex) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (Exception ex) {
           return Response.status(Status.NOT_FOUND).build();
        }
    }
    
    /**
     * Retorna los eventos a una banda
     * @param idBanda
     * @return 
     */
    @GET
    @Path("/banda/{idBanda}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBandEvents(@PathParam("idBanda")int idBanda){
        try {
            Facade pm = new Facade();
            Gson gson = new Gson();
            return Response.ok(gson.toJson(pm.getEventosBanda(idBanda))).build();
        } catch (SQLException ex) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (Exception ex) {
           return Response.status(Status.NOT_FOUND).build();
        }
    }
    
    /**
     * Crea un nuevo evento
     * @param evento
     * @return 
     */
    @POST
    @Path("/newEvent")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEvent(Evento evento){
        try {
            Facade pm = new Facade();
            pm.crearEvento(evento);
            return Response.ok().build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Status.NOT_FOUND).build();
        } catch (Exception ex) {
           return Response.status(Status.NOT_FOUND).build();
        }
    }
    
    /**
     * Cancela un evento
     * @param idEvent
     * @return 
     */
    @DELETE
    @Path("{idEvent}")
    public Response cancelEvent(@PathParam("idEvent")int idEvent){
        try {
            Facade pm = new Facade();
            pm.cancelEvent(idEvent);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (Exception ex) {
           return Response.status(Status.NOT_FOUND).build();
        }
    }
    
    /**
     * Obtiene la informacion de un evento
     * @param idevent
     * @return 
     */
    @GET
    @Path("{idEvent}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEventInfo(@PathParam("idEvent") int idevent){
        try {
            Facade manager = new Facade();
            return Response.ok(manager.getEventInfo(idevent)).build();
        } catch (Exception ex) {
           return Response.serverError().build();
        }
    }
    
   @GET
    @Path("getEventRate/{idEvento}")
    public Response getEventRate(@PathParam("idEvento")int idEvento) throws Exception{
        Facade pm = new  Facade();        
        return Response.ok(pm.getEventRate(idEvento)).build();
    } 
    
    @GET
    @Path("getEventComments/{idEvento}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEventComments(@PathParam("idEvento")int idEvento) throws Exception{
        Facade pm = new  Facade();
        String res = "";
        Gson gson = new Gson();
        
        res = gson.toJson(pm.getEventComments(idEvento));
        return Response.ok(res).build();
    } 
    
    
    
}
