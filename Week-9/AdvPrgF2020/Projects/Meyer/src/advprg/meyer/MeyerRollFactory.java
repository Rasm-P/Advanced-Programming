package advprg.meyer;

import java.util.List;

public interface MeyerRollFactory
{
    public MeyerRoll getRollFromDice(int dieA, int dieB);
    public MeyerRoll getRollFromValue(int value);
    public List<MeyerRoll> getAllRolls();
    public List<MeyerRoll> getRollsLargerThanOrEqualTo(MeyerRoll roll);
}
