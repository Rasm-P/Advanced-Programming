package advprg.meyer;

import java.util.List;

public interface MeyerPlayer
{
    public String getName();

    public MeyerRoll makeFirstRoll(MeyerRoll roll);

    public boolean flip(MeyerRoll claimedRoll);

    public MeyerRoll makeRoll(MeyerRoll claimedRoll, MeyerRoll actualRoll);

    public void looseLife(int i);

    public int getLife();

    public void gameOver(int place);
}
