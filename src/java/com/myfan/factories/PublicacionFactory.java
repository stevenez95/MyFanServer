/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.factories;

/**
 *
 * @author Steven
 */
public class PublicacionFactory {

    public PublicacionFactory() {
    }
    
    /**
     * Patron Factory que se encarga de crear una publicacion en Twitter de seguimiento
     * o califiacion dependiendo de la accion del cliente
     * @param tipo de publicacion que se desea realizar 
     */
    public IPublicacion getPublicacion(String tipo){
        if(tipo == null)return null;
        switch(tipo){
            case "seguimiento":
                return new Seguimiento();
            case "calificacion":
                return new Calificacion();
        }
        return null;
    }
    
}
