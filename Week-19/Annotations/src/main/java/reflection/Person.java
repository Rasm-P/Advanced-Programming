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
public class Person
{
    private String name;
    private int age;

    public Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    @MyAnnotation
    public String getName()
    {
        return name;
    }

    @MyAnnotation
    public void setName(String name)
    {
        this.name = name;
    }

    @MyAnnotation
    public int getAge()
    {
        return age;
    }

    @MyAnnotation
    public void setAge(int age)
    {
        this.age = age;
    }

    @Override
    public String toString()
    {
        return name + " (" + age + ")";
    }
}