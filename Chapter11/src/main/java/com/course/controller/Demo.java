package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@Log4j
@RestController
@Api(value="v1",description = "这是我的第一个demo")
@RequestMapping("v1")
public class Demo {

    //获取执行sql语句的对象
    @Autowired
    private SqlSessionTemplate template;


    @RequestMapping(value = "/getUserCount", method = RequestMethod.GET)
    @ApiOperation(value="可以获取到用户数", httpMethod = "GET")

    public int getUserCount(){
        return template.selectOne("getCount");

    }

    @RequestMapping(value="/addUser", method=RequestMethod.POST)
    @ApiOperation(value="add User",httpMethod = "POST")
    public int addUser(@RequestBody User u){
        return template.insert("addUser",u);
    }

    @RequestMapping(value="/updateUser", method=RequestMethod.POST)
    @ApiOperation(value="update User",httpMethod = "POST")
    public int updateUser(@RequestBody User user){
        return template.update("updateUser",user);
    }


    @RequestMapping(value="/deleteUser")
    public int deleteUser(@RequestParam int id){
        return template.delete("deleteUser",id);
    }
}
