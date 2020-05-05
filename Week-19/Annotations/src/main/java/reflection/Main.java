package reflection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rasmu
 */
public class Main
{
    public static void main(String[] args)
    {
        Person t = new Person("Tobias", 46);
        Inspector insp = new Inspector();

        insp.inspect(t);
    }
}
