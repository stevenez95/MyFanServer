/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.data;

import com.myfan.dto.Evento;
import com.myfan.dto.ResenaConcierto;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author steve_000
 */
public class EventInfo {

    public EventInfo() {
    }
    
        
    /*FAN*/
    public void getEventosFan(int idFan, Connection connection)throws SQLException{
        String query = "select e.idEvento, e.titulo, e.contenido, e.fechaCreacion,e.fechaEvento,e.concierto,e.ubicacion \n" +
                "from eventos e \n" +
                "join seguidos s \n" +
                "on s.idBanda = e.idBanda \n" +
                "where s.idFan = ? and e.cancelado = 0 \n" +
                "order by e.fechaCreacion;";
    }
    
    public void rateEvent(ResenaConcierto resenaConcierto, Connection connection)throws SQLException{
        String query = "insert into resenasconcierto (idEvento,idFan,calificacion,comentario) value (?,?,?,?);";
    }
    
    /*BANDA*/
    public void crearEvento(Evento evento, Connection connection)throws SQLException{
        String query = "insert into eventos (titulo,contenido,fechaCreacion,fechaEvento,concierto,ubicacion)\n" +
                        "value (?,?,?,?,?,?)";
    }
    
    public void getEventosBanda(int idBanda, Connection connection)throws SQLException{
        String query = "select idEvento, titulo, contenido, fechaCreacion,fechaEvento,concierto,ubicacion,cancelado \n" +
                "from eventos \n" +
                "where idBanda = ? \n" +
                "order by fechaCreacion;";
    }
    
    public void cancelEvent(int idEvento, Connection connection)throws SQLException{
        String query = "update eventos \n" +
                "set cancelado = not cancelado \n" +
                "where idEvento = ?;";
    }
    
    public void getEventComments(int idEvento, Connection connection)throws SQLException{
        String query = "select comentario \n" +
                "from resenasconcierto \n" +
                "where idEvento = ?";
    }
    
    public void getEventRate(int idEvento, Connection connection)throws SQLException{
        String query = "select avg(calificacion) as promedio, idEvento \n" +
                "from resenasconcierto \n" +
                "where idEvento = ? \n" +
                "group by idEvento;";
        
    }
}
