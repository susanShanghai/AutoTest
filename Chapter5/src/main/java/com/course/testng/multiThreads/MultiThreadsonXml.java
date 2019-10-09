package com.course.testng.multiThreads;

import org.testng.annotations.Test;

public class MultiThreadsonXml {
    @Test
    public void test1(){
        System.out.printf("From test1 **********Thread Id:%s%n",Thread.currentThread().getId());
    }
    @Test
    public void test2(){
        System.out.printf("From test2 **********Thread Id:%s%n",Thread.currentThread().getId());
    }
    @Test
    public void test3(){
        System.out.printf("From test3 **********Thread Id:%s%n",Thread.currentThread().getId());
    }

}
