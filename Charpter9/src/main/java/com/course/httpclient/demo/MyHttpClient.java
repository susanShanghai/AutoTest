package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;


public class MyHttpClient {
    @Test
    public void test1() throws IOException {
        String result;
        HttpGet get=new HttpGet("http://www.baidu.com");
        CloseableHttpClient client= HttpClients.createDefault();
        HttpResponse httpResponse=client.execute(get);
        result=EntityUtils.toString(httpResponse.getEntity(),"utf-8" );
        System.out.println(result);


    }
}
