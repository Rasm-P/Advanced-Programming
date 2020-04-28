/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package together;

import java.util.Scanner;

/**
 *
 * @author rasmu
 */
class ITextIO {
    private Scanner SCANNER = new Scanner(System.in);
    
    public String get() {
        return SCANNER.nextLine();
    }

    public void put(String string) {
        System.out.println(string);
    }
    
}
