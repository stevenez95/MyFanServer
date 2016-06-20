/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.dao;

import com.myfan.dto.Pais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Steven
 */
public class PaisesInfo {
    
    
    /**
     * Se obtienen los paises disponibles en el sistema
     * @param connection conexion a la BD
     * @return Lista de los paises disponibles
     * @throws SQLException En caso de haber un error en la BD
     */
    public ArrayList<Pais> getPaises(Connection connection)throws SQLException{
        String query = "select idPais, pais\n" +
                "from paises;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ArrayList<Pais> listaPaises = new ArrayList();
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Pais pais = new Pais();
            pais.setIdPais(rs.getInt("idPais"));
            pais.setPais(rs.getString("pais"));
            listaPaises.add(pais);
        }
        ps.close();
        connection.close();
        return listaPaises;
    }
    
}
