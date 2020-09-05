package com.shiro.controller;

import com.shiro.pojo.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@Component
public class HomeController {
    @RequestMapping({"/","/index"})
    public String index(){
        return"/index";
    }

    /**
     * 登录方法
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
    //@ResponseBody
    public String ajaxLogin(UserInfo userInfo, Map<String, Object> map) {
        ObjectMapper mapper = new ObjectMapper();
        //Map<String, Object> map = new HashMap<>();
        //JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        //subject.checkPermission(get);
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUsername(), userInfo.getPassword());
        try {
            subject.login(token);
            map.put("token", subject.getSession().getId());
            map.put("msg", "登录成功");
            //jsonObject.put("token", subject.getSession().getId());
            //jsonObject.put("msg", "登录成功");
            return "/index";
        } catch (IncorrectCredentialsException e) {
            //jsonObject.put("msg", "密码错误");
            map.put("msg", "密码错误" );
        } catch (LockedAccountException e) {
            //jsonObject.put("msg", "登录失败，该用户已被冻结");
            map.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            //jsonObject.put("msg", "该用户不存在");
            map.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*String responseStr = "";
        try {
            responseStr = mapper.writeValueAsString(map);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //return responseStr;
        return "/login";
        //return jsonObject.toString();
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
        System.out.println("HomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return "/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }

}