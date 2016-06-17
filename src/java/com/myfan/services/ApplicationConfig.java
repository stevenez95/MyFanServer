/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.services;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Steven
 */
@javax.ws.rs.ApplicationPath("api/v1")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.myfan.filters.CORSFilter.class);
        resources.add(com.myfan.filters.TokenFilter.class);
        resources.add(com.myfan.services.AuthService.class);
        resources.add(com.myfan.services.BandService.class);
        resources.add(com.myfan.services.DiscService.class);
        resources.add(com.myfan.services.EventService.class);
        resources.add(com.myfan.services.FanService.class);
        resources.add(com.myfan.services.GenreService.class);
        resources.add(com.myfan.services.MyFestService.class);
        resources.add(com.myfan.services.NewsService.class);
        resources.add(com.myfan.services.PaisService.class);
    }
    
}
