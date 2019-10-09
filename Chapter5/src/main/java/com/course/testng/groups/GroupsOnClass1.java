package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups="stu")
public class GroupsOnClass1 {
    public void stul(){
        System.out.println(" called GroupsOnClass1.stu1");
    }

    public void stu2(){
        System.out.println(" called GroupsOnClass1.stu2");
    }

}
