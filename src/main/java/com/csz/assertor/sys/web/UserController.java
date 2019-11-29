package com.csz.assertor.sys.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.csz.assertor.rest.ResultGenerator;
import com.csz.assertor.rest.response.Response;
import com.csz.assertor.sys.Vo.UserVO;
import com.csz.assertor.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author assertor
 * @since 2019-11-27
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping("all")
    public Response selectAll(@RequestParam Integer current,@RequestParam Integer size){
        Page<UserVO> page = new Page<>(current,size);
        Page<UserVO> users = service.getUser(page);
        return ResultGenerator.table(users);
    }
}
