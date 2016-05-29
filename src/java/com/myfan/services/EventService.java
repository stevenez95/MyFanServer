/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

import com.myfan.dto.Evento;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
        return Response.ok().build();
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
        return Response.ok().build();
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
        return Response.ok().build();
    }
    
    /**
     * Cancela un evento
     * @param idEvent
     * @return 
     */
    @DELETE
    @Path("cancel/{idEvent}")
    public Response cancelEvent(@PathParam("idEvent")int idEvent){
        return Response.ok().build();
    }
    
    /*
    Calificar
    Obtener calificacion
    Obtener comentarios    
    */
    
}
