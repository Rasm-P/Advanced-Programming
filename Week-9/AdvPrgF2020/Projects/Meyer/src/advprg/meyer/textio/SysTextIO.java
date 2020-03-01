/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advprg.meyer.textio;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Tobias Grundtvig
 */
public class SysTextIO implements ITextIO
{

    private final static Scanner keyboard = new Scanner(System.in);
    
    @Override
    public void put(String str)
    {
        System.out.print(str);
    }

    @Override
    public String get()
    {
        System.out.print("\n>");
        return keyboard.nextLine();
    }

    @Override
    public void clear()
    {
        for(int i = 0; i < 100; ++i)
        {
            System.out.println("");
        }
    }

    @Override
    public void close() throws IOException
    {
        System.out.println("\n\n\nGoodbye!\n\n\n");
    }
    
}
