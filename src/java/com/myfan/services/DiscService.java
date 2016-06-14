/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

import com.google.gson.Gson;
import com.myfan.dto.Cancion;
import com.myfan.dto.Discografia;
import com.myfan.model.ProjectManager;
import java.sql.SQLException;
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
    public Response getDiscs(@PathParam("idBanda")int idBanda){
        try {
            ProjectManager pm = new ProjectManager();
            Gson gson = new Gson();
            return Response.ok(gson.toJson(pm.getDiscs(idBanda))).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Status.NOT_FOUND).build();
        } catch (Exception ex) {
            ex.printStackTrace();
           return Response.status(Status.NOT_FOUND).build();
        }
    }
    
    /**
     * Retorne informacion y canciones de disco
     * @param idDisco
     * @return Response
     */
    @GET
    @Path("/{idDisco}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscsInfo(@PathParam("idDisco")int idDisco){
       try {
            ProjectManager pm = new ProjectManager();
            Gson gson = new Gson();
            return Response.ok(gson.toJson(pm.getDiscInfo(idDisco))).build();
        } catch (SQLException ex) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).build();        }
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
        try {
            ProjectManager pm = new ProjectManager();
            pm.createDisc(discografia);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).build();
        }
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
        try {
            ProjectManager pm = new ProjectManager();
            pm.editDisc(discografia, idDisco);
            return Response.ok().build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Status.NOT_FOUND).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Status.NOT_FOUND).build();
        }
    }
    
    /**
     * Borra un disco
     * @param idDisco
     * @return 
     */
    @DELETE
    @Path("delete/{idDisco}")
    public Response deleteDisc(@PathParam("idDisco")int idDisco){
        try {
            ProjectManager pm = new ProjectManager();
            pm.deleteDisc(idDisco);
            return Response.ok().build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Status.NOT_FOUND).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).build();
        }
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
        try {
            ProjectManager pm = new ProjectManager();
            pm.createSong(cancion);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).build();
        }
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
        try {
            ProjectManager pm = new ProjectManager();
            pm.editSong(cancion, idCancion);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
    
    /**
     * Borra una cancion de la discografia
     * @param idCancion
     * @return 
     */
    @DELETE
    @Path("deleteSong/{idCancion}")
    public Response deleteSong(@PathParam("idCancion")int idCancion){
        try {
            ProjectManager pm = new ProjectManager();
            pm.deleteSong(idCancion);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.status(404).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("getSongs/{idDisco}")
    public Response getSongs(@PathParam("idDisco")int idDisco){
        try {
            System.out.println("dsfsd");
            ProjectManager pm = new ProjectManager();
            Gson gson = new Gson();
            return Response.ok(gson.toJson(pm.getSongs(idDisco))).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Status.NOT_FOUND).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("getSong/{idCancion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSong(@PathParam("idCancion")int idCancion){
        ProjectManager pm = new  ProjectManager();
        return Response.ok(pm.getSong(idCancion)).build();
    }
    
    @GET
    @Path("getDiscRate/{idDisco}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscRate(@PathParam("idDisco")int idDisco) throws Exception{
        ProjectManager pm = new  ProjectManager();
        String res = "";
        Gson gson = new Gson();
        
        res = gson.toJson(pm.getDiscRate(idDisco));
        return Response.ok(res).build();
    }
    
    @GET
    @Path("getDiscComments/{idDisco}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscComments(@PathParam("idDisco")int idDisco) throws Exception{
        ProjectManager pm = new  ProjectManager();
        String res = "";
        Gson gson = new Gson();
        
        res = gson.toJson(pm.getDiscComments(idDisco));
        return Response.ok(res).build();
    } 
    
    /*
    Calificar
    obtener calificacion
    OBTENER COMENTARIOS
    */
    
}
