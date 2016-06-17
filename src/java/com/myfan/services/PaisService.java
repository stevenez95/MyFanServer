/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

import com.google.gson.Gson;
import com.myfan.connection.IConstantes;
import com.myfan.model.Facade;
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
@Path("paises")
public class PaisService {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaises(){
        try {
            Facade manager = new Facade();
            Gson g = new Gson();
            return Response.ok(g.toJson(manager.getPaises())).build();
        } catch (Exception ex) {
            return Response.serverError().entity(IConstantes.m).build();
        }
    }
    
}
