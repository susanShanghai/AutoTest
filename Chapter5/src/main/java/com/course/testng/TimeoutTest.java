package com.course.testng;

import org.testng.annotations.Test;

public class TimeoutTest {
/*    @Test(timeOut = 3000)//3000毫秒
    public void testSuccess() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("not timeout");
    }*/
    @Test(timeOut = 3000)//3000毫秒
    public void testFailed() throws InterruptedException {
        Thread.sleep(3500);
        System.out.println("timeout");
    }
}
