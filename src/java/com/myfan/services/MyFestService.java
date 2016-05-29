/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

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
    public Response editGenero(@PathParam("idGenero")int idGenero){
       return Response.ok().build();
    }
    
    @DELETE
    @Path("delete/{idGenero}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteGenero(@PathParam("idGenero")int idGenero){
       return Response.ok().build();
    }
    
    @POST
    @Path("new")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newGenero(){
       return Response.ok().build();
    }
    
}
