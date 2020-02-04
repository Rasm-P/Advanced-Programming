package mayer;

/**
 *
 * @author Rasmus2
 */
public class GameCtrl {

    private final PlayerCtrl player1;
    private final PlayerCtrl player2;
    private final AI god;
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
            if (PVP) {
                player1.setHealth(player1.getHealth() - player2.turnChoice(player1.getName(), player1.getShowToOtherPlayer(), player1.getRoll()));
                player2.takeTurn();
                TextUI.println(player2.getName() + " says they rolled " + player2.getShowToOtherPlayer());
                player2.setHealth(player2.getHealth() - player1.turnChoice(player2.getName(), player2.getShowToOtherPlayer(), player2.getRoll()));
            } else {
                player1.setHealth(player1.getHealth() - god.turnChoice(player1.getName(), player1.getShowToOtherPlayer(), player1.getRoll()));
                god.takeTurn();
                TextUI.println(god.getName() + " says they rolled " + god.getShowToOtherPlayer());
                god.setHealth(god.getHealth() - player1.turnChoice(god.getName(), god.getShowToOtherPlayer(), god.getRoll()));
            }

            if (player1.getHealth() <= 0 || player2.getHealth() <= 0 || god.getHealth() <= 0) {
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
            TextUI.println(player1.getName() + " has " + player1.getHealth() + " health.");
            TextUI.println(player2.getName() + " has " + player2.getHealth() + " health.");
            if (player1.getHealth() > player2.getHealth()) {
                TextUI.println("The winner is " + player1.getName() + "!");
            } else if (player1.getHealth() < player2.getHealth()) {
                TextUI.println("The winner is " + player2.getName() + "!");
            } else {
                TextUI.println("It was a draw!");
            }
        } else {
            TextUI.println(player1.getName() + " has " + player1.getHealth() + " points.");
            TextUI.println(god.getName() + " has " + god.getHealth() + " points.");
            if (player1.getHealth() > god.getHealth()) {
                TextUI.println("The winner is " + player1.getName() + "!");
            } else if (player1.getHealth() < god.getHealth()) {
                TextUI.println("The winner is " + god.getName() + "!");
            } else {
                TextUI.println("It was a draw!");
            }
        }
    }

}
