package com.course.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DateProviderTest {

    @Test(dataProvider = "data")
    public void testDateProvider(String name, int age){
        System.out.println("Name="+name+" Age="+age);
    }

    @DataProvider(name="data")
    public Object[][] providerData(){
        Object[][] o=new Object[][]{
                {"zhangsan",10},
                {"lishi",20},
                {"wangwu",30}
        };
        return o;
    }
    @Test(dataProvider = "methodData")
    public void test1( String name, int age){
        System.out.println("called test1 "+"name="+name+" age="+age);
    }

    @Test(dataProvider = "methodData")
    public void test2( String name, int age){
        System.out.println("called test2 "+"name="+name+" age="+age);
    }

    @DataProvider(name="methodData")
    public Object[][] methodDataTest(Method method){
        Object[][] result=null;

        if(method.getName().equals("test1")){
            result=new Object[][]{
                    {"zhangshan",20},
                    {"lishi",3}
            };

        }else if(method.getName().equals("test2")){
            result=new Object[][]{
                    {"wangwu",40},
                    {"zhaoliu",90}
            };

        }
        return result;
    }
}
