package blocks.basic.examples;

import blocks.basic.*;
import blocks.basic.impl.BasicBlockFactoryImpl;

public class IfExample
{
    public static void main(String[] args)
    {
        BasicBlockFactory factory = BasicBlockFactoryImpl.getInstance();

        Input<String> keyboard = factory.getKeyboardBlock();
        ThreadBlock<String> thread = factory.getThreadBlock();
        IfBlock<String> ifBlock = factory.getIfBlock(s -> s.toUpperCase().contains("FUCK"));
        Output<String> console = factory.getConsoleBlock();
        PushBlock<String, String> eraser = factory.getPushBlock(s -> "****");

        //Hook up blocks
        thread.setInput(keyboard);
        thread.setOutput(ifBlock);
        ifBlock.setTrueOutput(eraser);
        ifBlock.setFalseOutput(console);
        eraser.setOutput(console);

        thread.start();

    }
}
