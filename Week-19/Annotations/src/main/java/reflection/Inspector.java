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
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Inspector
{
    public void inspect(Object obj)
    {
        Class c = obj.getClass();
        System.out.println("Simple name: " + c.getSimpleName());
        System.out.println("Name: " + c.getName());

        Method[] methods = c.getMethods();
        int i = 0;
        for(Method m : methods)
        {
            ++i;
            System.out.println("Method " + i + " = " + m.getName());
            Annotation[] annotations = m.getAnnotations();
            for(Annotation a : annotations)
            {
                System.out.println("    " + a.toString());
                if("@dk.cphbusiness.advprg.annotations.MyAnnotation()".equals(a.toString()))
                {
                    System.out.println("We have a candidate...");
                    if("java.lang.String".equals(m.getReturnType().getName()))
                    {
                        Parameter[] parameters = m.getParameters();
                        for(Parameter p : parameters)
                        {
                            if(!"java.lang.String".equals(p.getType().getName()))
                            {
                                System.out.println("Wrong parameter type: " + p.getType().getName());
                                break;
                            }
                        }
                        System.out.println("Perfect, we have a match!!!");
                    }
                    else
                    {
                        System.out.println("Wrong return type: " + m.getReturnType().getName());
                    }
                }
            }
        }
    }
}