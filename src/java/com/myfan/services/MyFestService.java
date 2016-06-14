/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

import com.google.gson.Gson;
import com.myfan.dto.Genero;
import com.myfan.model.ProjectManager;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCantidadEventos(@PathParam("idBanda")int idBanda) throws Exception{
        ProjectManager pm = new  ProjectManager();        
        return Response.ok(pm.getCantidadEventos(idBanda)).build();
    } 
    
      @GET
    @Path("getAllDiscRate/{idBanda}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDiscRate(@PathParam("idBanda")int idBanda) throws Exception{
        ProjectManager pm = new  ProjectManager();
        String res = "";
        Gson gson = new Gson();  
        res = gson.toJson(pm.getAllDiscRate(idBanda));
        return Response.ok(res).build();
    }
    
    
      @GET
    @Path("getConcertRate/{idBanda}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConcertRate(@PathParam("idBanda")int idBanda) throws Exception{
        ProjectManager pm = new  ProjectManager();
        String res = "";
        Gson gson = new Gson();  
        res = gson.toJson(pm.getConcertRate(idBanda));
        return Response.ok(res).build();
    }
    
    
    
    /*
    @GET
    @Path("getCalificacionPromedioDiscos/{idBanda}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCalificacionPromedioDiscos(@PathParam("idBanda")int idBanda) throws Exception{
        ProjectManager pm = new  ProjectManager();        
        return Response.ok(pm.getCalificacionPromedioDiscos(idBanda)).build();
    } 
    */
}
