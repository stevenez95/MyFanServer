/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

import com.google.gson.Gson;
import com.myfan.dto.Noticia;
import com.myfan.model.ProjectManager;
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

/**
 *
 * @author steve_000
 */
@Path("noticias")
public class NewsService {
    
    /**
     * Retorna las noticias a un fan
     * @param idFan
     * @return 
     */
    @GET
    @Path("/fan/{idFan}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFanNews(@PathParam("idFan")int idFan){
        try {
            ProjectManager pm = new ProjectManager();
            Gson gson = new Gson();
            return Response.ok(gson.toJson(pm.getNoticiasFan(idFan))).build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception ex) {
           return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    /**
     * Retorna las noticias a una banda
     * @param idBanda
     * @return 
     */
    @GET
    @Path("/banda/{idBanda}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBandNews(@PathParam("idBanda")int idBanda){
         try {
            ProjectManager pm = new ProjectManager();
            Gson gson = new Gson();
            return Response.ok(gson.toJson(pm.getNoticiasBanda(idBanda))).build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception ex) {
           return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    /**
     * Crea una nueva noticia
     * @param noticia
     * @return 
     */
    @POST
    @Path("/newNews")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNews(Noticia noticia){
        try {
            ProjectManager pm = new ProjectManager();
            pm.crearNoticia(noticia);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception ex) {
           return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    /**
     * Cancela un evento
     * @param idNoticia
     * @return 
     */
    @DELETE
    @Path("{idNoticia}")
    public Response deleteNews(@PathParam("idNoticia")int idNoticia){
        try {
            ProjectManager pm = new ProjectManager();
            pm.deleteNews(idNoticia);
            return Response.ok().build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception ex) {
            ex.printStackTrace();
           return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
}
