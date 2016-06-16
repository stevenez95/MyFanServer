/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

import com.google.gson.Gson;
import com.myfan.model.ProjectManager;
import com.myfan.connection.IConstantes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Steven
 */
@Path("generos")
public class GenreService {
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response verGeneros (){
        try {
            ProjectManager manager = new ProjectManager();
            Gson g = new Gson();
            return Response.ok(g.toJson(manager.verGeneros())).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().entity(IConstantes.ERROR).build();
        }
    }
    
}
