/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meyerClient;

import interfaces.*;

/**
 *
 * @author rasmu
 */
public interface PlayerCtrl {

    public void takeTurn();

    public int turnChoice(String playerName, int show, int roll);
}
