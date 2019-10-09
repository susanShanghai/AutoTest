package com.course.cases;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
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
import java.util.Collections;
import java.util.List;



public class GetUserListTest {
    @Test(dependsOnGroups ="loginTrue",description = "获取用户列表信息")
    public void getUserList() throws IOException {
        SqlSession session= DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase=session.selectOne("getUserListCase",1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);

        //发送请求获取结果
        JSONArray resultJson=getJsonResult(getUserListCase);
        //把JSONArray转换成List,fastJson功能
        List<User> list=new ArrayList<User>();
        list=JSONObject.parseArray(resultJson.getString(0),User.class);
        for(User u:list){
            System.out.println("请求得到的user："+u.toString());
        }
        //验证
        SqlSession session1= DatabaseUtil.getSqlSession();
        List <User> userList=session1.selectList(getUserListCase.getExpected(),getUserListCase);

        for(User u:userList){
            System.out.println("获取的user："+u.toString());
        }
         Assert.assertEquals(list,userList);

    }

    private JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {

        HttpPost post=new HttpPost(TestConfig.getUserListUrl);
        JSONObject param=new JSONObject();
        param.put("userName", getUserListCase.getUserName());
        param.put("sex",getUserListCase.getSex());
        param.put("age",getUserListCase.getAge());

        post.setHeader("content-type","application/json");
        StringEntity entity= new StringEntity(param.toString());
        post.setEntity(entity);

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        String result;
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        JSONArray jsonArray=new JSONArray(Collections.singletonList(result));

        return jsonArray;
    }

}
