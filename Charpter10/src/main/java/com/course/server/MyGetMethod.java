package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value="/",description = "this is all get method")
public class MyGetMethod {
    @RequestMapping(value="/getCookies",method= RequestMethod.GET)
    @ApiOperation(value="you can get cookie using this method",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        //HttpServerletRequst
        //HttpServerletResponse
        Cookie cookie=new Cookie("login","true");
        response.addCookie(cookie);
        return "Congratulation!!!";
    }

    @RequestMapping(value="/get/with/cookies",method=RequestMethod.GET)
    @ApiOperation(value="getwithcookies",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        StringBuffer result=new StringBuffer();
        if (Objects.isNull(cookies)) {
            return "You must take cookies";
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login") && cookie.getName().equals("true")) {
                   result.append( "Congratulations!! you get the right cookie:" + cookie.getName() + "=" + cookie.getValue());
                }
                else{
                result.append( "your cookie info:" + cookie.getName() + "=" + cookie.getValue());
                }
            }
        }
        return result.toString();
    }

    /**
     * 开发一个需要携带参数才能访问的get请求
     * 第一种实现方式url：key=value&key=value
     * 模拟获取商品列表
     */

    @RequestMapping(value="/get/with/param",method =RequestMethod.GET)
    @ApiOperation(value="需要携带参数才能访问的get请求（第一种方法）",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> myList=new HashMap<>();
        myList.put("shoes",400);
        myList.put("noodles",1);
        myList.put("TShirt",300);
        return myList;

    }

    /**
     * 第二种携带参数访问
     * url:ip:port/get/with/param/10/20
     */


    @RequestMapping(value = "/get/with/param/{start}/{end}")
    @ApiOperation(value="需要携带参数才能访问的get请求（第二种方法）",httpMethod = "GET")
    public Map<String,Integer> myGetList(@PathVariable Integer start,
                         @PathVariable Integer end){
        Map<String,Integer> myList=new HashMap<>();
        myList.put("shoes",400);
        myList.put("noodles",11);
        myList.put("TShirt",300);
        return myList;

    }
}
