/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advprg.meyer.textio;

import java.io.Closeable;

/**
 *
 * @author Tobias Grundtvig
 */
public interface ITextIO extends Closeable
{
    public void put(String s);
    public void clear();
    public String get();
    //default helper methods
    public default boolean getYesOrNo()
    {
        while(true)
        {

            String input = get().toLowerCase();
            if(input.equals("y")|| input.equals("yes"))
            {
                return true;
            }
            if(input.equals("n")|| input.equals("no"))
            {
                return false;
            }
            put("\nYou must enter (y)es or (n)o: ");
        }
    }
    public default int getInteger()
    {
        while(true)
        {

            String input = get();
            try
            {
                int res = Integer.parseInt(input);
                return res;
            }
            catch(NumberFormatException exp)
            {
                put("\nYou must enter an integer: ");
            }
        }
    }

    public default int getInteger(int min, int max)
    {
        while(true)
        {
            int res = getInteger();
            if(res >= min && res <= max)
            {
                return res;
            }
            else
            {
                put("\nYou must enter an integer between " + min + " and " + max + ", both inclusive.");
            }
        }
    }

    public default int select(String header, Iterable<String> choices, String footer)
    {
        put(header);
        int count = 0;
        for(String s : choices)
        {
            put("\n" + (++count) + " - " + s);
        }
        put("\n" + footer);
        return getInteger(1, count)-1;
    }
}
