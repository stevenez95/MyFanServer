/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.data;

import com.myfan.dto.Evento;
import com.myfan.dto.ResenaConcierto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author steve_000
 */
public class EventInfo {
    
    public EventInfo() {
    }
    
    
    /*FAN*/
    public ArrayList<Evento> getEventosFan(int idFan, Connection connection)throws SQLException{
        String query = "select e.idEvento, e.titulo, e.contenido, e.fechaCreacion,e.fechaEvento,e.concierto,e.ubicacion \n" +
                "from eventos e \n" +
                "join seguidos s \n" +
                "on s.idBanda = e.idBanda \n" +
                "where s.idFan = ? and e.cancelado = 0 \n" +
                "order by e.fechaCreacion;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idFan);
        ResultSet rs = ps.executeQuery();
        ArrayList<Evento> eventosFanList = new ArrayList<>();
        while(rs.next()){
            Evento evento = new Evento();
            evento.setIdBanda(rs.getInt("idEvento"));
            evento.setTitulo(rs.getString("titulo"));
            evento.setContenido(rs.getString("contenido"));
            evento.setFechaCreacion(rs.getString("fechaCreacion"));
            evento.setFechaEvento(rs.getString("fechaEvento"));
            evento.setConcierto(rs.getBoolean("concierto"));
            evento.setUbicacion(rs.getString("ubicacion"));
            eventosFanList.add(evento);
        }
        connection.close();
        ps.close();
        return eventosFanList;
    }
    
    public void rateEvent(ResenaConcierto resenaConcierto, Connection connection)throws SQLException{
        String query = "insert into resenasconcierto (idEvento,idFan,calificacion,comentario) value (?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, resenaConcierto.getIdEvento());
        ps.setInt(2, resenaConcierto.getIdFan());
        ps.setInt(3, resenaConcierto.getCalificacion());
        ps.setString(4, resenaConcierto.getComentario());
        ps.execute();
        ps.close();
        connection.close();
    }
    
    /*BANDA*/
    public void crearEvento(Evento evento, Connection connection)throws SQLException{
        String query = "insert into eventos (titulo,contenido,fechaCreacion,fechaEvento,concierto,ubicacion)\n" +
                "value (?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, evento.getTitulo());
        ps.setString(2, evento.getContenido());
        ps.setString(3, evento.getFechaCreacion());
        ps.setString(4, evento.getFechaEvento());
        ps.setString(5, evento.getFechaEvento());
        ps.setBoolean(6, evento.isConcierto());
        ps.execute();
        ps.close();
        connection.close();
    }
    
    public ArrayList<Evento> getEventosBanda(int idBanda, Connection connection)throws SQLException{
        String query = "select idEvento, titulo, contenido, fechaCreacion,fechaEvento,concierto,ubicacion,cancelado \n" +
                "from eventos \n" +
                "where idBanda = ? \n" +
                "order by fechaCreacion;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        ResultSet rs = ps.executeQuery();
        ArrayList<Evento> eventosFanList = new ArrayList<>();
        while(rs.next()){
            Evento evento = new Evento();
            evento.setIdBanda(rs.getInt("idEvento"));
            evento.setTitulo(rs.getString("titulo"));
            evento.setContenido(rs.getString("contenido"));
            evento.setFechaCreacion(rs.getString("fechaCreacion"));
            evento.setFechaEvento(rs.getString("fechaEvento"));
            evento.setConcierto(rs.getBoolean("concierto"));
            evento.setUbicacion(rs.getString("ubicacion"));
            evento.setCancelado(rs.getBoolean("cancelado"));
            eventosFanList.add(evento);
        }
        connection.close();
        ps.close();
        return eventosFanList;
    }
    
    public void cancelEvent(int idEvento, Connection connection)throws SQLException{
        String query = "update eventos \n" +
                "set cancelado = not cancelado \n" +
                "where idEvento = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idEvento);
        ps.executeUpdate();
        ps.close();
        connection.close();
    }
    
    public ArrayList<ResenaConcierto> getEventComments(int idEvento, Connection connection)throws SQLException{
        String query = "select comentario \n" +
                "from resenasconcierto \n" +
                "where idEvento = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idEvento);
        ResultSet rs = ps.executeQuery();
        ArrayList<ResenaConcierto> comentariosList = new ArrayList<>();
        while(rs.next()){
            ResenaConcierto  resenaConcierto = new ResenaConcierto();
            resenaConcierto.setComentario(rs.getString("comentario"));
            comentariosList.add(resenaConcierto);
        }
        connection.close();
        ps.close();
        return comentariosList;
    }
    
    public ArrayList<ResenaConcierto> getEventRate(int idEvento, Connection connection)throws SQLException{
        String query = "select avg(calificacion) as promedio, idEvento \n" +
                "from resenasconcierto \n" +
                "where idEvento = ? \n" +
                "group by idEvento;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idEvento);
        ResultSet rs = ps.executeQuery();
        ArrayList<ResenaConcierto> eventoPromList = new ArrayList<>();
        while(rs.next()){
            ResenaConcierto  resenaConcierto = new ResenaConcierto();
            resenaConcierto.setCalificacion(rs.getInt("promedio"));
            resenaConcierto.setIdEvento(rs.getInt("idEvento"));
            eventoPromList.add(resenaConcierto);
        }
        connection.close();
        ps.close();
        return eventoPromList;
    }
}
