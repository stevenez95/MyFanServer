/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.data;

import java.sql.Connection;

/**
 *
 * @author Steven
 */
public class NewsFeedInfo {

    public NewsFeedInfo() {
    }
    
    public void getNoticias(int idFan, Connection connection){
        String query = "select n.idNoticia , n.titulo, n.contenido, n.fechaCreacion \n" +
                "from noticias n \n" +
                "join seguidos s \n" +
                "	on s.idBanda = n.idBanda \n" +
                "where s.idFan = ? \n" +
                "order by n.fechaCreacion;";
        
    }
    
    public void deleteNews(int idNoticia, Connection connection){
        String query = "delete from noticias \n" +
                "where idNoticia = ?;";
    }
    
    public void getEventos(int idFan, Connection connection){
        String query = "select e.idEvento, e.titulo, e.contenido, e.fechaCreacion,e.fechaEvento,e.concierto,e.ubicacion \n" +
                "from eventos e \n" +
                "join seguidos s \n" +
                "on s.idBanda = e.idBanda \n" +
                "where s.idFan = ? and e.cancelado = 0 \n" +
                "order by e.fechaCreacion;";
    }
    
    public void cancelEvent(int idEvento, Connection connection){
        String query = "update eventos \n" +
                "set cancelado = not cancelado \n" +
                "where idEvento = ?;";
    }
    
}
