/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

import com.google.gson.Gson;
import com.myfan.connection.MyFestConnection;
import com.myfan.dto.Banda;
import com.myfan.model.ProjectManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Steven
 */
@Path("banda")
public class BandService {
    
    @GET
    @Path("me/{idBanda}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBandInfo(@PathParam("idBanda")int idBanda){
        
        ProjectManager manager = new ProjectManager();
        return Response.ok(manager.getBandInfo(idBanda)).build();
    }
    
    @GET
    @Path("seguidores/{idBanda}")
    public Response getCantSeguidores(@PathParam("idBanda")int idBanda){
        
        try {
            ProjectManager manager = new ProjectManager();
            return Response.ok(manager.getCantidadSeguidores(idBanda)).build();
        } catch (Exception ex) {
            Logger.getLogger(BandService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }
    
    @GET
    @Path("getBandRate/{idBanda}")
    public Response getBandRate(@PathParam("idBanda")int idBanda) throws Exception{
        ProjectManager pm = new  ProjectManager();
        return Response.ok(pm.getBandRate(idBanda)).build();
    }
    
    @DELETE
    @Path("desactivar/{idBanda}")
    public Response desactivarFan(@PathParam("idBanda")int idBanda){
        try {
            ProjectManager pm = new ProjectManager();
            pm.desactivarBanda(idBanda);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
    
    @PUT
    @Path("actualizar/{idBanda}")
    public Response actualizarBanda(Banda banda, @PathParam("idBanda")int idBanda){
        try {
            ProjectManager pm = new ProjectManager();
            pm.actualizarBanda(banda,idBanda);
            
            MyFestConnection connection = new MyFestConnection();
            connection.actualizarArtistas(banda,idBanda);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
    }
    
    @GET
    @Path("generos/{idBanda}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGenerosBanda(@PathParam("idBanda") int idBanda){
        try {
            ProjectManager manager = new ProjectManager();
            Gson g = new Gson();
            return Response.ok(g.toJson(manager.getGenerosBanda(idBanda))).build();
        } catch (Exception ex) {
           return Response.serverError().build();
        }
    }
    
    @GET
    @Path("getBandComments/{idBanda}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBandComments(@PathParam("idBanda")int idBanda) throws Exception{
        ProjectManager pm = new  ProjectManager();
        String res = "";
        Gson gson = new Gson();
        
        res = gson.toJson(pm.getBandComments(idBanda));
        return Response.ok(res).build();
    } 
    
   
}
