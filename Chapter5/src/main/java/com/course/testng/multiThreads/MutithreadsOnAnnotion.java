package com.course.testng.multiThreads;

import org.testng.annotations.Test;

public class MutithreadsOnAnnotion {
    @Test(invocationCount =10,threadPoolSize =5)
    public void test(){
        System.out.println("11111");
        System.out.printf("Thread Id:%s%n",Thread.currentThread().getId());
    }
}
