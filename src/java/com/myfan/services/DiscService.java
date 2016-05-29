/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

import com.myfan.dto.Cancion;
import com.myfan.dto.Discografia;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author Steven
 */
@Path("disco")
public class DiscService {
    
    /**
     * Retorna lista de discos de un artista
     * @param idBanda
     * @return 
     */
    @GET
    @Path("/banda/{idBanda}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs(/*@HeaderParam("x-access-token") String token*/@PathParam("idBanda")int idBanda){
        return Response.ok().build();
    }
    
    /**
     * Retorne informacion y canciones de disco
     * @param idDisco
     * @return Response
     */
    @GET
    @Path("/{idDisco}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscsInfo(/*@HeaderParam("x-access-token") String token*/@PathParam("idDisco")int idDisco){
        return Response.ok().build();
    }
    
    /**
     * Crea un nuevo disco
     * @param discografia
     * @return 
     */
    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewDisc(Discografia discografia){
        return Response.ok().build();
    }
    
    /**
     * Actuliza un disco
     * @param discografia
     * @param idDisco
     * @return 
     */
    @PUT
    @Path("edit/{idDisco}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editDisc(Discografia discografia, @PathParam("idDisco")int idDisco){
        return Response.ok().build();
    }
    
    /**
     * Borra un disco
     * @param idDisco
     * @return 
     */
    @DELETE
    @Path("delete/{idDisco}")
    public Response deleteDisc(@PathParam("idDisco")int idDisco){
        return Response.ok().build();
    }
    
    /**
     * Crea una cancion
     * @param cancion
     * @return 
     */
    @POST
    @Path("/newSong")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSong(Cancion cancion){
        return Response.ok().build();
    }
    
    /**
     * Actualiza una cancion
     * @param cancion
     * @param idCancion
     * @return 
     */
    @PUT
    @Path("editSong/{idCancion}")
    public Response editSong(Cancion cancion, @PathParam("idCancion")int idCancion){
        return Response.ok().build();
    }
    
    @DELETE
    @Path("deleteSong/{idCancion}")
    public Response deleteSong(@PathParam("idCancion")int idCancion){
        return Response.ok().build();
    }
    
    /*
    Calificar
    obtener calificacion
    */
    
}
