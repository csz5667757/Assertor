package com.csz.assertor.sys.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.csz.assertor.rest.ResultGenerator;
import com.csz.assertor.rest.response.Response;
import com.csz.assertor.sys.Vo.RecommendedVO;
import com.csz.assertor.sys.entity.Questions;
import com.csz.assertor.sys.entity.Recommended;
import com.csz.assertor.sys.entity.TechCategory;
import com.csz.assertor.sys.service.IQuestionsService;
import com.csz.assertor.sys.service.IRecommendedService;
import com.csz.assertor.sys.service.ITechCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author assertor
 * @since 2019-12-01
 */

@Controller
@RequestMapping("/sys/recommended")
public class RecommendedController {

    @Autowired
    private IRecommendedService service;

    @Autowired
    private ITechCategoryService tcService;

    @Autowired
    private IQuestionsService qService;

    @GetMapping("page")
    public String recommenden(HttpServletRequest request){
        List<TechCategory> techCategories = tcService.selectList(new EntityWrapper<>());
        List<Recommended> recommendedList = service.selectList(new EntityWrapper<>());
        Set<Integer> set = new HashSet();
        for (Recommended recommended : recommendedList) {
            set.add(recommended.getLevel());
        }
        System.out.println(set);
        request.setAttribute("levelList",set);
        request.setAttribute("tcgList",techCategories);
        return "recommended.btl";
    }

    @GetMapping("select")
    @ResponseBody
    public Response select(@RequestParam(required = false) Integer techCategoryId,
                           @RequestParam(required = false) Integer level,@RequestParam Integer current,@RequestParam Integer size){
        Page<RecommendedVO> voPage = service.SelectRecommendeds(techCategoryId, level, new Page<>(current, size));
        return ResultGenerator.table(voPage);
    }

    @GetMapping("selectList")
    public Response selectList(@RequestParam Integer recommendedId){
        /// TODO: 2019/12/2  
        EntityWrapper<Questions> wrapper = new EntityWrapper<>();
        wrapper.eq("recommended_Id",recommendedId);
        qService.selectList(wrapper);
        return null;
    }


}
