package basic.examples;

import basic.Input;
import basic.Output;
import basic.BasicBlockFactory;
import basic.ThreadBlock;
import basic.PushBlock;
import basic.impl.BasicBlockFactoryImpl;

import java.util.function.Function;

public class SimpleKeyboardConsoleExample
{
    public static void main(String[] args)
    {
        BasicBlockFactory factory = BasicBlockFactoryImpl.getInstance();
        Input<String> keyboard = factory.getKeyboardBlock();

        Output<String> console = factory.getConsoleBlock();
        ThreadBlock<String> thread = factory.getThreadBlock();
        PushBlock<String, String> push = factory.getPushBlock(s -> s+", for fanden!");
        push.setOutput(console);
        thread.setInput(keyboard);
        thread.setOutput(push);
        thread.start();
    }
}
