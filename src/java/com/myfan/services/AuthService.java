/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.services;

import com.google.gson.JsonParser;
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
import javax.ws.rs.core.Response.Status;

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
            //System.out.println("user name es" + banda.getFechaCreacion());
            return Response.ok(IConstantes.SUCCESS).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(403).build();
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
            ex.printStackTrace();
            return Response.status(400).entity(IConstantes.NOT_FOUND).build();
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
            JsonParser jp = new JsonParser();
            ProjectManager manager = new ProjectManager();
            String token = manager.login(fan.getUsername(), fan.getPassword());
            String res = jp.parse("{success:true,token:"+token+"}").toString();
            return Response.ok(res).build();
        } catch (SQLException ex) {
            if(ex.getErrorCode()==1){
                return Response.status(Status.NOT_FOUND).entity(IConstantes.USUARIO_INCORRECTO).build();
            }
            return Response.status(403).build();
        } catch (Exception ex) {
            Logger.getLogger(AuthService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(403).build();
        }
        
    }
    
}




