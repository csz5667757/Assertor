package com.csz.assertor.sys.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.csz.assertor.rest.ResultGenerator;
import com.csz.assertor.rest.response.Response;
import com.csz.assertor.sys.Vo.QuestionVO;
import com.csz.assertor.sys.entity.Exclusive;
import com.csz.assertor.sys.entity.ExclusiveGroup;
import com.csz.assertor.sys.service.IExclusiveGroupService;
import com.csz.assertor.sys.service.IExclusiveService;
import com.csz.assertor.sys.service.IQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author assertor
 * @since 2019-11-27
 */
@Controller
@RequestMapping("/sys/questions")
public class QuestionsController {

    @Autowired
    private IQuestionsService service;

    @Autowired
    private IExclusiveGroupService egService;

    @Autowired
    private IExclusiveService eService;

    @GetMapping("select")
    @ResponseBody
    public Response selectQuestions(@RequestParam(required = false) String categoryGroupId,
                                    @RequestParam(required = false) String exclusiveId ,@RequestParam Integer current,@RequestParam Integer size){
        Page<QuestionVO> voList = service.SelectQuestions(categoryGroupId, exclusiveId,new Page<>(current,size));
        return ResultGenerator.table(voList);
    }

    @GetMapping("page")
    public String question(HttpServletRequest request){
        List<ExclusiveGroup> egList = egService.selectList(new EntityWrapper<ExclusiveGroup>());
        request.setAttribute("egList",egList);
        return "questionss.btl";
    }

    @GetMapping("getExclusive")
    @ResponseBody
    public Response getExclusive(@RequestParam Integer categoryGroupId){
        EntityWrapper<Exclusive> wrapper =new EntityWrapper<>();
        wrapper.eq("category_group_id",categoryGroupId);
        List<Exclusive> exclusives = eService.selectList(wrapper);
        return ResultGenerator.ok(exclusives);
    }

    @GetMapping("upload")
    public String upload(){
        return "uploadQuestions.btl";
    }
}
