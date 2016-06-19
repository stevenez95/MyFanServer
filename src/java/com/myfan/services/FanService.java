/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

import com.google.gson.Gson;
import com.myfan.connection.IConstantes;
import com.myfan.connection.TwitterConnect;
import com.myfan.dto.Fan;
import com.myfan.dto.Resena;
import com.myfan.model.Facade;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author ToolMakers
 */
@Path("fan")
public class FanService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WebResource
     */
    public FanService() {
    }

    /**
     * Permite que un fan siga a una banda
     * @param idFan id del fan
     * @param idBanda id de la badna
     * @return confirmacion
     */
    @POST
    @Path("seguir/{idFan}/{idBanda}")
    public Response seguirBanda(@PathParam("idFan")int idFan, @PathParam("idBanda")int idBanda){
        try {
            Facade pm = new Facade();
            pm.seguirBanda(idFan, idBanda);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
    }
    
    /**
     * Permite saber si un fan sigue a una banda
     * @param idFan id del fan
     * @param idBanda id de la banda
     * @return true si el fan sigue a la banda, false en caso contrario
     */
    @GET
    @Path("esSeguidor/{idFan}/{idBanda}")
    public Response esSeguidor(@PathParam("idFan")int idFan, @PathParam("idBanda")int idBanda){
        try {
            Facade pm = new Facade();
            pm.esSeguidor(idFan, idBanda);
            return Response.ok(pm.esSeguidor(idFan, idBanda)).build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
    }
    
    /**
     * Permite que un fan deje de  seguir a una banda
     * @param idFan id del fan
     * @param idBanda id de la badna
     * @return confirmacion
     */
    @DELETE
    @Path("dejarSeguir/{idFan}/{idBanda}")
    public Response dejarSeguirBanda(@PathParam("idFan")int idFan, @PathParam("idBanda")int idBanda){
        try {
            Facade pm = new Facade();
            pm.dejarSeguirBanda(idFan, idBanda);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
    }
    
    /**
     * Desactiva la cuenta de un fan
     * @param idFan id del fan
     * @return confirmacion
     */
    @DELETE
    @Path("desactivar/{idFan}")
    public Response desactivarFan(@PathParam("idFan")int idFan){
        try {
            Facade pm = new Facade();
            pm.desactivarFan(idFan);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
    }
    
    /**
     * Actualiza la cuenta de un fan
     * @param fan informacion del fan
     * @param idFan id del fan
     * @return confirmacion
     */
    @PUT
    @Path("actualizar/{idFan}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarFan(Fan fan, @PathParam("idFan")int idFan){
        Facade facade = new Facade();
        return facade.actualizarFan(fan, idFan);
    }
 
    /**
     * Obtiene los artistas que el fan esta siguiendo
     * @param idFan id del fan
     * @return Lista de artistas que sigue el fan
     */
    @GET
    @Path("verArtistas/{idFan}")
    public Response verMisArtistas(@PathParam("idFan")int idFan){
        try {
            Facade pm = new Facade();
            Gson g = new Gson();
            return Response.ok(g.toJson(pm.verMisArtistas(idFan))).build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
    }
    
    /**
     * Obtiene artistas basado en una serie de parametros
     * @param nombreBanda Nombre de la banda que se busca
     * @param genero Genro musical de la banada que se busca
     * @param pais Pais de la banda que se busca
     * @return Lista de bandas que cumplan con los parametros de la busqueda
     */
    @GET
    @Path("buscarArtista/{nombreBanda}/{genero}/{pais}")
    public Response buscarArtistas(@PathParam("nombreBanda")String nombreBanda, @PathParam("genero")String genero, @PathParam("pais")String pais){
        try {
            Facade pm = new Facade();
            Gson g = new Gson();
            return Response.ok(g.toJson(pm.buscarArtistas(nombreBanda, pais, genero))).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().build();
        }
    }
    
    /**
     * Permite al fan calificar y comentar una banda
     * @param resenaBanda resena creada por el fan
     * @return confirmacion
     */
    @POST
    @Path("rateBand")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response rateBand(Resena resenaBanda){
        try {
            Facade manager = new Facade();
            manager.rateBand(resenaBanda);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
    
    /**
     * Permite al fan calificar y comentar un disco
     * @param resenaDisco resena creada por el fan
     * @return confirmacion
     */
    @POST
    @Path("rateDisc")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response rateDisc(Resena resenaDisco){
        try {
            Facade manager = new Facade();
            manager.rateDisc(resenaDisco);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().entity(IConstantes.m).build();
        }
    }
    
    /**
     * Permite al fan calificar y comentar un evento
     * @param resenaEvento creada por el fan
     * @return confirmacion
     */
    @POST
    @Path("rateEvent")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response rateEvent(Resena resenaEvento){
        try {
            Facade manager = new Facade();
            manager.rateEvent(resenaEvento);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
    
    /**
     * Obtiene la informacion de un fan
     * @param idFan id del fan
     * @return Informacion del fanatico
     */
    @GET
    @Path("me/{idFan}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFanInfo(@PathParam("idFan")int idFan){
        try {
            Facade manager = new Facade();
            Gson g = new Gson();
            return Response.ok(g.toJson(manager.getFanInfo(idFan))).build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
    }

}
