/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import meyer2.PlayerCtrlImpl;

/**
 *
 * @author rasmu
 */
public interface GameControle {

    public void playGame();

    public void managePoints(PlayerCtrlImpl currentPlayer, PlayerCtrlImpl nextPlayers);

    public void isOut(int number);

    public void showIntro();

    public void gameFinished();

    public void pageBreak(int sleep);

}
