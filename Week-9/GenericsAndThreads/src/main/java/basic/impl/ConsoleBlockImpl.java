package basic.impl;

import basic.Output;


//Singleton
class ConsoleBlockImpl implements Output<String>
{
    private final static Output<String> instance = new ConsoleBlockImpl();

    public static Output<String> getInstance()
    {
        return instance;
    }

    private ConsoleBlockImpl()
    {
    }

    @Override
    public void put(String str)
    {
        System.out.println(str);
    }
}
