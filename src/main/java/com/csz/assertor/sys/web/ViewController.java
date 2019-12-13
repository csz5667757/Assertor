package com.csz.assertor.sys.web;

import com.csz.assertor.rest.ResultGenerator;
import com.csz.assertor.rest.response.Response;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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

    @GetMapping("/exclusive")
    public String category(){
        return "cate.btl";
    }

    @GetMapping("/json")
    @ResponseBody
    public Response jsonTest(){
        List<data> list = Lists.newArrayList();
        data data = new data();
        data.setId(1);
        data.setName("aaaa");
        data.setPid(3);
        data.setSex("nan");


        data data1 = new data();
        data1.setId(2);
        data1.setName("bbbbb");
        data1.setPid(3);
        data1.setSex("nv");

        data data2 = new data();
        data2.setId(3);
        data2.setName("cccccc");
        data2.setPid(0);
        data2.setSex("nan");

        data data3 = new data();
        data3.setId(4);
        data3.setName("ddddd");
        data3.setPid(0);
        data3.setSex("nv");


        list.add(data);
        list.add(data1);
        list.add(data2);
        list.add(data3);

        return ResultGenerator.table(list);
    }
}


