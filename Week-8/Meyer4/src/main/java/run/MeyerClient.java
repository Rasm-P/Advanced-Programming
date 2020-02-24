/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import impl.TextGameClient;

/**
 *
 * @author rasmu
 */
public class MeyerClient {

    private static final TextGameClient CT = TextGameClient.getInstance();

    public static void main(String[] args) {
        CT.run();
    }

}
