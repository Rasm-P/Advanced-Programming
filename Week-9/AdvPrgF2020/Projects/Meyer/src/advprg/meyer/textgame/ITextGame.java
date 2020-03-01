/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advprg.meyer.textgame;

import advprg.meyer.textio.ITextIO;

/**
 *
 * @author Tobias Grundtvig
 */
public interface ITextGame
{
    public int getNumberOfPlayers();
    public void startGame(ITextIO[] players);
}
