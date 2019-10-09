package com.course.cases;

import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.UpdateUserInfoCase;
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

public class UpdateUserInfoTest {

    @Test(dependsOnGroups ="loginTrue",description = "更新用户信息接口测试")
    public void updateUserInfo() throws IOException {

        SqlSession session= DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase=session.selectOne("updateUserInfoCase",1);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

        //接口发送请求更新
        int result=getUpdateUserResult(updateUserInfoCase);

        //查询db进行验证
        SqlSession session1=DatabaseUtil.getSqlSession();
        User user=session1.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);

        //
        Assert.assertNotNull(result);
        Assert.assertNotNull(user);
    }

    private int getUpdateUserResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {
        HttpPost post=new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject param=new JSONObject();
        param.put("userName",updateUserInfoCase.getUserName());
        param.put("sex",updateUserInfoCase.getSex());
        param.put("permission",updateUserInfoCase.getPermission());
        param.put("isDelete",updateUserInfoCase.getIsDelete());
        param.put("id",updateUserInfoCase.getUserId());
        param.put("age",updateUserInfoCase.getAge());

        post.setHeader("content-type","application/json");
        StringEntity entity=new StringEntity(param.toString());
        post.setEntity(entity);

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        String result= EntityUtils.toString(response.getEntity());
        return Integer.parseInt(result);
    }

    @Test(dependsOnGroups ="loginTrue",description = "删除用户信息接口测试")
    public void deleteUser() throws IOException {

        SqlSession session= DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase=session.selectOne("updateUserInfoCase",2);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

        //接口发送请求更新
        int result=getUpdateUserResult(updateUserInfoCase);

        //查询db进行验证
        SqlSession session2=DatabaseUtil.getSqlSession();
        User user=session2.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);

        //
        Assert.assertNotNull(result);
        Assert.assertNotNull(user);

    }




}
