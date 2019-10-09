package com.tester.extend.demo;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestMethodDemo {
    @Test
    public void test1(){
        Assert.assertEquals(1,2);
//        System.out.println("this is tes1:Error");
    }
    @Test
    public void test2(){
        Assert.assertEquals(1,1);
//        System.out.println("this is tes2:Right");
    }
    @Test
    public void test3(){
        Assert.assertEquals("aaa","aaa");
//        System.out.println("this is tes2:Right");
    }

    @Test
    public void logDemo(){
        Reporter.log("This is my log");
        throw new RuntimeException("This is my runtimeException");
    }







}
