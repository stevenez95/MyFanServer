/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Steven
 */
public class DataBaseConnect {
    public Connection getConnection() throws Exception
    {
        try
        {
            String connectionURL = "jdbc:mysql://localhost:3306/MyFanDB";
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //connection =  DriverManager.getConnection(connectionURL, "root", "chacon");
           connection =  DriverManager.getConnection(connectionURL, "root", "password");
            return connection;
        }
        catch (SQLException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
