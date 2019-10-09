package com.course.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class SuiteConifg {
    @BeforeSuite
    public void beforeSuit(){
        System.out.println("beforeSuit is running");
    }
    @AfterSuite
    public void afterSuit(){
        System.out.println("afterSuit is running");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("afterTest");
    }
}
