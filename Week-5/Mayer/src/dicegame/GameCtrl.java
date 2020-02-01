/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dicegame;

/**
 *
 * @author Rasmus2
 */
public class GameCtrl {

    private PlayerCtrl player1;
    private PlayerCtrl player2;
    private AI god;
    private boolean PVP;

    public GameCtrl() {
        player1 = new PlayerCtrl();
        player2 = new PlayerCtrl();
        god = new AI();
    }

    public void playGame() {
        showIntro();
        TextUI.println("What's your name, player 1?");
        player1.init();
        if (PVP) {
            TextUI.println("What's your name, player 2?");
            player2.init();
        }
        while (true) {
            player1.takeTurn();
            TextUI.println(player1.getName() + " says they rolled " + player1.getShowToOtherPlayer());
            player1.setScore(player1.getScore() - player2.turnChoice(player1));
            if (PVP) {
                player2.takeTurn();
                TextUI.println(player2.getName() + " says they rolled " + player2.getShowToOtherPlayer());
                player2.setScore(player2.getScore() - player1.turnChoice(player2));
            } else {
                god.takeTurn();
            }

            if (player1.getScore() == 0 || player2.getScore() == 0 || god.getScore() == 0) {
                gameFinished();
                break;
            }
        }
    }

    private void showIntro() {
        TextUI.println("Welcome to the game");
        String[] choices = {"PVP", "AI"};
        int answer = TextUI.choice(choices);
        if (answer == 0) {
            PVP = true;
        } else {
            PVP = false;
        }
    }

    private void gameFinished() {
        TextUI.println("Game over!");

        if (PVP) {
            TextUI.println(player1.getName() + " has " + player1.getScore() + " health.");
            TextUI.println(player2.getName() + " has " + player2.getScore() + " health.");
            if (player1.getScore() > player2.getScore()) {
                TextUI.println("The winner is " + player1.getName() + "!");
            } else if (player1.getScore() < player2.getScore()) {
                TextUI.println("The winner is " + player2.getName() + "!");
            } else {
                TextUI.println("It was a draw!");
            }
        } else {
            TextUI.println(player1.getName() + " has " + player1.getScore() + " points.");
            TextUI.println(god.getName() + " has " + god.getScore() + " points.");
            if (player1.getScore() > god.getScore()) {
                TextUI.println("The winner is " + player1.getName() + "!");
            } else if (player1.getScore() < god.getScore()) {
                TextUI.println("The winner is " + god.getName() + "!");
            } else {
                TextUI.println("It was a draw!");
            }
        }
    }

}
