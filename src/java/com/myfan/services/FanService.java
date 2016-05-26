/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * REST Web Service
 *
 * @author Steven
 */
@Path("fan")
public class FanService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WebResource
     */
    public FanService() {
    }

    /**
     * Retrieves representation of an instance of com.mymusic.services.WebResource
     * @param token
     * @param id
     * @return an instance of java.lang.String
     */
    @GET
   // @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(/*@HeaderParam("x-access-token") String token,@PathParam("id") int id*/) {
        //TODO return proper representation object
      //  System.out.println("ID: "+id);
        return Response.ok("{\"token\":\"acdasda"+"\"}", MediaType.APPLICATION_JSON)
                .header("Access-Control-Allow-Origin", "http://localhost:8383")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
    }

    /**
     * PUT method for updating or creating an instance of WebResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
