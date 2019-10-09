package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {
    @Test(groups="server")
    public void test1(){
        System.out.println("this is server side method test1");
    }

    @Test(groups="server")
    public void test2(){
        System.out.println("this is server side method test2");
    }
    @Test(groups="client")
    public void test3(){
        System.out.println("this is client side method test3");
    }

    @Test(groups="client")
    public void test4(){
        System.out.println("this is client side method test4");
    }

    @BeforeGroups("server")
    public void beforeGroupOnServer(){
        System.out.println("this is called before server group ");
    }
    @AfterGroups("server")
    public void afterGroupOnServer(){
        System.out.println("this is called after server group");
    }
    @BeforeGroups("client")
    public void beforeGroupOnClient(){
        System.out.println("this is called before client group ");
    }
    @AfterGroups("client")
    public void afterGroupOnClient(){
        System.out.println("this is called after client group");
    }
}
