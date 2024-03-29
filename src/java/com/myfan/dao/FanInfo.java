/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.dao;

import com.myfan.dto.Banda;
import com.myfan.dto.Fan;
import com.myfan.dto.Genero;
import com.myfan.dto.Resena;
import com.myfan.security.PasswordEncrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Steven
 */
public class FanInfo {
    
    public FanInfo() {
    }
    
    /**
     * Un fanatico se hace seguidor de una banda
     * @param idFan  fanatico que va a seguir a la banda
     * @param idBanda  que se desea seguir
     * @param connection conexion a la BD
     * @throws SQLException En caso de haber un error en la BD
     */
    public void seguirBanda(int idFan , int idBanda, Connection connection)throws SQLException{
        String query = "insert into seguidos (idFan, idBanda) value (?,?);";
        System.out.println("seguir");
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idFan);
        ps.setInt(2, idBanda);
        ps.execute();
        ps.close();
        connection.close();
    }
    
    /**
     * Un fanatico deja de seguir una banda
     * @param idFan  fanatico que va a dejar de seguir a la banda
     * @param idBanda  que se desea dejar de seguir
     * @param connection conexion a la BD
     * @throws SQLException En caso de haber un error en la BD
     */
    public void dejarSeguirBanda(int idFan,  int idBanda, Connection connection)throws SQLException{
        String query = "delete from seguidos where idFan = ? and idBanda = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idFan);
        ps.setInt(2, idBanda);
        ps.execute();
        ps.close();
        connection.close();
    }
    
    /**
     * Un fanatico desactiva la cuenta
     * @param idFan  que desea desactivar la cuenta
     * @param connection conexion a la BD
     * @throws SQLException En caso de haber un error en la BD
     */
    public void desactivarFan(int idFan, Connection connection)throws SQLException{
        String query = "update fans set activo = not activo where idFan = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idFan);
        ps.executeUpdate();
        ps.close();
        connection.close();
    }
    
    /**
     * Un fanatico actualiza la informacion de su perfil
     * @param idFan  fanatico que desea modificar la informacion
     * @param connection conexion a la BD
     * @throws SQLException En caso de haber un error en la BD
     */
    public void actualizarFan(Fan fan,int idFan, Connection connection)throws SQLException{
        String query2 = "update fans set nombre= ?,apellido= ?,fechaNac= ?,genero= ?,idPais= ?,fotoPerfil= ? where idFan = ?;";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date fecha = null;
        try {
            fecha = new java.sql.Date(format.parse(fan.getFechaNac()).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement ps;
        ps = connection.prepareStatement(query2);
        ps.setString(1, fan.getNombre());
        ps.setString(2, fan.getApellido());
        ps.setDate(3, fecha);
        ps.setString(4, fan.getGenero());
        ps.setInt(5, fan.getIdPais());
        ps.setString(6, fan.getFotoPerfil());
        ps.setInt(7, idFan);
        ingresarGenerosFan(fan.getGeneros(), idFan, connection);
        ps.executeUpdate();
        ps.close();
        connection.close();
    }
    
    /**
     * Un fanatico desea registrarse en el sistema MyFan
     * @param fan  fanatico que desea crear cuenta
     * @param connection conexion a la BD
     * @throws SQLException En caso de haber un error en la BD
     */
    public void registrarFan(Fan fan,Connection connection) throws SQLException{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date fecha = null;
        try {
            fecha = new java.sql.Date(format.parse(fan.getFechaNac()).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = "Insert into fans (username,password,nombre,apellido,fechaNac,fechaCreacion,genero,idPais,fotoPerfil) value (?,?,?,?,?,?,?,?,?);";
        String password = PasswordEncrypt.hashPassword(fan.getPassword());
        PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, fan.getUsername());
        ps.setString(2, password);
        ps.setString(3, fan.getNombre());
        ps.setString(4, fan.getApellido());
        ps.setDate(5, fecha);
        ps.setDouble(6, fan.getFechaCreacion());
        ps.setString(7, fan.getGenero());
        ps.setInt(8, fan.getIdPais());
        ps.setString(9, "http://localhost:8080/MyFanServer/uploads/"+fan.getFotoPerfil());
        ps.execute();
        
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        ingresarGenerosFan(fan.getGeneros(), rs.getInt(1), connection);
        
        ps.close();
        connection.close();
    }
    
    /**
     * Se encarga de ingresar los generos de un fanatico a la BD
     * @param generos  que se van ingresar a la BD
     * @param idFan  que se asignaran los generos musicales
     * @param connection conexion a la BD
     * @throws SQLException En caso de haber un error en la BD
     */
    private void ingresarGenerosFan(int[] generos, int idFan, Connection c)throws SQLException{
        if(generos == null)return;
        if(generos.length == 0) return;
        String delete = "delete from fangeneros where idFan = ?;";
        PreparedStatement ps1 = c.prepareStatement(delete);
        ps1.setInt(1, idFan);
        ps1.execute();
        
        String insert = "insert into fangeneros (idGenero, idFan) value (?,?);";
        for (int i = 0; i < generos.length; i++) {
            PreparedStatement ps2 = c.prepareStatement(insert);
            ps2.setInt(1, generos[i]);
            ps2.setInt(2, idFan);
            ps2.execute();
            ps2.close();
        }
        
        ps1.close();
        c.close();
        
    }
    
    /**
     * Se encarga de obtener los artistas que presenta un fanatico
     * @param idFan  fanatico que van a obtener los generos
     * @param connection conexion a la BD
     * @return Lista de los artistas del fan
     * @throws SQLException En caso de haber un error en la BD
     */
    public ArrayList<Banda> verMisArtistas(int idFan, Connection connection)throws SQLException{
        String query = "select b.idBanda, b.nombreBanda,b.fotoPerfil \n" +
                "from bandas b \n" +
                "join seguidos s \n" +
                "on s.idBanda = b.idBanda \n" +
                "where s.idFan = ? and b.activo=1;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idFan);
        ResultSet rs = ps.executeQuery();
        ArrayList<Banda> artistasList = new ArrayList<>();
        while(rs.next()){
            Banda banda = new Banda();
            banda.setIdBanda(rs.getInt("idBanda"));
            banda.setNombreBanda(rs.getString("nombreBanda"));
            banda.setFotoPerfil(rs.getString("fotoPerfil"));
            artistasList.add(banda);
        }
        connection.close();
        ps.close();
        return artistasList;
        
    }
    
    /**
     * Se encarga de obtener si un fanatico es seguidor de una banda o no
     * @param idFan  fanatico que se va a realizar la consulta
     * @param idBanda  que se va a verificar si es seguidor
     * @param connection conexion a la BD
     * @return boolean si es seguidor o no
     * @throws SQLException En caso de haber un error en la BD
     */
    public boolean esSeguidor (int idFan, int idBanda, Connection connection)throws SQLException{
        String query = "Select * from seguidos where idfan=? And idbanda=?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idFan);
        ps.setInt(2, idBanda);
        ResultSet rs = ps.executeQuery();
        boolean bandera = false;
        while(rs.next()){
            bandera = true;
        }
        connection.close();
        ps.close();
        
        return bandera;
    }
    
    /**
     * Se encarga de obtener la lista de artistas que cumplan con las condiciones
     * @param nombre  nombre de la banda que se desea buscar
     * @param pais  pais de la banda que se desea buscar
     * @param genero   genero de la banda que se desea buscar
     * @param connection conexion a la BD
     * @return Lista de resultados
     * @throws SQLException En caso de haber un error en la BD
     */
    public ArrayList<Banda> buscarArtistas(String nombre, String pais, String genero, Connection connection)throws SQLException{
        String query = "SELECT b.idBanda, b.nombreBanda\n"
                + "from bandas b\n"
                + "join (select g.nombre, bg.idBanda\n"
                + "		from generos g\n"
                + "		join bandageneros bg\n"
                + "		on bg.idGenero = g.idGenero) r\n"
                + "on r.idBanda = b.idBanda\n"
                + "join paises p\n" +
                "on p.idPais = b.idPais\n"
                + "where (b.nombreBanda like ? or p.pais like ? or r.nombre like ?) and b.activo=1\n"
                + "group by b.idBanda;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, "%" + nombre + "%" );
        ps.setString(2,"%" +  pais + "%");
        ps.setString(3, "%" + genero + "%");
        
        ResultSet rs = ps.executeQuery();
        ArrayList<Banda> bandas = new ArrayList<>();
        while(rs.next()){
            Banda banda = new Banda();
            banda.setIdBanda(rs.getInt("idBanda"));
            banda.setNombreBanda(rs.getString("nombreBanda"));
            bandas.add(banda);
        }
        
        connection.close();
        ps.close();
        return bandas;
    }
    
    /**
     * Se encarga de realizar una reseña a una banda
     * @param resenaBanda  que se le aplicara a la banda correspondiente
     * @param connection conexion a la BD
     * @throws SQLException En caso de haber un error en la BD
     */
    public void rateBand(Resena resenaBanda, Connection connection)throws SQLException{
        String delete = "delete from resenasbanda where idBanda = ? and idFan = ?";
        PreparedStatement ps2 = connection.prepareStatement(delete);
        ps2.setInt(1, resenaBanda.getId());
        ps2.setInt(2, resenaBanda.getIdFan());
        ps2.execute();
        String query = "insert into resenasbanda (idBanda,idFan,calificacion,comentario,fecha) values (?,?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, resenaBanda.getId());
        ps.setInt(2, resenaBanda.getIdFan());
        ps.setInt(3, resenaBanda.getCalificacion());
        ps.setString(4, resenaBanda.getComentario());
        ps.setDouble(5, resenaBanda.getFecha());
        ps.execute();
        ps.close();
        ps2.close();
        connection.close();
    }
    
    /**
     * Se encarga de realizar la reseña al disco correspondiente
     * @param resenaDisco  que se le aplicara a la banda
     * @param connection conexion a la BD
     * @throws SQLException En caso de haber un error en la BD
     */
    public void rateDisc(Resena resenaDisco, Connection connection)throws SQLException{
        String delete = "delete from resenasdisco where idDisco = ? and idFan = ?";
        PreparedStatement ps2 = connection.prepareStatement(delete);
        ps2.setInt(1, resenaDisco.getId());
        ps2.setInt(2, resenaDisco.getIdFan());
        ps2.execute();
        String query = "insert into resenasdisco (idDisco,idFan,calificacion,comentario,fecha) values (?,?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, resenaDisco.getId());
        ps.setInt(2, resenaDisco.getIdFan());
        ps.setInt(3, resenaDisco.getCalificacion());
        ps.setString(4, resenaDisco.getComentario());
        ps.setDouble(5, resenaDisco.getFecha());
        ps.execute();
        connection.close();
        ps2.close();
        ps.close();
    }
    
    /**
     * Se encarga de realizar la reseña al evento correspondiente
     * @param resenaEvento  que se le aplicara a la banda
     * @param connection conexion a la BD
     * @throws SQLException En caso de haber un error en la BD
     */
    public void rateEvent(Resena resenaEvento, Connection connection)throws SQLException{
        String delete = "delete from resenasconcierto where idEvento = ? and idFan = ?";
        PreparedStatement ps2 = connection.prepareStatement(delete);
        ps2.setInt(1, resenaEvento.getId());
        ps2.setInt(2, resenaEvento.getIdFan());
        ps2.execute();
        String query = "insert into resenasconcierto (idEvento,idFan,calificacion,comentario,fecha) value (?,?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, resenaEvento.getId());
        ps.setInt(2, resenaEvento.getIdFan());
        ps.setInt(3, resenaEvento.getCalificacion());
        ps.setString(4, resenaEvento.getComentario());
        ps.setDouble(5, resenaEvento.getFecha());
        ps.execute();
        ps.close();
        ps2.close();
        connection.close();
    }
    
    
    /**
     * Obtiene la informacion personal de un fanatico
     * @param idFan  fanatico que va a obtener la informacion
     * @param connection conexion a la BD
     * @return Informacion del fanatico
     * @throws SQLException En caso de haber un error en la BD
     */
    public Fan getFanInfo(int idFan, Connection connection)throws SQLException{
        String query = "select f.idFan,f.username, f.genero, floor(datediff(now(),f.fechaNac)/365) as age, count(s.idFan) as total, f.activo, p.pais,f.nombre,f.apellido,f.fechaNac,f.fotoPerfil \n" +
                "from fans f \n" +
                "join seguidos s \n" +
                "on s.idFan = f.idFan \n" +
                "join paises p\n" +
                "on p.idPais = f.idPais\n"+
                "where f.idFan = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idFan);
        ResultSet rs = ps.executeQuery();
        Fan fan = new Fan();
        while(rs.next()){
            fan.setIdFan(rs.getInt("idFan"));
            fan.setUsername(rs.getString("username"));
            fan.setGenero(rs.getString("genero"));
            fan.setEdad(rs.getInt("age"));
            fan.setSiguiendo(rs.getInt("total"));
            fan.setActivo(rs.getBoolean("activo"));
            fan.setPais(rs.getString("pais"));
            fan.setNombre(rs.getString("nombre"));
            fan.setApellido(rs.getString("apellido"));
            fan.setFechaNac(rs.getDate("fechaNac").toString());
            fan.setFotoPerfil(rs.getString("fotoPerfil"));
        }
        connection.close();
        ps.close();
        return fan;
        
    }
    
    /**
     * Se obtienen los generos de un fanatico
     * @param idFan  fanatico que va a obtener los generos
     * @param connection conexion a la BD
     * @return Lista de los generos de un fan
     * @throws SQLException En caso de haber un error en la BD
     */
    public ArrayList<Genero> getFanGeneros (int idFan, Connection connection)throws SQLException{
        String query = "select g.nombre,g.idGenero\n" +
                "from fangeneros bg\n" +
                "join fan b\n" +
                "on bg.idFan = b.idFan\n" +
                "join generos g\n" +
                "on g.idGenero = bg.idGenero\n" +
                "where bg.idFan = ?;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idFan);
        
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
    
    /**
     * Borra la calificacion de una banda
     * @param idFan  id del fan
     * @param id id de la banda
     * @param connection conexion a la BD
     * @throws SQLException En caso de haber un error en la BD
     */
    public void deleteRateBand (int idFan,int id, Connection connection)throws SQLException{
        String query = "delete from resenasbanda where idBanda = ? and idFan = ?;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.setInt(2, idFan);
        
        ps.execute();
        
        connection.close();
        ps.close();
    }
    
    /**
     * Borra la calificacion de un concierto
     * @param idFan  id del fan
     * @param id id del concierto
     * @param connection conexion a la BD
     * @throws SQLException En caso de haber un error en la BD
     */
    public void deleteRateConcert (int idFan,int id, Connection connection)throws SQLException{
        String query = "delete from resenasconcierto where idBanda = ? and idFan = ?;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.setInt(2, idFan);
        
        ps.execute();
        
        connection.close();
        ps.close();
    }
    
    
    /**
     * Borra la calificacion de un disco
     * @param idFan  id del fan
     * @param id id del disco
     * @param connection conexion a la BD
     * @throws SQLException En caso de haber un error en la BD
     */
    public void deleteRateDisc (int idFan,int id, Connection connection)throws SQLException{
        String query = "delete from resenasdisco where idBanda = ? and idFan = ?;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.setInt(2, idFan);
        
        ps.execute();
        
        connection.close();
        ps.close();
    }
    
}
