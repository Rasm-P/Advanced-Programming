/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadsAndSynchronized;

import java.util.Scanner;

/**
 *
 * @author rasmu
 */
public class main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        
        syncBufferImpl1<String> sync = new syncBufferImpl1();
        System.out.println("Write somthing!");
        String line = scanner.nextLine();
        
        sync.put(line);
        String outLine = sync.get();
        System.out.println(outLine);
    }
}
