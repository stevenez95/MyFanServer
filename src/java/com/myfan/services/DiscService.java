/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

import com.google.gson.Gson;
import com.myfan.dto.Cancion;
import com.myfan.dto.Discografia;
import com.myfan.model.Facade;
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
 * @author ToolMakers
 */
@Path("disco")
public class DiscService {
    
    /**
     * Retorna lista de discos de un artista
     * @param idBanda id de la badna
     * @return Discos de una banda
     */
    @GET
    @Path("/banda/{idBanda}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs(@PathParam("idBanda")int idBanda){
        try {
            Facade pm = new Facade();
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
     * Retorna informacion de un disco
     * @param idDisco id del disco
     * @return Informacion del disco
     */
    @GET
    @Path("/{idDisco}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscsInfo(@PathParam("idDisco")int idDisco){
       try {
            Facade pm = new Facade();
            Gson gson = new Gson();
            return Response.ok(gson.toJson(pm.getDiscInfo(idDisco))).build();
        } catch (SQLException ex) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).build();        }
    }
    
    /**
     * Crea un nuevo disco
     * @param discografia Disco a crear
     * @return Confirmacion
     */
    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewDisc(Discografia discografia){
        try {
            Facade pm = new Facade();
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
     * @param discografia Disco a actualizar
     * @param idDisco id del disco a actualizar
     * @return Confirmacion
     */
    @PUT
    @Path("edit/{idDisco}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editDisc(Discografia discografia, @PathParam("idDisco")int idDisco){
        try {
            Facade pm = new Facade();
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
     * @param idDisco id del disco a borrar
     * @return Confirmacion
     */
    @DELETE
    @Path("delete/{idDisco}")
    public Response deleteDisc(@PathParam("idDisco")int idDisco){
        try {
            Facade pm = new Facade();
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
     * @param cancion Cancion a crear
     * @return Confirmacion
     */
    @POST
    @Path("/newSong")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSong(Cancion cancion){
        try {
            Facade pm = new Facade();
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
     * @param cancion Cancion a aactulizar
     * @param idCancion id de la cancion a actualizar
     * @return 
     */
    @PUT
    @Path("editSong/{idCancion}")
    public Response editSong(Cancion cancion, @PathParam("idCancion")int idCancion){
        try {
            Facade pm = new Facade();
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
     * @param idCancion ide de la cancio a borrar
     * @return Confirmacion
     */
    @DELETE
    @Path("deleteSong/{idCancion}")
    public Response deleteSong(@PathParam("idCancion")int idCancion){
        try {
            Facade pm = new Facade();
            pm.deleteSong(idCancion);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.status(404).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
    
    /**
     * Obtiene las canciones de un disco
     * @param idDisco id del disco
     * @return Lista de canciones
     */
    @GET
    @Path("getSongs/{idDisco}")
    public Response getSongs(@PathParam("idDisco")int idDisco){
        try {
            System.out.println("dsfsd");
            Facade pm = new Facade();
            Gson gson = new Gson();
            return Response.ok(gson.toJson(pm.getSongs(idDisco))).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Status.NOT_FOUND).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
    
    /**
     * Obtiene informacion de una cancion
     * @param idCancion id del cancion
     * @return Informacion de la cancion
     */
    @GET
    @Path("getSong/{idCancion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSong(@PathParam("idCancion")int idCancion){
        Facade pm = new  Facade();
        return Response.ok(pm.getSong(idCancion)).build();
    }
    
    @GET
    @Path("getDiscRate/{idDisco}")
    public Response getDiscRate(@PathParam("idDisco")int idDisco) throws Exception{
        Facade pm = new  Facade();        
        return Response.ok(pm.getDiscRate(idDisco)).build();
    }
    
    /**
     * Obtiene los comentarios de un disco
     * @param idDisco id del disco
     * @return lista de comentarios
     * @throws Exception en caso de error
     */
    @GET
    @Path("getDiscComments/{idDisco}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscComments(@PathParam("idDisco")int idDisco) throws Exception{
        Facade pm = new  Facade();
        String res = "";
        Gson gson = new Gson();
        
        res = gson.toJson(pm.getDiscComments(idDisco));
        return Response.ok(res).build();
    } 
    
}
