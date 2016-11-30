package edu.uoc.pec2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Matias on 22/11/2016.
 */
public class Testing {
    public void test(Object ...xx){

            Method method = this.getClass().getMethods()[2];
            Class c = method.getParameterTypes()[0];
        System.out.println(c.toString());
        try {
            method.invoke(this,xx[2]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
    public void prueba(Integer x){
        System.out.println(x);
    }
    public static void main(String arg[]){
        Testing t=new Testing();
        t.test(2,4,5,2);
    }
}
