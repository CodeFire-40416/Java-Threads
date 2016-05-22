/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javathreads;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author human
 */
public class MyRun implements Runnable {
    
    private String name;

    public MyRun(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(name + ": " + i);
//            try {
//                Thread.sleep(1); // ms
//            } catch (InterruptedException ex) {
//                Logger.getLogger(MyRun.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }
    
}
