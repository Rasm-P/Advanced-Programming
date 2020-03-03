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
//        Scanner scanner = new Scanner(System.in);
//        syncBufferImpl1<String> sync = new syncBufferImpl1();
//        System.out.println("Write somthing!");
//        String line = scanner.nextLine();
//        
//        sync.put(line);
//        String outLine = sync.get();
//        System.out.println(outLine);

        Scanner scanner = new Scanner(System.in);
        syncBufferImpl2<String> sync = new syncBufferImpl2(3);
        System.out.println("Write somthing!");
        String line = scanner.nextLine();

        syncThread t1 = new syncThread(sync, 1);
        syncThread t2 = new syncThread(sync, 2);
        syncThread t3 = new syncThread(sync, 3);
        syncThread t4 = new syncThread(sync, 4);

        sync.put(line);
        for (int i = 0; i < 4; i++) {
            System.out.println(sync.get());
        }

    }
}
