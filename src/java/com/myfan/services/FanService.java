/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

import com.google.gson.Gson;
import com.myfan.dto.Fan;
import com.myfan.dto.Resena;
import com.myfan.model.ProjectManager;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
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
 * @author Steven
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

    @POST
    @Path("seguir/{idFan}/{idBanda}")
    public Response seguirBanda(@PathParam("idFan")int idFan, @PathParam("idBanda")int idBanda){
        try {
            ProjectManager pm = new ProjectManager();
            pm.seguirBanda(idFan, idBanda);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
    }
    
    @POST
    @Path("dejarSeguir/{idFan}/{idBanda}")
    public Response dejarSeguirBanda(@PathParam("idFan")int idFan, @PathParam("idBanda")int idBanda){
        try {
            ProjectManager pm = new ProjectManager();
            pm.dejarSeguirBanda(idFan, idBanda);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
    }
    
    @PUT
    @Path("desactivar/{idFan}")
    public Response desactivarFan(@PathParam("idFan")int idFan){
        try {
            ProjectManager pm = new ProjectManager();
            pm.desactivarFan(idFan);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
    }
    
    @PUT
    @Path("actualizarFan/{idFan}")
    public Response actualizarFan(Fan fan, @PathParam("idFan")int idFan){
        try {
            ProjectManager pm = new ProjectManager();
            pm.actualizarFan(fan,idFan);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
    }
 
    @GET
    @Path("verArtistas/{idFan}")
    public Response verMisArtistas(@PathParam("idFan")int idFan){
        try {
            ProjectManager pm = new ProjectManager();
            pm.verMisArtistas(idFan);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
    }
    
    //public Response buscarArtistas(){}
    
    @POST
    @Path("rateBand")
    public Response rateBand(Resena resenaBanda){
        ProjectManager manager = new ProjectManager();
        manager.rateBand(resenaBanda);
        return Response.ok().build();
    }
    
    @GET
    @Path("me/{idFan}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFanInfo(@PathParam("idFan")int idFan){
        try {
            ProjectManager manager = new ProjectManager();
            Gson g = new Gson();
            return Response.ok(g.toJson(manager.getFanInfo(idFan))).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.serverError().build();
        }
    }

}
