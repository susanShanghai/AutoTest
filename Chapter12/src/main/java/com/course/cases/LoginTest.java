package com.course.cases;

import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {

    @BeforeTest
    public void beforeTest(){
        TestConfig.loginUrl= ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.addUserUrl=ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.updateUserInfoUrl=ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.getUserInfoUrl=ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl=ConfigFile.getUrl(InterfaceName.GETUSERLIST);

        TestConfig.defaultHttpClient=new DefaultHttpClient();
    }

    @Test(groups="loginTrue",description = "登录成功")
    public void loginTrue() throws IOException {
        SqlSession session= DatabaseUtil.getSqlSession();
        LoginCase loginCase=session.selectOne("loginCase",1);
        System.out.println("登录的用户是："+loginCase.toString());
        System.out.println("登录的url是："+TestConfig.loginUrl);

        String result=getResult(loginCase);
        Assert.assertEquals(result,loginCase.getExpected());
    }

    private String getResult(LoginCase loginCase) throws IOException {
        HttpPost post=new HttpPost(TestConfig.loginUrl);
        JSONObject param=new JSONObject();
        param.put("userName",loginCase.getUserName());
        param.put("password",loginCase.getPassword());

        post.setHeader("content-type","application/json");
        StringEntity entity= new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        String result= EntityUtils.toString(response.getEntity(),"utf-8");
        //TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        TestConfig.store=TestConfig.defaultHttpClient.getCookieStore();
        return result;
    }

    @Test(groups="loginFalse",description = "登录成功")
    public void loginFalse() throws IOException {
        SqlSession session= DatabaseUtil.getSqlSession();
        LoginCase loginCase=session.selectOne("loginCase",2);
        System.out.println("登录的用户是："+loginCase.toString());
        System.out.println("登录的url是："+TestConfig.loginUrl);

        String result=getResult(loginCase);
        Assert.assertEquals(result,loginCase.getExpected());
    }

}
