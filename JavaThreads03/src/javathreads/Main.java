/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javathreads;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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
        
        List<String> urls = new ArrayList<>();
        
        try (Scanner playListScanner = new Scanner(new URL("http://www.ex.ua/playlist/148363.m3u").openStream())) {
            while (playListScanner.hasNextLine()) {
                urls.add(playListScanner.nextLine());
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        for (String url : urls) {
            try {
                DownloadTask dt = new DownloadTask(storage, new URL(url));

                threadPool.execute(dt);
//                new Thread(dt).start();
            } catch (MalformedURLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Soft stoping...
        threadPool.shutdown();
        
        if (!threadPool.isTerminated()) {
            System.out.println("for cancel downloading press any key");
            try {
                System.in.read();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("CANCELING...");
            
            try {
                // Wait 1 second for tasks.
                if (!threadPool.awaitTermination(1, TimeUnit.SECONDS)) {
                    // Hard stop...
                    List<Runnable> shutdownNow = threadPool.shutdownNow();
                    
                    for (Runnable runnable : shutdownNow) {
                        DownloadTask dt = (DownloadTask)runnable;
                        
                        System.out.println("Not downloaded: " + dt.getLinkedFile());
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
