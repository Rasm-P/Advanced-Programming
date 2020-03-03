/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadsAndSynchronized;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rasmu
 */
public class syncThread implements Runnable {

    private final int number;
    private final Thread workerThread;
    private final syncBufferImpl2 bufferImpl;

    public syncThread(syncBufferImpl2 buffer,int i) {
        this.bufferImpl = buffer;
        this.number = i;
        workerThread = new Thread(this);
        workerThread.start();
    }

    @Override
    public void run() {
        try {
            bufferImpl.put("Thread "+number);
        } catch (InterruptedException ex) {
            Logger.getLogger(syncThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
