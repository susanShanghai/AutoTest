package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "These are all my Post request")
@RequestMapping("/v1")
public class MyPostMethod {

    private static Cookie cookie;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    @ApiOperation(value="login API,成功后获取Cookies信息",httpMethod = "POST")

    public String login(HttpServletResponse response,
                        @RequestParam(value="userName",required = true) String username,
                        @RequestParam(value = "password",required = true) String password){
        if(username.equals("zhangsan") && password.equals("123456")){

            cookie =new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜登录成功";
        }
        return "登录错误";
    }

    @RequestMapping(value="/getUserList", method = RequestMethod.POST)
    @ApiOperation(value="getUserList",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                              @RequestBody User u){
        User user = new User();
        Cookie[] cookies=request.getCookies();
        for(Cookie c:cookies){
            if(c.getName().equals("login")
                    && c.getValue().equals("true")
                    && u.getUserName().equals("zhangshan")
                    && u.getPassword().equals("123456")){
                user.setName("lisi");
                user.setAge(18);
                user.setSex("man");
                return user.toString();
                }
        }
        return "参数不合法";

    }
}
