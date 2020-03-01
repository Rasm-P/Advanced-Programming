package advprg.meyer;

import advprg.meyer.impl.MeyerRollFactoryImpl;

import java.util.List;

public class RollExample
{
    public static void main(String[] args)
    {
        MeyerRollFactory factory = MeyerRollFactoryImpl.getInstance();
        System.out.println("All possible rolls in Meyer:");
        print(factory.getAllRolls());
        System.out.println("\n\nAll rolls better than or equal to 65: ");
        print(factory.getRollsLargerThanOrEqualTo(factory.getRollFromDice(5, 6)));
    }

    private static void print(List<MeyerRoll> meyerRollImpls)
    {
        meyerRollImpls.forEach(r -> System.out.println("Value: " + r.getValue() + " Dice: " + r.getDieA() + " " + r.getDieB() + " Name: " + r.getName()));
    }
}
