package com.csz.assertor.sys.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.csz.assertor.rest.ResultGenerator;
import com.csz.assertor.rest.response.Response;
import com.csz.assertor.sys.entity.Menu;
import com.csz.assertor.sys.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author assertor
 * @since 2019-11-24
 */
@RestController
@RequestMapping("/sys/menu")
public class MenuController {

    @Autowired
    private IMenuService iMenuService;

    @RequestMapping("addMenu")
    public Response addMenu(){
        Menu menu =new Menu();
        menu.setCreateTime(new Date());
        menu.setMenuName("test");
        menu.setMenuSequnence("11");
        menu.setMenuType("1");
        menu.setEnabled(1);
        menu.setUrl("/user");
        menu.setPid(123);
        iMenuService.insert(menu);
        return ResultGenerator.ok();
    }

    @RequestMapping("deleteMenu")
    public Response deleteMenu(){
        Menu menu =  new Menu();
        menu.setId(11);
        iMenuService.deleteById(menu);
        return ResultGenerator.ok();
    }

    @RequestMapping("SelectAll")
    public Response<List> selectAll(){
        EntityWrapper<Menu> wrapper = new EntityWrapper<>();
        List<Menu> menuList = iMenuService.selectList(wrapper);
        return ResultGenerator.ok(menuList);
    }

}
