/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.model;

import com.myfan.connection.DataBaseConnect;
import com.myfan.data.BandInfo;
import com.myfan.data.FanInfo;
import com.myfan.data.UserInfo;
import com.myfan.dto.Banda;
import com.myfan.dto.Fan;
import java.sql.SQLException;

/**
 *
 * @author Daniel
 */
public class ProjectManager {
    
    public void registrarFan(Fan fan) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        FanInfo fanInfo = new FanInfo();
        fanInfo.registrarFan(fan, database.getConnection());
    }
    
    public void registrarBanda(Banda banda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo = new BandInfo();
        bandInfo.registrarBanda(banda, database.getConnection());
    }
    
    public String login (String username, String password) throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        UserInfo project = new UserInfo();
        return project.login(username,password, database.getConnection());
    }
}
