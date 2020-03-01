package advprg.meyer;

import java.util.List;

public interface MeyerPlayerIO
{
    public String getName();
    public MeyerRoll makeFirstRoll(MeyerRoll actualRoll, List<MeyerRoll> choices, int lifesLeft);
    public boolean flip(MeyerRoll claimedRoll, int lifesLeft);
    //Return null to indicate a reroll (same or above);
    public MeyerRoll makeRoll(MeyerRoll claimedRoll, MeyerRoll actualRoll, List<MeyerRoll> choices, int lifesLeft);
    public void looseLife(int i, int lifesLeft);
    public void gameOver(int place);
}
