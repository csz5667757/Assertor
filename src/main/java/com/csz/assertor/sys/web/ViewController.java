package com.csz.assertor.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Assertor
 * @Description
 * @Date：2019/11/26
 */
@Controller
public class ViewController {
    @RequestMapping("/login")
    public String Login(){
        return "login.btl";
    }

    @RequestMapping("/user/register")
    public String Register(){
        return "register.btl";
    }

    @GetMapping("index")
    public String Index(){return "index.btl";}
}


