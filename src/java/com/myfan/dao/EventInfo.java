/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.dao;

import com.myfan.dto.Evento;
import com.myfan.dto.Resena;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author steve_000
 */
public class EventInfo {
    
    public EventInfo() {
    }
    
    
    /*FAN*/
    public ArrayList<Evento> getEventosFan(int idFan, Connection connection)throws SQLException{
        String query = "select e.idEvento, e.titulo, e.contenido, e.fechaCreacion,e.fechaEvento,e.concierto,e.ubicacion,b.nombreBanda \n" +
                "from eventos e \n" +
                "join seguidos s \n" +
                "on s.idBanda = e.idBanda \n" +
                "join bandas b \n" +
                "on b.idBanda = e.idBanda \n"+
                "where s.idFan = ? and e.cancelado = 0 and b.activo = 1 \n" +
                "order by e.fechaCreacion asc;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idFan);
        ResultSet rs = ps.executeQuery();
        ArrayList<Evento> eventosFanList = new ArrayList<>();
        while(rs.next()){
            Evento evento = new Evento();
            evento.setIdEvento(rs.getInt("idEvento"));
            evento.setTitulo(rs.getString("titulo"));
            evento.setContenido(rs.getString("contenido"));
            evento.setFechaCreacion(rs.getDouble("fechaCreacion"));
            evento.setFechaEvento(rs.getDate("fechaEvento").toString());
            evento.setConcierto(rs.getBoolean("concierto"));
            evento.setUbicacion(rs.getString("ubicacion"));
            evento.setCreadoPor(rs.getString("nombreBanda"));
            eventosFanList.add(evento);
        }
        connection.close();
        ps.close();
        return eventosFanList;
    }
    
    /*BANDA*/
    public void crearEvento(Evento evento, Connection connection)throws SQLException{
        String query = "insert into eventos (titulo,contenido,fechaCreacion,fechaEvento,concierto,ubicacion,idBanda)\n" +
                "value (?,?,?,?,?,?,?)";
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date fecha = null;
        try {
            fecha = new java.sql.Date(format.parse(evento.getFechaEvento()).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, evento.getTitulo());
        ps.setString(2, evento.getContenido());
        ps.setDouble(3, evento.getFechaCreacion());
        ps.setDate(4, fecha);
        ps.setBoolean(5, evento.isConcierto());
        ps.setString(6, evento.getUbicacion());
        ps.setInt(7, evento.getIdBanda());
        ps.execute();
        ps.close();
        connection.close();
    }
    
    public ArrayList<Evento> getEventosBanda(int idBanda, Connection connection)throws SQLException{
        String query = "select idEvento, titulo, contenido, fechaCreacion,fechaEvento,concierto,ubicacion,cancelado \n" +
                "from eventos \n" +
                "where idBanda = ? \n" +
                "order by fechaCreacion asc;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        ResultSet rs = ps.executeQuery();
        ArrayList<Evento> eventosFanList = new ArrayList<>();
        while(rs.next()){
            Evento evento = new Evento();
            evento.setIdEvento(rs.getInt("idEvento"));
            evento.setTitulo(rs.getString("titulo"));
            evento.setContenido(rs.getString("contenido"));
            evento.setFechaCreacion(rs.getDouble("fechaCreacion"));
            evento.setFechaEvento(rs.getDate("fechaEvento").toString());
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
    
    public ArrayList<Resena> getEventComments(int idEvento, Connection connection)throws SQLException{
        String query = "select r.comentario, f.username, r.fecha\n" +
                "from resenasconcierto r \n" +
                "join fans f\n" +
                "on f.idFan = r.idFan\n" +
                "where r.idEvento = ? \n"
                + "order by r.fecha desc \n"
                + "limit 6;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idEvento);
        ResultSet rs = ps.executeQuery();
        ArrayList<Resena> comentariosList = new ArrayList<>();
        while(rs.next()){
            Resena  resenaConcierto = new Resena();
            resenaConcierto.setComentario(rs.getString("comentario"));
            resenaConcierto.setCreadoPor(rs.getString("username"));
            resenaConcierto.setFecha(rs.getDouble("fecha"));
            comentariosList.add(resenaConcierto);
        }
        connection.close();
        ps.close();
        return comentariosList;
    }
    
    public float getEventRate(int  idEvento, Connection connection)throws SQLException{
        String query = "select avg(calificacion) as promedio, idEvento \n" +
                "from resenasconcierto \n" +
                "where idEvento = ? \n" +
                "group by idEvento;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idEvento);
        ResultSet rs = ps.executeQuery();
        float promedio = 0;
        while(rs.next()){
            promedio= rs.getFloat("promedio");
        }
        connection.close();
        ps.close();
        return promedio;
    }
    
    public Evento getEventInfo(int idEvento, Connection connection)throws SQLException{
        String query = "select idEvento, titulo, contenido,fechaEvento,ubicacion \n" +
                "from eventos \n" +
                "where idEvento = ?;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idEvento);
        
        ResultSet rs = ps.executeQuery();
        rs.next();
        Evento evento = new Evento();
        
        evento.setIdEvento(rs.getInt("idEvento"));
        evento.setTitulo(rs.getString("titulo"));
        evento.setContenido(rs.getString("contenido"));
        evento.setFechaEvento(rs.getDate("fechaEvento").toString());
        evento.setUbicacion(rs.getString("ubicacion"));
        
        ps.close();
        connection.close();
        return evento;
    }
}
