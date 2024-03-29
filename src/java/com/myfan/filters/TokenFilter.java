/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.filters;

import com.myfan.security.JwtManager;
import java.io.IOException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Steven
 */
@Provider
public class TokenFilter implements ContainerRequestFilter{

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        JwtManager jwtManager = new JwtManager();
        String path = requestContext.getUriInfo().getPath();
        if (path.equals("autenticar/login") || path.equals("autenticar/signUpBanda") || path.equals("autenticar/signUpFan") || path.equals("generos") || path.equals("paises")) {
            System.out.println("logg");
            return;
        }
        if(requestContext.getMethod().equals("OPTIONS")) {
            throw new WebApplicationException(Status.OK);
        }
        
        String token = requestContext.getHeaderString("x-access-token");
        if(!jwtManager.jwtValidate(token)){
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
        }
    }
    
}
