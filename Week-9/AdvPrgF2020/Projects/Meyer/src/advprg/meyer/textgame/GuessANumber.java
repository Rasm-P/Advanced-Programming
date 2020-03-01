/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advprg.meyer.textgame;

import advprg.meyer.textio.ITextIO;

import java.util.ArrayList;

/**
 *
 * @author Tobias Grundtvig
 */
public class GuessANumber implements ITextGame
{

    @Override
    public int getNumberOfPlayers()
    {
        return 2;
    }

    @Override
    public void startGame(ITextIO[] players)
    {
        ArrayList<String> answers = new ArrayList<>();
        answers.add("Too low");
        answers.add("Too high");
        answers.add("Correct");
        players[0].put("\nGame started.");
        players[1].put("\nGame started.");
        players[0].put("\nPlease think of a number! Press ENTER when ready...");
        players[0].get();
        int answer = 0;
        int count = 0;
        while(answer != 2)
        {
            ++count;
            players[1].put("\n\nMake your guess number " + count + ": ");
            int guess = players[1].getInteger(0, 100);
            answer = players[0].select("\n\nHe guessed: " + guess + " is this: ", answers, "Please select: ");
            if(answer == 0)
            {
                players[1].put("\nYour guess was too low..");
            }
            else if(answer == 1)
            {
                players[1].put("\nYour guess was too high..");
            }
            else
            {
                players[0].put("\nYour secret number was guessed in " + count + " guesses.");
                players[1].put("\nHurray! You guessed the secret number in " + count + " guesses.");
            }
        }
    }
    
    public static void main(String[] args)
    {
        int port = 3737;
        TextGameServer server = new TextGameServer(new GuessANumber(), port);
        server.run();
    }
    
}
