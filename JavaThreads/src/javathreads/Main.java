/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javathreads;

/**
 *
 * @author human
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        MyRun myRun1 = new MyRun("One");
        MyRun myRun2 = new MyRun("Two");
        
//        myRun1.run();
//        myRun2.run();

        Thread t1 = new Thread(myRun1);
        Thread t2 = new Thread(myRun2);
        
        // Intialize & start
        t1.start();
        t2.start();
        
        
    }
    
}
