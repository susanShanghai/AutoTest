package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnotation {
   @Test
    public void testCase1(){
       System.out.println("this is testcase1.用来把方法作为测试的一部分");
       System.out.printf("From basic.testCase1 **********Thread Id:%s%n",Thread.currentThread().getId());
   }
    @Test
   public void testCase2(){
       System.out.println("这是testcase2");
        System.out.printf("From basic.testCase2 **********Thread Id:%s%n",Thread.currentThread().getId());
    }
   @BeforeMethod
    public void beforeMethod(){
       System.out.println("Before method");
   }

   @AfterMethod
    public void afterMethod(){
       System.out.println("after method");
   }

   @BeforeClass
    public void beforeClasse(){
       System.out.println("这是在类运行前运行的类");
   }
    @AfterClass
    public void afterClasse(){
        System.out.println("这是在类运行后运行的类");
    }
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuit");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuit");
    }

}
