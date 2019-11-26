package com.csz.assertor.sys.web;


import com.csz.assertor.Exception.OPException;
import com.csz.assertor.rest.ResultGenerator;
import com.csz.assertor.rest.response.Response;
import com.csz.assertor.sys.entity.OperatUser;
import com.csz.assertor.sys.service.IOperatUserService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  运营用户管理
 * </p>
 *
 * @author assertor
 * @since 2019-11-26
 */
@RestController
@RequestMapping("/sys/user")
@Api("运营用户管理")
public class OperatUserController {
    @Autowired
    private IOperatUserService service;

    @PostMapping("/register")
    public Response registe(@RequestParam String userName , @RequestParam String password,
                            @RequestParam String verifyPassword, HttpServletResponse response) throws IOException {
        if (!(password.equals(verifyPassword))){
            response.sendRedirect("/user/register");
            return ResultGenerator.failure("两次输入密码不一致");
        }
        if (password.length()<6){
            response.sendRedirect("/user/register");
            return ResultGenerator.failure("密码不能少于六位");
        }
        OperatUser users = service.selectByUserName(userName);
        if (users!=null){
            response.sendRedirect("/user/register");
            return ResultGenerator.failure("用户名已注册");
        }
        else {
            OperatUser user = new OperatUser();
            user.setUserName(userName);
            //加盐
            String salt = RandomStringUtils.randomAlphanumeric(8);
            user.setSalt(salt);
            String dbPassword = DigestUtils.md5DigestAsHex((password + salt) .getBytes());
            user.setPassword(dbPassword);
            service.insert(user);
            response.sendRedirect("/login");
            return ResultGenerator.ok();
        }
    }

    @PostMapping("/login")
    public Response login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) throws IOException {
        OperatUser user = service.selectByUserName(username);
        String salt = user.getSalt();
        if (!DigestUtils.md5DigestAsHex((password + salt).getBytes()).equals(user.getPassword())) {
            throw new OPException("用户名或密码错误！");
        }
        response.sendRedirect("/index");
        return  null;
    }

}
