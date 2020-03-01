package advprg.meyer.impl;

import advprg.meyer.MeyerPlayerIO;
import advprg.meyer.MeyerRoll;
import advprg.meyer.textio.ITextIO;

import java.util.ArrayList;
import java.util.List;

public class MeyerPlayerIOImpl implements MeyerPlayerIO
{
    private final ITextIO textIO;
    private String name;

    public MeyerPlayerIOImpl(ITextIO textIO)
    {
        this.textIO = textIO;
        this.name = null;
    }

    @Override
    public String getName()
    {
        if(name == null)
        {
            textIO.put("Please enter your name: ");
            name = textIO.get();
        }
        return name;
    }

    @Override
    public MeyerRoll makeFirstRoll(MeyerRoll actualRoll, List<MeyerRoll> choices, int lifesLeft)
    {
        textIO.put("Lives left: " + lifesLeft + "\n");
        textIO.put("This is the inital roll, you have rolled: " + actualRoll.getName() + "\n");
        return claimRoll(choices);
    }

    @Override
    public boolean flip(MeyerRoll claimedRoll, int lifesLeft)
    {
        textIO.put("Lives left: " + lifesLeft + "\n");
        if(claimedRoll == null)
        {
            textIO.put("Previous player claims to have rolled same or above.");
        }
        else
        {
            textIO.put("Previous player claims to have rolled: " + claimedRoll.getName());
        }
        textIO.put(". Do you want to lift and see if it is a lie? :");
        return textIO.getYesOrNo();
    }

    @Override
    public MeyerRoll makeRoll(MeyerRoll claimedRoll, MeyerRoll actualRoll, List<MeyerRoll> choices, int lifesLeft)
    {
        textIO.put("You have rolled: " + actualRoll.getName() + "\n");
        return claimRoll(choices);
    }

    @Override
    public void looseLife(int i, int lifesLeft)
    {
        textIO.put("You have lost: " + i + " life(s), you have " + lifesLeft + " left.\n");
    }

    @Override
    public void gameOver(int place)
    {
        textIO.put("You have lost all your lifes, the game is over for you. Your place is " + place + ".\n");
    }

    private MeyerRoll claimRoll(List<MeyerRoll> choices)
    {
        List<String> options = new ArrayList<>();
        for(MeyerRoll roll : choices)
        {
            options.add(roll.getName());
        }
        options.add("Same or above (do a reroll)");
        int i = textIO.select("What is your claim?", options, "Enter your choice (1-" + options.size() + ")");
        if(i == options.size()-1)
        {
            return null;
        }
        return choices.get(i);
    }
}
