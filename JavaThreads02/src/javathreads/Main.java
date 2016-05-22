/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javathreads;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author human
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File storage = new File("Storage");

        if (!storage.exists()) {
            storage.mkdir();
        }

        String[] urls = {
            "http://www.ex.ua/load/535647",
            "http://www.ex.ua/load/535644",
            "http://www.ex.ua/load/535655"
        };

        for (String url : urls) {
            try {
                DownloadTask dt = new DownloadTask(storage, new URL(url));

                new Thread(dt).start();
            } catch (MalformedURLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
