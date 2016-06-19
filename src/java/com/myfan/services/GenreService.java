/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.services;

import com.google.gson.Gson;
import com.myfan.model.Facade;
import com.myfan.connection.IConstantes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ToolMakers
 */
@Path("generos")
public class GenreService {
    
    /**
     * Obtiene todos los generos musicales creado
     * @return Lista de generos musicales
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response verGeneros (){
        try {
            Facade manager = new Facade();
            Gson g = new Gson();
            return Response.ok(g.toJson(manager.verGeneros())).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().entity(IConstantes.ERROR).build();
        }
    }
    
}
