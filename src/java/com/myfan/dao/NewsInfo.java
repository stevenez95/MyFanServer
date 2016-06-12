/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.dao;

import com.myfan.dto.Noticia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Steven
 */
public class NewsInfo {
    
    public NewsInfo() {
    }
    
    /*FAN*/
    public ArrayList<Noticia> getNoticiasFan(int idFan, Connection connection)throws SQLException{
        String query = "select n.idNoticia , n.titulo, n.contenido, n.fechaCreacion, b.nombreBanda \n" +
                "from noticias n \n" +
                "join seguidos s \n" +
                "on s.idBanda = n.idBanda \n" +
                "join bandas b \n" +
                "on b.idBanda = n.idBanda \n"+
                "where s.idFan = ? \n" +
                "order by n.fechaCreacion asc;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idFan);
        ResultSet rs = ps.executeQuery();
        ArrayList<Noticia> noticiasFanList = new ArrayList<>();
        while(rs.next()){
            Noticia noticia = new Noticia();
            noticia.setIdNoticia(rs.getInt("idNoticia"));
            noticia.setTitulo(rs.getString("titulo"));
            noticia.setContenido(rs.getString("contenido"));
            noticia.setFechaCreacion(rs.getDouble("fechaCreacion"));
            noticia.setCreadoPor(rs.getString("nombreBanda"));
            noticiasFanList.add(noticia);
        }
        connection.close();
        ps.close();
        return noticiasFanList;
    }
    
    /*BANDA*/
    public void crearNoticia(Noticia noticia, Connection connection)throws SQLException{
        String query = "insert into noticias (titulo,contenido,fechaCreacion,idBanda) \n" +
                "value (?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, noticia.getTitulo());
        ps.setString(2, noticia.getContenido());
        ps.setDouble(3, noticia.getFechaCreacion());
        ps.setInt(4, noticia.getIdBanda());
        ps.execute();
        ps.close();
        connection.close();
    }
    
    public ArrayList<Noticia> getNoticiasBanda(int idBanda, Connection connection)throws SQLException{
        String query = "select idNoticia , titulo, contenido, fechaCreacion \n" +
                "from noticias \n" +
                "where idBanda = ? \n" +
                "order by fechaCreacion desc;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        ResultSet rs = ps.executeQuery();
        ArrayList<Noticia> noticiasBandaList = new ArrayList<>();
        while(rs.next()){
            Noticia noticia = new Noticia();
            noticia.setIdNoticia(rs.getInt("idNoticia"));
            noticia.setTitulo(rs.getString("titulo"));
            noticia.setContenido(rs.getString("contenido"));
            noticia.setFechaCreacion(rs.getDouble("fechaCreacion"));
            noticiasBandaList.add(noticia);
        }
        connection.close();
        ps.close();
        return noticiasBandaList;
        
    }
    
    public void deleteNews(int idNoticia, Connection connection)throws SQLException{
        String query = "delete from noticias \n" +
                "where idNoticia = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idNoticia);
        ps.execute();
        ps.close();
        connection.close();
    }
    
    
}
