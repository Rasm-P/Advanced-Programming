package advprg.meyer.impl;


//  Roll    Value   name:
//  3 2     1       32
//  4 1     2       41
//  4 2     3       42
//  4 3     4       43
//  5 1     5       51
//  5 2     6       52
//  5 3     7       53
//  5 4     8       54
//  6 1     9       61
//  6 2     10      62
//  6 3     11      63
//  6 4     12      64
//  6 5     13      65
//  1 1     14      Par 1
//  2 2     15      Par 2
//  3 3     16      Par 3
//  4 4     17      Par 4
//  5 5     18      Par 5
//  6 6     19      Par 6
//  3 1     20      Lille Meyer
//  2 1     21      Meyer


import advprg.meyer.MeyerRoll;

import java.util.Objects;

class MeyerRollImpl implements MeyerRoll
{
    private final int dieA;
    private final int dieB;
    private final int value;
    private final String name;

    public MeyerRollImpl(int dieA, int dieB)
    {
        if(dieA < 1 || dieA > 6 || dieB < 1 || dieB > 6)
        {
            throw new IllegalArgumentException("Dice must be 1 to 6");
        }
        if (dieA > dieB)
        {
            this.dieA = dieA;
            this.dieB = dieB;
        } else
        {
            this.dieA = dieB;
            this.dieB = dieA;
        }
        switch (this.dieA)
        {
            case 1:
                value = 14;
                name = "Par 1";
                break;
            case 2:
                if (this.dieB == 1)
                {
                    value = 21;
                    name = "Meyer";
                } else
                {
                    value = 15;
                    name = "Par 2";
                }
                break;
            case 3:
                switch (this.dieB)
                {
                    case 1:
                        value = 20;
                        name = "Lille Meyer";
                        break;
                    case 2:
                        value = 1;
                        name = "32";
                        break;
                    default:
                        value = 16;
                        name = "Par 3";
                        break;
                }
                break;
            case 4:
                switch (this.dieB)
                {
                    case 1:
                        value = 2;
                        name = "41";
                        break;
                    case 2:
                        value = 3;
                        name = "42";
                        break;
                    case 3:
                        value = 4;
                        name = "43";
                        break;
                    default:
                        value = 17;
                        name = "Par 4";
                        break;
                }
                break;
            case 5:
                switch (this.dieB)
                {
                    case 1:
                        value = 5;
                        name = "51";
                        break;
                    case 2:
                        value = 6;
                        name = "52";
                        break;
                    case 3:
                        value = 7;
                        name = "53";
                        break;
                    case 4:
                        value = 8;
                        name = "54";
                        break;
                    default:
                        value = 18;
                        name = "Par 5";
                        break;
                }
                break;
            default:
                switch (this.dieB)
                {
                    case 1:
                        value = 9;
                        name = "61";
                        break;
                    case 2:
                        value = 10;
                        name = "62";
                        break;
                    case 3:
                        value = 11;
                        name = "63";
                        break;
                    case 4:
                        value = 12;
                        name = "64";
                        break;
                    case 5:
                        value = 13;
                        name = "65";
                        break;
                    default:
                        value = 19;
                        name = "Par 6";
                        break;
                }
                break;
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeyerRollImpl meyerRollImpl = (MeyerRollImpl) o;
        return value == meyerRollImpl.value;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(value);
    }

    public int getDieA()
    {
        return dieA;
    }

    public int getDieB()
    {
        return dieB;
    }

    @Override
    public int getValue()
    {
        return value;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name;
    }

}
