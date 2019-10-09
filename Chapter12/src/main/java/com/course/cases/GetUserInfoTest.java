package com.course.cases;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnGroups ="loginTrue",description = "获取用户信息测试")
    public void getUserInfo() throws IOException {
        SqlSession session= DatabaseUtil.getSqlSession();
        GetUserInfoCase getUserInfoCase=session.selectOne("getUserInfoCase",1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);
        //发送请求
        JSONArray jsonArray=getJsonResult(getUserInfoCase);

        List<User> list=new ArrayList<User>();
        list=JSONObject.parseArray(jsonArray.getString(0),User.class);
        //验证结果
        SqlSession session1= DatabaseUtil.getSqlSession();
        User resultUserInfo=session1.selectOne(getUserInfoCase.getExpected(),getUserInfoCase);
        List<User> userList=new ArrayList<User>();
        userList.add(resultUserInfo);

        Assert.assertEquals(list,userList);

    }

    private JSONArray getJsonResult(GetUserInfoCase getUserInfoCase) throws IOException {

        HttpPost post=new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param=new JSONObject();
        param.put("id",getUserInfoCase.getUserId());
        StringEntity entity=new StringEntity(param.toString());

        post.setHeader("content-type","application/json");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        HttpResponse response=TestConfig.defaultHttpClient.execute(post);

        String result= EntityUtils.toString(response.getEntity(),"utf-8");
        List resultList= Arrays.asList(result);
        JSONArray resultArray=new JSONArray(resultList);
        return resultArray;
    }



}
