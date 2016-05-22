/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javathreads;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author human
 */
public class DownloadTask implements Runnable {
    
    private File storage;
    private URL linkedFile;

    public DownloadTask(File storage, URL linkedFile) {
        this.storage = storage;
        this.linkedFile = linkedFile;
    }

    public URL getLinkedFile() {
        return linkedFile;
    }

    @Override
    public void run() {
        System.out.println("Download: " + linkedFile);
        
        try {
            URLConnection conn = linkedFile.openConnection();
            
            // Wait for redirect's.
            conn.getContentType();
            
            // Get real file name
            String filename = new File(URLDecoder.decode(conn.getURL().getFile(), "ISO-8859-1")).getName();
            
            long copy = Files.copy(new BufferedInputStream(conn.getInputStream()), new File(storage, filename).toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Downloaded: " + copy + " bytes");
        } catch (IOException ex) {
            Logger.getLogger(DownloadTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
