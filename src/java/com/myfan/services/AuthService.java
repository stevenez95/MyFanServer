/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.services;

import com.myfan.connection.ImageSaver;
import com.myfan.connection.MyFestConnection;
import com.myfan.dto.Banda;
import com.myfan.dto.Fan;
import com.myfan.dto.Message;
import com.myfan.model.ProjectManager;
import com.myfan.connection.IConstantes;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
        Message m = new Message();
        try {
            ProjectManager projectmanager = new ProjectManager();
            
            
            ImageSaver imageSaver = new ImageSaver();
            String foto = imageSaver.saveImage(banda.getFotoPerfil());
            
            banda.setFotoPerfil(foto);
            projectmanager.registrarBanda(banda);
            
            if(foto==null) {
                m.setSuccess(false);
                m.setMensaje(IConstantes.ERROR);
                return Response.status(500).entity(m).build();
            }

            MyFestConnection  connection = new MyFestConnection();
            connection.enviarArtistas(banda);
            return Response.ok().build();
            
        } catch(SQLException ex){
            m.setSuccess(false);
            m.setMensaje(IConstantes.USUARIO_EXISTE);
            if(ex.getErrorCode() == 1048 || ex.getErrorCode() == 1062) return Response.status(500).entity(m).build();
             m.setMensaje(IConstantes.ERROR);
            return Response.status(500).entity(m).build();
        } catch (Exception ex) {
            m.setMensaje(IConstantes.ERROR);
            return Response.status(500).entity(m).build();
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
         Message m = new Message();
        try {
            ProjectManager projectmanager = new ProjectManager();
            
            
            //System.out.println("foto " + fan.getFotoPerfil());
            ImageSaver imageSaver = new ImageSaver();
            String foto = imageSaver.saveImage(fan.getFotoPerfil());
           if(foto==null) {
                m.setSuccess(false);
                m.setMensaje(IConstantes.ERROR);
                return Response.status(500).entity(m).build();
            }
            
           fan.setFotoPerfil(foto);
            projectmanager.registrarFan(fan);
            
            return Response.ok().build();
        } catch(SQLException ex){
            m.setSuccess(false);
            m.setMensaje(IConstantes.USUARIO_EXISTE);
            if(ex.getErrorCode() == 1048 || ex.getErrorCode() == 1062) return Response.status(500).entity(m).build();
            m.setMensaje(IConstantes.ERROR);
            return Response.status(500).entity(m).build();
        } catch (Exception ex) {
            m.setMensaje(IConstantes.ERROR);
            return Response.status(500).entity(m).build();
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Fan fan){
        Message m = new Message();
        try {            
            ProjectManager pm = new ProjectManager();
            return Response.ok(pm.login(fan.getUsername(), fan.getPassword())).build();
        } catch (SQLException ex) {
            m.setSuccess(false);
            if(ex.getErrorCode()==1){
                m.setMensaje(IConstantes.USUARIO_INCORRECTO);
                return Response.status(403).entity(m).build();
            }
            else{
                m.setMensaje(IConstantes.ERROR);
                return Response.serverError().entity(m).build();
            }
        } catch (Exception ex) {
            m.setMensaje(IConstantes.ERROR);
            return Response.serverError().entity(m).build();
        }
    }
    
    
}




