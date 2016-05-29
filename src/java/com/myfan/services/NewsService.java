/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

import com.myfan.dto.Noticia;
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
        return Response.ok().build();
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
        return Response.ok().build();
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
        return Response.ok().build();
    }
    
    /**
     * Cancela un evento
     * @param idNoticia
     * @return 
     */
    @DELETE
    @Path("delete/{idNoticia}")
    public Response deleteNews(@PathParam("idNoticia")int idNoticia){
        return Response.ok().build();
    }
    
}
