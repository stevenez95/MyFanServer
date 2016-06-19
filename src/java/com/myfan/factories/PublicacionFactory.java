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
