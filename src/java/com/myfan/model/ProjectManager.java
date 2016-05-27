/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.model;

import com.myfan.connection.DataBaseConnect;
import com.myfan.connection.Project;
import com.myfan.dto.Banda;
import com.myfan.dto.Fan;

/**
 *
 * @author Daniel
 */
public class ProjectManager {
    
    public void registrarFan(Fan fan) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        Project project = new Project();
        project.registrarFan(fan, database.getConnection());
    }
    
     public void registrarBanda(Banda banda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        Project project = new Project();
        project.registrarBanda(banda, database.getConnection());
    }
     
     public void login (String username, String password) throws Exception{
         DataBaseConnect database = new DataBaseConnect();
        Project project = new Project();
        project.login(username,password, database.getConnection());
     }
}
