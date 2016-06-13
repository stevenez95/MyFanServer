/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.connection;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;


/**
 *
 * @author Steven
 */
public class ImageSaver {

    public ImageSaver() {
    }
    
    public String saveImage(String base64Img){
        FileOutputStream imageOutFile = null;
        try {
            String imageDataBytes = base64Img.substring(base64Img.indexOf(",")+1);
            System.out.println(imageDataBytes);
            byte[] imageByteArray = Base64.decodeBase64(imageDataBytes);
            String outFileName = new SimpleDateFormat("ddMMyy-hhmmss-SSS'.jpg'").format(new Date());
            imageOutFile = new FileOutputStream("C:\\Test\\uploads\\"+outFileName);
            imageOutFile.write(imageByteArray);
            imageOutFile.close();
            System.out.println("finaly");
            return outFileName;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageSaver.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(ImageSaver.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                imageOutFile.close();
            } catch (IOException ex) {
                Logger.getLogger(ImageSaver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
}
