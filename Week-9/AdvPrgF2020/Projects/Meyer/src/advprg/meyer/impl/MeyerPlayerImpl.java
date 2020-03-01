package advprg.meyer.impl;

import advprg.meyer.MeyerPlayer;
import advprg.meyer.MeyerPlayerIO;
import advprg.meyer.MeyerRoll;
import advprg.meyer.MeyerRollFactory;

public class MeyerPlayerImpl implements MeyerPlayer
{
    private final MeyerPlayerIO playerIO;
    private final MeyerRollFactory rollFactory;
    private int lifes;

    public MeyerPlayerImpl(MeyerPlayerIO playerIO)
    {
        this.playerIO = playerIO;
        this.rollFactory = MeyerRollFactoryImpl.getInstance();
        this.lifes = 6;
    }

    @Override
    public String getName()
    {
        return playerIO.getName();
    }

    @Override
    public MeyerRoll makeFirstRoll(MeyerRoll actualRoll)
    {
        return playerIO.makeFirstRoll(actualRoll, rollFactory.getAllRolls(), lifes);
    }

    @Override
    public boolean flip(MeyerRoll claimedRoll)
    {
        return playerIO.flip(claimedRoll, lifes);
    }

    @Override
    public MeyerRoll makeRoll(MeyerRoll claimedRoll, MeyerRoll actualRoll)
    {
        return playerIO.makeRoll(claimedRoll, actualRoll, rollFactory.getRollsLargerThanOrEqualTo(claimedRoll), lifes);
    }

    @Override
    public void looseLife(int i)
    {
        lifes -= i;
        playerIO.looseLife(i, lifes);
    }

    @Override
    public int getLife()
    {
        return lifes;
    }

    @Override
    public void gameOver(int place)
    {
        playerIO.gameOver(place);
    }
}
