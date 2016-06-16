/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.connection;

import com.google.gson.Gson;
import com.myfan.dto.Banda;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jboss.classfilewriter.constpool.StringEntry;

/**
 *
 * @author Steven
 */
public class MyFestConnection {
    
    public boolean enviarArtistas (Banda banda){
    try {
            HttpPost post = new HttpPost(IConstantes.MYFEST_URL+"myfan");
            Gson g = new Gson();
            String json = g.toJson(banda);
            System.out.println(json);
            DefaultHttpClient client = new DefaultHttpClient();
            
            StringEntity input = new StringEntity(json);
            input.setContentType("application/json");
            
            post.setEntity(input);
            HttpResponse response = (HttpResponse) client.execute(post);
            
            System.out.println(response.getStatusLine().getStatusCode());

            if(response.getStatusLine().getStatusCode()==200)
                return true;
            else
                return false;
            
        } catch (UnsupportedEncodingException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }    
    }
    
   public boolean actualizarArtistas (Banda banda, int idBanda){
    try {
            HttpPut put = new HttpPut(IConstantes.MYFEST_URL+"myfest/"+idBanda);
            Gson g = new Gson();
            String json = g.toJson(banda);
            DefaultHttpClient client = new DefaultHttpClient();
            
            StringEntity entity = new StringEntity(json);
            entity.setContentType("application/json");
            put.setEntity(entity);
            HttpResponse response = (HttpResponse) client.execute(put);       
            System.out.println(response.getStatusLine().getStatusCode());

            if(response.getStatusLine().getStatusCode()==200)
                return true;
            else
                return false;
            
        } catch (UnsupportedEncodingException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }    
    }
    
    
}
