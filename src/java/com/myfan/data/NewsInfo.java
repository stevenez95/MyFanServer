/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.data;

import com.myfan.dto.Noticia;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Steven
 */
public class NewsInfo {

    public NewsInfo() {
    }
    
    /*FAN*/
    public void getNoticiasFan(int idFan, Connection connection)throws SQLException{
        String query = "select n.idNoticia , n.titulo, n.contenido, n.fechaCreacion \n" +
                "from noticias n \n" +
                "join seguidos s \n" +
                "	on s.idBanda = n.idBanda \n" +
                "where s.idFan = ? \n" +
                "order by n.fechaCreacion;";
        
    }
    
    /*BANDA*/
    public void crearNoticia(Noticia noticia, Connection connection)throws SQLException{
        String query = "insert into noticias (titulo,contenido,fechaCreacion,idBanda) \n" +
                "value (?,?,?,?);";
    }
    
    public void getNoticiasBanda(int idBanda, Connection connection)throws SQLException{
        String query = "select idNoticia , titulo, contenido, fechaCreacion \n" +
                "from noticias \n" +
                "where idBanda = ? \n" +
                "order by fechaCreacion;";
        
    }
    
    public void deleteNews(int idNoticia, Connection connection)throws SQLException{
        String query = "delete from noticias \n" +
                "where idNoticia = ?;";
    }

    
}
