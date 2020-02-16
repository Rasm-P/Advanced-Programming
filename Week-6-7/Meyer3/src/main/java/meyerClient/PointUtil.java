/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meyerClient;

/**
 *
 * @author rasmu
 */
public class PointUtil {

    protected static int rollSum(int d1, int d2) {
        if (d1 > d2) {
            return d1 * 10 + d2;
        } else {
            return d2 * 10 + d1;
        }
    }

    protected static int turnPointsYesChoice(int turnRoll, int show, String name, String playerName, int d1, int d2) {
        if (turnRoll == show) {
            TextUI.rolledTheSame();
            return 0;
        } else if (turnRoll == 21) {
            TextUI.rolledMeyer(name, playerName);
            return 2;
        } else if (turnRoll == 31 && show != 21) {
            TextUI.rolledLilleMeyer(name, playerName);
            return 1;
        } else if ((d1 == d2 && String.valueOf(show).charAt(0) != String.valueOf(show).charAt(1) && show != 21 && show != 31) || (d1 == d2 && String.valueOf(show).charAt(0) == String.valueOf(show).charAt(1) && turnRoll > show)) {
            TextUI.rolledPair1(name, playerName);
            return 1;
        } else if (turnRoll > show && String.valueOf(show).charAt(0) != String.valueOf(show).charAt(1) && show != 21 && show != 31) {
            TextUI.rolledHigher(name, playerName);
            return 1;
        } else {
            TextUI.rolledLower(name, playerName, show);
            return -1;
        }
    }

    protected static int turnPointsNoChoice(int roll, int show, String name, String playerName) {
        if (show == roll) {
            TextUI.wasIncorrect(name);
            return -1;
        } else {
            TextUI.wasCorrect(name, playerName);
            return 1;
        }
    }

}
