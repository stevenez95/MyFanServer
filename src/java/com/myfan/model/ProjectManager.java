/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.model;

import com.myfan.connection.DataBaseConnect;
import com.myfan.data.*;
import com.myfan.dto.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class ProjectManager {
    
    /*** AUTENTICACION ***/
    
    /*fan*/
    
    public void registrarFan(Fan fan) throws SQLException{
        try {
            DataBaseConnect database = new DataBaseConnect();
            FanInfo fanInfo = new FanInfo();
            fanInfo.registrarFan(fan, database.getConnection());
        } catch (Exception ex) {
            Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void seguirBanda(int idFan , int idBanda){}
    
    public void dejarSeguirBanda(int idFan,  int idBanda){}
    
    public void desactivarFan(int idFan){}
    
    public void actualizarFan(Fan fan){}
    
    public ArrayList<Banda> verMisArtistas(int idFan){return null;}
    
    public void buscarArtistas(String nombre, String pais, String genero){}
    
    public void rateBand(ResenaBanda resenaBanda){}
    
    public ArrayList<Evento> getEventosFan(int idFan){return null;}
    
    public void rateEvent(ResenaConcierto resenaConcierto){}
    
    public ArrayList<Noticia> getNoticiasFan(int idFan){return null;}
    
    /*banda*/
    
    public void registrarBanda(Banda banda) throws SQLException{
        try {
            DataBaseConnect database = new DataBaseConnect();
            BandInfo bandInfo = new BandInfo();
            bandInfo.registrarBanda(banda, database.getConnection());
        } catch (Exception ex) {
            Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizarBanda(Banda banda){}
    
    public void desactivarBanda(int idBanda){}
    
    public int getCantidadSeguidores(int idBanda){return 0;}
    
    public ArrayList<ResenaBanda> getBandComments(int idBanda){return null;}
    
    public int getBandRate(int idBanda){return 0;}
    
    public void crearEvento(Evento evento){}
    
    public ArrayList<Evento> getEventosBanda(int idBanda){return null;}
    
    public void cancelEvent(int idEvento){}
    
    public ArrayList<ResenaConcierto> getEventComments(int idEvento){return null;}
    
    public int getEventRate(int idEvento){return 0;}
    
    public void crearNoticia(Noticia noticia){}
    
    public ArrayList<Noticia> getNoticiasBanda(int idBanda){return null;}
    
    public void deleteNews(int idNoticia){}
    
    /******/
    public String login (String username, String password) throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        UserInfo project = new UserInfo();
        return project.login(username,password, database.getConnection());
    }
    
    public ArrayList<Genero> verGeneros(){return null;}
    
    public void editarGenero(String nombre,int idGenero){}
    
    public void borrarGenero(int idGenero){}
    
    public void crearGenero(Genero genero){}
    
    /*discos*/
    
    public void createDisc(Discografia discografia) throws SQLException{
    }
    
    public ArrayList<Discografia> getDiscs(int idBanda)throws SQLException{
        return null;
    }
    
    public ArrayList<Discografia> getDiscInfo(int idDisco)throws SQLException{
        return null;
        
    }
    
    public void editDisc(Discografia discografia, int idDisco)throws SQLException{
    }
    
    public void deleteDisc(int idDisco)throws SQLException{
    }
    
    public void createSong(Cancion cancion){   }
    
    public ArrayList<Cancion> getSongs(int idDisco)throws SQLException{
        return null;
    }
    
    public void editSong(Cancion cancion,int idCancion)throws SQLException{
        
    }
    
    public void deleteSong(int idCancion)throws SQLException{
        
    }
    
    public void rateDisc(ResenaDisco resenaDisco)throws SQLException{
        
    }
    
    public ArrayList<ResenaDisco> getDiscComments(int idDisco)throws SQLException{
        return null;
    }
    
    public int getDiscRate(int  idDisco)throws SQLException{
        return 0;
    }
    
    
    
}
