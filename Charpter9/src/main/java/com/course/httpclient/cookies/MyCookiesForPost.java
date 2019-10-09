package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {

    private ResourceBundle bundle=ResourceBundle.getBundle("application",Locale.CHINA);
    private String url=bundle.getString("test.url");
    private CookieStore store=new BasicCookieStore();

    @Test
    public void getCookies() throws IOException {
         String uri=bundle.getString("getCookies.uri");
         String testUrl=url+uri;
         HttpGet get=new HttpGet(testUrl);
         CloseableHttpClient client= HttpClients.custom().setDefaultCookieStore(store).build();
        HttpResponse response= client.execute(get);
        String result=EntityUtils.toString(response.getEntity());
        System.out.println(result);

        List<Cookie> cookieList=store.getCookies();
        for(Cookie cookie:cookieList){
            String name=cookie.getName();
            String value=cookie.getValue();
            System.out.println("Cookie Name:"+name+" Value:"+value);
        }
    }

    @Test(dependsOnMethods = {"getCookies"})
    public void testPostWithCookies() throws IOException {
        String uri=bundle.getString("test.post.with.cookies");
        String testUrl=url+uri;

        HttpPost post=new HttpPost(testUrl);
        CloseableHttpClient client=HttpClients.custom().setDefaultCookieStore(store).build();
        //添加参数
        JSONObject param=new JSONObject();
        param.put("name","huhansan");
        param.put("age","20");
        //设置请求头信息
        post.setHeader("Content-Type","application/json");
        //将参数信息添加到方法中
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //
        HttpResponse response=client.execute(post);
        String result=EntityUtils.toString(response.getEntity());
//        System.out.println("aaa");
        System.out.println(result);
        //处理结果
        //将返回结果转换成Json对象
        JSONObject resultJson=new JSONObject(result);
        //判断返回结果值
//        String success=(String)resultJson.get("huhansan");
        String success=resultJson.get("huhansan").toString();
        String status=(String)resultJson.get("status");
        Assert.assertEquals("success",success);
        Assert.assertEquals("1",status);
    }

}