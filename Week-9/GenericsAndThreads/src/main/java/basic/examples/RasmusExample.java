/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic.examples;

import basic.BasicBlockFactory;
import basic.IfBlock;
import basic.Input;
import basic.Output;
import basic.PushBlock;
import basic.ThreadBlock;
import basic.impl.BasicBlockFactoryImpl;
import java.util.regex.Pattern;

/**
 *
 * @author rasmu
 */
public class RasmusExample {

    public static void main(String[] args) {
        BasicBlockFactory factory = BasicBlockFactoryImpl.getInstance();

//        Input<String> keyboard = factory.getKeyboardBlock();
//        Output<String> console = factory.getConsoleBlock();
//        ThreadBlock<String> thread = factory.getThreadBlock();
//        IfBlock<String> ifBlock = factory.getIfBlock(s -> s.length() > 6);
//        PushBlock<String, String> push = factory.getPushBlock(s -> s+", is longer than 6!");
//        
//        thread.setInput(keyboard);
//        thread.setOutput(ifBlock);
//        ifBlock.setTrueOutput(push);
//        ifBlock.setFalseOutput(console);
//        push.setOutput(console);

        Input<String> keyboard = factory.getKeyboardBlock();
        Output<String> console = factory.getConsoleBlock();
        ThreadBlock<String> thread = factory.getThreadBlock();
        IfBlock<String> ifBlock = factory.getIfBlock(s -> s.length() < 8 || !Pattern.compile("[A-Z ]").matcher(s).find()
                || !Pattern.compile("[a-z ]").matcher(s).find() || !Pattern.compile("[0-9 ]").matcher(s).find()
                || !Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE).matcher(s).find());
        PushBlock<String, String> pushWeak = factory.getPushBlock(s -> s + ", is a weak password!");
        PushBlock<String, String> pushStrong = factory.getPushBlock(s -> s + ", is a good strong password!");

        thread.setInput(keyboard);
        thread.setOutput(ifBlock);
        ifBlock.setTrueOutput(pushWeak);
        ifBlock.setFalseOutput(pushStrong);
        pushWeak.setOutput(console);
        pushStrong.setOutput(console);

        System.out.println("Please write a strong password:");
        thread.start();

    }
}
