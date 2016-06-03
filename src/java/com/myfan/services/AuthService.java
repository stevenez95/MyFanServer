/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.services;

import com.myfan.dto.Banda;
import com.myfan.dto.Fan;
import com.myfan.model.ProjectManager;
import com.myfan.security.IConstantes;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Steven
 */
@Path("autenticar")
public class AuthService {
    
    /**
     * Registra una nueva banda
     * @param banda
     * @return 
     */
    @POST
    @Path("signUpBanda")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarBanda(Banda banda){
        try {
            ProjectManager projectmanager = new ProjectManager();
            projectmanager.registrarBanda(banda);
            return Response.ok(IConstantes.SUCCESS).build();
        } catch (Exception ex) {
            return Response.status(404).build();
        }
    }
    
    /**
     * Crea un nuevo fan
     * @param fan
     * @return 
     */
    @POST
    @Path("signUpFan")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarFan(Fan fan){
        try {
            ProjectManager projectmanager = new ProjectManager();
            projectmanager.registrarFan(fan);
            System.out.println("user name es" + fan.getFechaCreacion());
            return Response.ok(IConstantes.SUCCESS).build();
        } catch (Exception ex) {
            return Response.status(404).entity(IConstantes.NOT_FOUND).build();
        }
        
    }
    
    /**
     * Loguea a los usuarios al sistema
     * @param fan
     * @return 
     */
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Fan fan){
        try {
            ProjectManager pm = new ProjectManager();
            String token = pm.login(fan.getUsername(), fan.getPassword());
            return Response.ok("{\"token\":" + "\"" + token + "\"" + "}").build();
        } catch (SQLException ex) {
            if(ex.getErrorCode()==1)return Response.status(Response.Status.NOT_FOUND).entity(IConstantes.USUARIO_INCORRECTO).build();
            else return Response.serverError().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
    
}




