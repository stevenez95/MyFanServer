/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.myfan.dto.Banda;
import com.myfan.dto.Fan;
import com.myfan.model.ProjectManager;
import com.myfan.security.IConstantes;
import com.myfan.security.JwtManager;
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
    
  @POST
 @Path("SignUpBanda")
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
    
  @POST
 @Path("SignUpFan")
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
 
  @POST
 @Path("Login")
 @Consumes(MediaType.APPLICATION_JSON)
 public Response login(Fan fan){
      try {
          //     System.out.println("Login es " + fan.getUsername());
//     JwtManager jwtManager = new JwtManager();
//     JsonParser json = new JsonParser();
//     String res = json.parse("{success:true,token:"+jwtManager.jwtGenerate()+"}").toString();
          ProjectManager manager = new ProjectManager();
          manager.login(fan.getUsername(), fan.getPassword());
          return Response.ok().build();
      } catch (Exception ex) {
          Logger.getLogger(AuthService.class.getName()).log(Level.SEVERE, null, ex);
          return Response.status(400).build();
      }
}
 
}




