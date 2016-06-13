/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.services;

import com.google.gson.JsonParser;
import com.myfan.connection.ImageSaver;
import com.myfan.dto.Banda;
import com.myfan.dto.Fan;
import com.myfan.dto.Genero;
import com.myfan.model.ProjectManager;
import com.myfan.security.IConstantes;
import com.myfan.security.JwtManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
            
            
            ImageSaver imageSaver = new ImageSaver();
            String foto = imageSaver.saveImage(banda.getFotoPerfil());
            
            banda.setFotoPerfil(foto);
            projectmanager.registrarBanda(banda);
            
            if(foto==null) return Response.status(500).entity(IConstantes.ERROR).build();
            return Response.ok(IConstantes.SUCCESS).build();
            
        } catch(SQLException ex){
            if(ex.getErrorCode() == 1048 || ex.getErrorCode() == 1062) return Response.status(500).entity(IConstantes.USUARIO_EXISTE).build();
            return Response.status(500).entity(IConstantes.ERROR).build();
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
            
            
            //System.out.println("foto " + fan.getFotoPerfil());
            ImageSaver imageSaver = new ImageSaver();
            String foto = imageSaver.saveImage(fan.getFotoPerfil());
           if(foto==null) return Response.status(500).entity(IConstantes.ERROR).build();
            
           fan.setFotoPerfil(foto);
            projectmanager.registrarFan(fan);
            
            return Response.ok(IConstantes.SUCCESS).build();
        } catch(SQLException ex){
            if(ex.getErrorCode() == 1048 || ex.getErrorCode() == 1062) return Response.status(500).entity(IConstantes.USUARIO_EXISTE).build();
            return Response.status(500).entity(IConstantes.ERROR).build();
        } catch (Exception ex) {
            return Response.status(500).entity(IConstantes.ERROR).build();
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
            JwtManager jm = new JwtManager();
            
            ProjectManager pm = new ProjectManager();
            String token = pm.login(fan.getUsername(), fan.getPassword());
            String type = jm.getType(token);
            System.out.println(type);
            String res = "{success:true,token:"+token+"type:"+type+"}";
            JsonParser jp = new JsonParser();
            return Response.ok(jp.parse(res).toString()).build();
        } catch (SQLException ex) {
            if(ex.getErrorCode()==1)return Response.status(403).entity(IConstantes.USUARIO_INCORRECTO).build();
            else return Response.serverError().entity("{\"error\":\"Ocurrio un errror en el servidor\"}").build();
        } catch (Exception ex) {
            return Response.serverError().entity("{\"error\":\"Ocurrio un errror en el servidor\"}").build();
        }
    }
    
    
}




