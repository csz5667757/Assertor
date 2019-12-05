package com.csz.assertor.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Assertor
 * @Description
 * @Date：2019/11/26
 */
@Controller
public class ViewController {


    @RequestMapping("/logins")
    public String Login(){
        return "login.btl";
    }

    @RequestMapping("/user/register")
    public String Register(){
        return "register.btl";
    }

    @GetMapping("user")
    public String user(@RequestParam String nickname,HttpServletRequest request){
        request.setAttribute("nickname",nickname);
        return "user.btl";
    }

    @GetMapping("category")
    public String techCategory(@RequestParam String nickname,HttpServletRequest request){
        request.setAttribute("nickname",nickname);
        return "techCategory.btl";
    }

    @GetMapping("/login")
    public String logins(){
        return "logins.btl";
    }

    @GetMapping("indexs")
    public String indexs(@RequestParam String nickname,HttpServletRequest request){
        request.setAttribute("nickname",nickname);
        return "indexs.btl";
    }

    @GetMapping("users")
    public String users(){
        return "users.btl";
    }

    @GetMapping("categorys")
    public String categorys(){
        return "techCategorys.btl";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("logined","logout");
        return "logins.btl";
    }

}


