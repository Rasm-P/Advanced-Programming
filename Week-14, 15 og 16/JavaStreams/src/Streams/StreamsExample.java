/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author rasmu
 */
public class StreamsExample {
    
    public static void main(String[] args) throws IOException {
        
        String[] number = {"10", "11", "12", "13", "14", "15"};
        int even = Arrays.stream(number).map(x -> Integer.parseInt(x)).filter(x -> x % 2 == 1).reduce(0, (ans, i) -> ans + i);
        System.out.println(even);
        
        BufferedReader buffReader = Files.newBufferedReader(Paths.get("C:\\Users\\rasmu\\Desktop\\Advanced-Programming\\Week-14\\Navne.txt"), StandardCharsets.UTF_8);        
        List result1 = new ArrayList<>();        
        List result2 = new ArrayList<>();
        buffReader.lines().filter(s -> s.length() > 6).filter(s -> s.equals(s.toUpperCase()) || s.charAt(0) == "A".charAt(0) || s.charAt(0) == "E".charAt(0) || 
                s.charAt(0) == "C".charAt(0)).forEach((x) -> ((x.charAt(0) == "A".charAt(0) || x.charAt(0) == "E".charAt(0) || x.charAt(0) == "C".charAt(0)) ? result1 : result2).add(x));
        System.out.println(result1);
        System.out.println(result2);
    }
    
}
