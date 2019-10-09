package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Log4j
@RestController
@Api(value="v1",description = "UserManager.service")
@RequestMapping("v1")
public class UserManager {

    @Autowired
    private SqlSessionTemplate template;

    @ApiOperation(value="login API",httpMethod ="POST")
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Boolean login(HttpServletResponse response, @RequestBody User user){
        int i=template.selectOne("login",user);
        Cookie cookie=new Cookie("login","true");
        response.addCookie(cookie);
        log.info("查询的结果是："+i);
        if(i==1){
            log.info("登录的用户是："+user.getUserName());
            return  true;
        }
        return  false;
    }

    @ApiOperation(value="addUser API",httpMethod ="POST")
    @RequestMapping(value="/addUser",method = RequestMethod.POST)
    public boolean addUser(HttpServletRequest request, @RequestBody User user){
        Boolean x=verifyCookies(request);
        int result=0;
        if(x){
            result= template.insert("addUser",user);
            System.out.println("******************"+user.getId());
        }
        if(result>0){
            log.info("添加用户成功");
            return true;
        }
        return false;
    }

    @ApiOperation(value = "获取用户（列表）接口",httpMethod = "POST")
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    public List<User> getUserInfo(HttpServletRequest request,@RequestBody User user){
        Boolean x=verifyCookies(request);
        if(x){
            List<User> users=template.selectList("getUserInfo",user);
            log.info("获取到的用户数量是："+users.size());
            return users;
        } else{
        return null;}

    }

    @ApiOperation(value = "更新/删除用户接口",httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)

    public int updateUser(HttpServletRequest request, @RequestBody User user){
        Boolean x=verifyCookies(request);
        int result=0;
        if(x){
            result=template.update("updateUserInfo",user);
            log.info("更新用户的数量是："+result);
        }
        return result;
    }

    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie [] cookies=request.getCookies();

        if(cookies.length==0){
            log.info("cookies are empty");
            return false;
        }
        for(Cookie c :cookies){
            if(c.getName().equals("login") && c.getValue().equals("true")){
                log.info("cookies 验证通过");
                return true;
            }
        }
        return false;
    }

}
