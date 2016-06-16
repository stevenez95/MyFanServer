/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

import com.google.gson.Gson;
import com.myfan.dto.Evento;
import com.myfan.dto.Genero;
import com.myfan.model.ProjectManager;
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
    
    
    @POST
    @Path("new")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newGenero(Genero genero){
         try {
            ProjectManager pm = new ProjectManager();
            pm.crearGenero(genero);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception ex) {
           return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("getCantidadEventos/{idBanda}")
    public Response getCantidadEventos(@PathParam("idBanda")int idBanda) throws Exception{
        ProjectManager pm = new  ProjectManager();        
        return Response.ok(pm.getCantidadEventos(idBanda)).build();
    } 
    
    
    @GET
    @Path("getUltimosEventos/{idBanda}")
    public Response getUltimosEventos(@PathParam("idBanda")int idBanda) throws Exception{
        ProjectManager pm = new  ProjectManager(); 
        ArrayList<Evento> eventos = new ArrayList<>();
        Gson g = new Gson();
        eventos = pm.getUltimosEventos(idBanda);
        String json = g.toJson(eventos);
        return Response.ok(json).build();
    } 
    
      @GET
    @Path("getAllDiscRate/{idBanda}")
    public Response getAllDiscRate(@PathParam("idBanda")int idBanda){
        try {
            ProjectManager pm = new  ProjectManager();
            return Response.ok(pm.getAllDiscRate(idBanda)).build();
        } catch (Exception ex) {
            Logger.getLogger(MyFestService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }
    
    
      @GET
    @Path("getConcertRate/{idBanda}")
    public Response getConcertRate(@PathParam("idBanda")int idBanda) throws Exception{
        ProjectManager pm = new  ProjectManager();
        return Response.ok(pm.getConcertRate(idBanda)).build();
    }
    
}
