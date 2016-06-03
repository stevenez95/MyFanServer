/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

import com.myfan.dto.Genero;
import com.myfan.model.ProjectManager;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author steve_000
 */
@Path("generos")
public class MyFestService {
    
    @PUT
    @Path("edit/{idGenero}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editGenero(Genero genero ,@PathParam("idGenero")int idGenero){
          try {
            ProjectManager pm = new ProjectManager();
            pm.editarGenero(genero.getNombre(),idGenero);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception ex) {
           return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @DELETE
    @Path("delete/{idGenero}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteGenero(@PathParam("idGenero")int idGenero){
         try {
            ProjectManager pm = new ProjectManager();
            pm.borrarGenero(idGenero);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception ex) {
           return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
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
    
}
