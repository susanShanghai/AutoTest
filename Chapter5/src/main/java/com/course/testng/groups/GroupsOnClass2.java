package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups="stu")
public class GroupsOnClass2 {
    public void stul(){
        System.out.println(" called GroupsOnClass2.stu1");
    }
    public void stu2(){
        System.out.println(" called GroupsOnClass2.stu2");
    }
}
