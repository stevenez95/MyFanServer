/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.data;

import com.myfan.dto.Genero;

/**
 *
 * @author steve_000
 */
public class GenreInfo {

    public GenreInfo() {
    }
    
    public void verGeneros(){
        String query = "select idGenero,nombre \n" +
                "from generos;";
    }
    
    public void editarGenero(String nombre,int idGenero){
        String query = "update genero set nombre = ? \n" +
                "where idGenero = ?; ";
    }
    
    public void borrarGenero(int idGenero){
        String query = "delete from generos \n" +
                "where idGenero = ?;";
    }
    
    public void crearGenero(Genero genero){
        String query = "insert into generos (idGenero,nombre) value (?,?);";
    }
    
}
