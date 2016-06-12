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

/**
 *
 * @author Steven
 */
public class MyFestConnection {
    
    public boolean enviarArtistas (Banda banda){
    try {
            String url ="http://172.19.13.148:13291/Odyssey/webresources/TrackService/UploadTrack";
            HttpPost post = new HttpPost(url);
            Gson g = new Gson();
            String json = g.toJson(banda);
            DefaultHttpClient client = new DefaultHttpClient();
            
            post.setEntity(new StringEntity(json));
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
    
   public boolean actualizarArtistas (Banda banda){
    try {
            String url ="http://172.19.13.148:13291/Odyssey/webresources/TrackService/UploadTrack";
            HttpPut put = new HttpPut(url);
            Gson g = new Gson();
            String json = g.toJson(banda);
            DefaultHttpClient client = new DefaultHttpClient();
            
            put.setEntity(new StringEntity(json));
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
