package advprg.meyer;

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

public interface MeyerRoll extends Comparable<MeyerRoll>
{
    public int getDieA();
    public int getDieB();
    public int getValue();
    public String getName();

    @Override
    default public int compareTo(MeyerRoll o)
    {
        return getValue() - o.getValue();
    }
}
