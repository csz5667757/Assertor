package com.csz.assertor.sys.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.csz.assertor.rest.ResultGenerator;
import com.csz.assertor.rest.response.Response;
import com.csz.assertor.sys.entity.User;
import com.csz.assertor.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Assertor
 * @since 2019-11-24
 */
@RestController
@RequestMapping("/sys/user")
public class UserEntity {
    @Autowired
    private IUserService iUserService;

//    public Response<List<User>> UserSelect(){
//        List<User> users = iUserService.list();
//        return new Response<>(ResultEnum.SUCCESS,users);
//    }

    @RequestMapping("allUser")
    public Response<List> UserSelect(){
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        List<User> users = iUserService.selectList(wrapper);
        return  ResultGenerator.ok(users);
    }

    @RequestMapping("updateUser")
    public Response UpdateUser(){
        User user = new User();
        user.setId(2);
        user.setPassword("12345");
        iUserService.updateById(user);
        return ResultGenerator.ok();
    }

    @RequestMapping("addUser")
    public Response addUser(){
        User user =new User();
        user.setName("李克松");
        user.setUsername("lks");
        user.setPassword("123456");
        user.setAddress("湖北省荆州市");
        user.setUseDate(LocalDateTime.now());
        iUserService.insert(user);
        return ResultGenerator.ok();
    }
}
