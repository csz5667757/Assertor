package com.csz.assertor.sys.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.csz.assertor.rest.ResultGenerator;
import com.csz.assertor.rest.response.Response;
import com.csz.assertor.sys.entity.TechCategory;
import com.csz.assertor.sys.service.ITechCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author assertor
 * @since 2019-11-27
 */
@Controller
@RequestMapping("/sys/tech")
public class TechCategoryController {

    @Autowired
    private ITechCategoryService service;

    @GetMapping("all")
    @ResponseBody
    public Response SelectTechCategory(@RequestParam Integer current, @RequestParam Integer size){
        Page<TechCategory> page = new Page<>(current,size);
        Page<TechCategory> selectPage = service.selectPage(page);
        return ResultGenerator.table(selectPage);
    }

    @PostMapping("add")
    public String AddTechCategory(@RequestParam String category, HttpServletResponse response) throws IOException {
        TechCategory techCategory = new TechCategory();
        techCategory.setName(category);
        service.insert(techCategory);
        return "techCategorys.btl";
    }

    @PostMapping("update")
    public String updateTechCategory(@RequestParam Integer id,@RequestParam String category
    ,HttpServletResponse response) throws IOException {
        TechCategory techCategory = new TechCategory();
        techCategory.setId(id);
        techCategory.setName(category);
        service.updateById(techCategory);
        return "techCategorys.btl";
    }

    @PostMapping("delete")
    @ResponseBody
    public Response deleteTechCategory(@RequestParam Integer id,HttpServletResponse response) throws IOException {
        EntityWrapper<TechCategory> wrapper =new EntityWrapper<>();
        wrapper.eq("id",id);
        TechCategory selectOne = service.selectOne(wrapper);
        if (!Objects.isNull(selectOne)){
            service.deleteById(id);
        }
        return ResultGenerator.ok("删除成功！");
    }
}
