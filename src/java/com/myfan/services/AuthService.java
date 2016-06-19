/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.services;

import com.myfan.dto.Banda;
import com.myfan.dto.Fan;
import com.myfan.model.Facade;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ToolMakers
 */
@Path("autenticar")
public class AuthService {
    
    /**
     * Registra una nueva banda
     * @param banda Banda nueva
     * @return Mensaje de confirmacion
     */
    @POST
    @Path("signUpBanda")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarBanda(Banda banda){
        Facade facade = new Facade();
        return facade.registrarBanda(banda);
    }
    
    /**
     * Crea un nuevo fan
     * @param fan Nuevo fanatico
     * @return Mensaje de confirmacion
     */
    @POST
    @Path("signUpFan")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarFan(Fan fan){
        Facade f = new Facade();
        return f.registrarFan(fan);
    }
    
    /**
     * Loguea a los usuarios al sistema
     * @param fan Informacion del usuario que hace login
     * @return Mensaje de respuesta
     */
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Fan fan){
        Facade pm = new Facade();
        return pm.login(fan.getUsername(), fan.getPassword());
    }
    
    
}




