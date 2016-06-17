/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.dao;

import com.myfan.connection.MyFestConnection;
import com.myfan.dto.Banda;
import com.myfan.dto.Evento;
import com.myfan.dto.Genero;
import com.myfan.dto.Resena;
import com.myfan.security.PasswordEncrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Steven
 */
public class BandInfo {
    
    public BandInfo() {}
    
    public void registrarBanda(Banda banda,Connection connection) throws SQLException{
        String query = "insert into Bandas (username,password,nombreBanda,anioCreacion,hashtag,biografia,fechaCreacion,idPais,integrantes,fotoPerfil) value (?,?,?,?,?,?,?,?,?,?);";
        String password = PasswordEncrypt.hashPassword(banda.getPassword());
        if(banda.getHashtag().charAt(0) != '#') banda.setHashtag("#"+banda.getHashtag());
        PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, banda.getUsername());
        ps.setString(2, password);
        ps.setString(3, banda.getNombreBanda());
        ps.setInt(4, banda.getAnioCreacion());
        ps.setString(5, banda.getHashtag());
        ps.setString(6, banda.getBiografia());
        ps.setDouble(7, banda.getFechaCreacion());
        ps.setInt(8, banda.getIdPais());
        ps.setString(9, banda.getIntegrantes());
        ps.setString(10, "http://localhost:8080/MyFanServer/uploads/"+banda.getFotoPerfil());
        ps.execute();
        
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int id = rs.getInt(1);
        ingresarGenerosBanda(banda.getGeneros(), id, connection);
        MyFestConnection  connection1 = new MyFestConnection();
        banda.setIdBanda(id);
        connection1.enviarArtistas(banda);
        ps.close();
        connection.close();
    }
    
    public void actualizarBanda(Banda banda,int idBanda, Connection connection)throws SQLException{
        String query1 = "update bandas set password = ?,nombreBanda= ?,hashtag= ?,biografia= ?,integrantes= ?,idPais= ?,fotoPerfil= ?, anioCreacion=? where idBanda = ?;";
        String query2 = "update bandas set nombreBanda= ?,hashtag= ?,biografia= ?,integrantes= ?,idPais= ?,fotoPerfil= ?, anioCreacion=? where idBanda = ?;";
        PreparedStatement ps;
        String pass="";
        if(banda.getHashtag().charAt(0) != '#') banda.setHashtag("#"+banda.getHashtag());
        if(banda.getPassword()==null){
            System.out.println("entreeeee");
            ps = connection.prepareStatement(query2);
            ps.setString(1, banda.getNombreBanda());
            ps.setString(2, banda.getHashtag());
            ps.setString(3, banda.getBiografia());
            ps.setString(4, banda.getIntegrantes());
            ps.setInt(5, banda.getIdPais());
            ps.setString(6, banda.getFotoPerfil());
            ps.setInt(7, banda.getAnioCreacion());
            ps.setInt(8, idBanda);
        }
        else{
            ps = connection.prepareStatement(query1);
            pass = PasswordEncrypt.hashPassword(banda.getPassword());
            ps.setString(1, pass);
            ps.setString(2, banda.getNombreBanda());
            ps.setString(3, banda.getHashtag());
            ps.setString(4, banda.getBiografia());
            ps.setString(5, banda.getIntegrantes());
            ps.setInt(6, banda.getIdPais());
            ps.setString(7, banda.getFotoPerfil());
            ps.setInt(8, banda.getAnioCreacion());
            ps.setInt(9, idBanda);
        }
        
        ps.executeUpdate();
        ingresarGenerosBanda(banda.getGeneros(), idBanda, connection);
        MyFestConnection myFestConnection = new MyFestConnection();
        myFestConnection.actualizarArtistas(banda, idBanda);
        
        ps.close();
        connection.close();
    }
    
    private void ingresarGenerosBanda(int[] generos, int idBanda, Connection c)throws SQLException{
        if(generos==null) return;
        if(generos.length == 0) return;
        String delete = "delete from bandageneros where idBanda = ?;";
        PreparedStatement ps1 = c.prepareStatement(delete);
        ps1.setInt(1, idBanda);
        ps1.execute();
        
        String insert = "insert into bandageneros (idGenero, idBanda) value (?,?);";
        for (int i = 0; i < generos.length; i++) {
            PreparedStatement ps2 = c.prepareStatement(insert);
            ps2.setInt(1, generos[i]);
            ps2.setInt(2, idBanda);
            ps2.execute();
            ps2.close();
        }
        
        ps1.close();
        c.close();
        
    }
    
    public void desactivarBanda(int idBanda,Connection connection)throws SQLException{
        String query = "update bandas set activo = not activo where idBanda = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        ps.executeUpdate();
        ps.close();
        connection.close();
    }
    
    public int getCantidadSeguidores(int idBanda, Connection connection)throws SQLException{
        String query ="select count(*) as total\n" +
                "from seguidos s \n" +
                "join fans f\n" +
                "on s.idFan = f.idFan\n" +
                "where s.idBanda = ? and f.activo = 1;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        
        ResultSet rs = ps.executeQuery();
        rs.next();
        int seguidores = rs.getInt("total");
        
        ps.close();
        connection.close();
        return seguidores;
        
    }
    
    public int getCantidadEventos(int idBanda, Connection connection)throws SQLException{
        String query ="select count(*) as total\n" +
                "from eventos e \n" +
                "join bandas b \n" +
                "on e.idBanda = b.idBanda\n" +
                "where e.idBanda = ?;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        
        ResultSet rs = ps.executeQuery();
        rs.next();
        int eventos = rs.getInt("total");
        
        ps.close();
        connection.close();
        return eventos;
        
    }
    
     public ArrayList<Evento> getUltimosEventos(int idBanda, Connection connection)throws SQLException{
        String query ="SELECT count(idBanda) as Eventos, month(fechaEvento) as mes\n" +
                        " FROM eventos\n" +
                        " where idBanda = ? and fechaEvento >= date_sub(curdate(), interval 6 month)\n" +
                        " GROUP BY (month(fechaEvento) )\n" +
                        " order by fechaEvento desc;";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        ArrayList<Evento> eventos = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Evento evento = new Evento();
            evento.setIdBanda(rs.getInt("Eventos"));
            evento.setContenido(rs.getString("mes"));
            eventos.add(evento);
            
        }
        ps.close();
        connection.close();
        return eventos;
        
    } 
    
    public ArrayList<Resena> getBandComments(int idBanda, Connection connection)throws SQLException{
        String query = "select r.comentario, f.username, r.fecha\n" +
                "from resenasbanda r\n" +
                "join fans f\n" +
                "on f.idFan = r.idFan\n" +
                "where r.idBanda = ? \n"
                +"order by r.fecha desc \n"
                + "limit 6;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        ResultSet rs = ps.executeQuery();
        ArrayList<Resena> bandaComentList = new ArrayList<>();
        while(rs.next()){
            Resena resenabanda = new Resena();
            resenabanda.setComentario(rs.getString("comentario"));
            resenabanda.setCreadoPor(rs.getString("username"));
            resenabanda.setFecha(rs.getDouble("fecha"));
            bandaComentList.add(resenabanda);
        }
        connection.close();
        ps.close();
        return bandaComentList;
    }
    
    public float getBandRate(int  idBanda, Connection connection)throws SQLException{
        String query = "select avg(calificacion) as promedio, idBanda \n" +
                "from resenasbanda \n" +
                "where idBanda = ? \n" +
                "group by idBanda;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        ResultSet rs = ps.executeQuery();
        float promedio = 0;
        while(rs.next()){
            promedio= rs.getFloat("promedio");
        }
        connection.close();
        ps.close();
        return promedio;
    }
    
    public Banda getBandInfo(int idBanda, Connection connection) throws SQLException {
        String query = "select b.idBanda, b.username, b.nombreBanda, b.hashtag, p.pais, b.activo, b.anioCreacion,b.integrantes, b.biografia, b.fotoPerfil \n" +
                "from bandas b\n" +
                "join paises p\n" +
                "on p.idPais = b.idPais\n"+
                "where b.idBanda = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        ResultSet rs = ps.executeQuery();
        Banda banda = new Banda();
        while(rs.next()){
            banda.setIdBanda(rs.getInt("idBanda"));
            banda.setUsername(rs.getString("username"));
            banda.setNombreBanda(rs.getString("nombreBanda"));
            banda.setHashtag(rs.getString("hashtag"));
            banda.setActivo(rs.getBoolean("activo"));
            banda.setPais(rs.getString("pais"));
            banda.setAnioCreacion(rs.getInt("anioCreacion"));
            banda.setIntegrantes(rs.getString("integrantes"));
            banda.setBiografia(rs.getString("biografia"));
            banda.setFotoPerfil(rs.getString("fotoPerfil"));
        }
        connection.close();
        ps.close();
        return banda;
        
    }
    
    public ArrayList<Genero> getBandGeneros (int idBanda, Connection connection)throws SQLException{
        String query = "select g.nombre,g.idGenero\n" +
                "from bandageneros bg\n" +
                "join bandas b\n" +
                "on bg.idBanda = b.idBanda\n" +
                "join generos g\n" +
                "on g.idGenero = bg.idGenero\n" +
                "where bg.idBanda = ?;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        
        ResultSet rs = ps.executeQuery();
        ArrayList<Genero> generosList = new ArrayList<>();
        while(rs.next()){
            Genero genero = new Genero();
            genero.setIdGenero(rs.getInt("idGenero"));
            genero.setNombre(rs.getString("nombre"));
            generosList.add(genero);
        }
        
        connection.close();
        ps.close();
        return generosList;
    }
    
    public float getAllDiscRate(int idBanda, Connection connection)throws SQLException{
        String query = "SELECT avg(resenasdisco.calificacion) as promedio, idBanda \n" +
                " FROM discografias \n" +
                "INNER JOIN resenasdisco \n" +
                "ON discografias.idDiscografia = resenasdisco.idDisco \n" +
                "where idBanda = ? \n" +
                "group by idBanda; ";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        ResultSet rs = ps.executeQuery();
        float promedio = 0;
        while(rs.next()){
            promedio= rs.getFloat("promedio");
        }
        connection.close();
        ps.close();
        return promedio;
    }
    
    public float getConcertRate(int idBanda, Connection connection)throws SQLException{
        String query = "SELECT avg(resenasconcierto.calificacion) as promedio, idBanda \n" +
                "FROM eventos \n" +
                "INNER JOIN resenasconcierto \n" +
                "ON eventos.idEvento = resenasconcierto.idEvento \n" +
                "where idBanda = ?  and concierto = true and cancelado = 0\n" +
                "group by idBanda; ";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        ResultSet rs = ps.executeQuery();
        float promedio = 0;
        while(rs.next()){
            promedio= rs.getFloat("promedio");
        }
        connection.close();
        ps.close();
        return promedio;
    }
    
}
