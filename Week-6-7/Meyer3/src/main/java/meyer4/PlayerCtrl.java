/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meyer4;

/**
 *
 * @author rasmu
 */
public interface PlayerCtrl {

    public void takeTurn();

    public int turnChoice(String playerName, int show, int roll);

    public void isOut();

    public void gameWon();
}
