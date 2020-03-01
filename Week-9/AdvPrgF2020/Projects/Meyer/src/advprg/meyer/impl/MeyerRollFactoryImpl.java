package advprg.meyer.impl;

import advprg.meyer.MeyerRoll;
import advprg.meyer.MeyerRollFactory;

import java.util.*;

public class MeyerRollFactoryImpl implements MeyerRollFactory
{
    private static MeyerRollFactoryImpl instance = null;
    private static final ArrayList<MeyerRoll> allMeyerRolls;
    private static final List<MeyerRoll> unmodifiableList;

    static
    {
        allMeyerRolls = new ArrayList<>(21);
        for(int dieA = 1; dieA <= 6; ++dieA)
        {
            for(int dieB = dieA; dieB <= 6; ++dieB)
            {
                allMeyerRolls.add(new MeyerRollImpl(dieA, dieB));
            }
        }
        allMeyerRolls.sort(Comparator.naturalOrder());
        unmodifiableList = Collections.unmodifiableList(allMeyerRolls);
    }

    public static MeyerRollFactory getInstance()
    {
        if(instance == null)
        {
            instance = new MeyerRollFactoryImpl();
        }
        return instance;
    }

    private MeyerRollFactoryImpl() {}

    @Override
    public MeyerRoll getRollFromDice(int dieA, int dieB)
    {
        if(dieA < 1 || dieA > 6 || dieB < 1 || dieB > 6)
        {
            throw new IllegalArgumentException("Dice must be in range 1 to 6");
        }
        if(dieB > dieA)
        {
            int tmp = dieA;
            dieA = dieB;
            dieB = tmp;
        }
        for (MeyerRoll roll : allMeyerRolls)
        {
            if(dieA == roll.getDieA() && dieB == roll.getDieB())
            {
                return roll;
            }
        }
        throw new Error("Roll did not exist in the list");
    }

    @Override
    public MeyerRoll getRollFromValue(int value)
    {
        if(value < 1 || value > 21)
        {
            throw new IllegalArgumentException("Value must be in range 1 to 21");
        }
        return allMeyerRolls.get(value-1);
    }

    @Override
    public List<MeyerRoll> getAllRolls()
    {
        return allMeyerRolls;
    }

    @Override
    public List<MeyerRoll> getRollsLargerThanOrEqualTo(MeyerRoll roll)
    {
        int i = 0;
        MeyerRoll cur = allMeyerRolls.get(i);
        while(cur.compareTo(roll) < 0)
        {
            ++i;
            cur = allMeyerRolls.get(i);
        }
        List<MeyerRoll> res = allMeyerRolls.subList(i, allMeyerRolls.size());
        return Collections.unmodifiableList(res);
    }
}
