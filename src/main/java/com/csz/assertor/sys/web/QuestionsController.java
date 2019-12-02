package com.csz.assertor.sys.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.csz.assertor.rest.ResultGenerator;
import com.csz.assertor.rest.response.Response;
import com.csz.assertor.sys.DTO.GetOneQuestionDto;
import com.csz.assertor.sys.DTO.QuestionsDTO;
import com.csz.assertor.sys.DTO.QuestionsEditDTO;
import com.csz.assertor.sys.Vo.EditQuestionVO;
import com.csz.assertor.sys.Vo.QuestionAndOptionsVO;
import com.csz.assertor.sys.Vo.QuestionVO;
import com.csz.assertor.sys.entity.Exclusive;
import com.csz.assertor.sys.entity.ExclusiveGroup;
import com.csz.assertor.sys.entity.Options;
import com.csz.assertor.sys.entity.Questions;
import com.csz.assertor.sys.service.IExclusiveGroupService;
import com.csz.assertor.sys.service.IExclusiveService;
import com.csz.assertor.sys.service.IOptionsService;
import com.csz.assertor.sys.service.IQuestionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
@Api(tags = "题库模块")
public class QuestionsController {

    @Autowired
    private IQuestionsService service;

    @Autowired
    private IExclusiveGroupService egService;

    @Autowired
    private IExclusiveService eService;

    @Autowired
    private IOptionsService oService;

    @GetMapping("select")
    @ResponseBody
    @ApiOperation("题库信息查询")
    public Response selectQuestions(@RequestParam(required = false) String categoryGroupId,
                                    @RequestParam(required = false) String exclusiveId ,@RequestParam Integer current,@RequestParam Integer size){
        Page<QuestionVO> voList = service.SelectQuestions(categoryGroupId, exclusiveId,new Page<>(current,size));
        return ResultGenerator.table(voList);
    }

    @GetMapping("page")
    @ApiOperation("题库视图并返回一级专项")
    public String question(HttpServletRequest request){
        List<ExclusiveGroup> egList = egService.selectList(new EntityWrapper<ExclusiveGroup>());
        request.setAttribute("egList",egList);
        return "questionss.btl";
    }

    @GetMapping("getExclusive")
    @ResponseBody
    @ApiOperation("获取二级专项")
    public Response getExclusive(@RequestParam Integer categoryGroupId){
        EntityWrapper<Exclusive> wrapper =new EntityWrapper<>();
        wrapper.eq("category_group_id",categoryGroupId);
        List<Exclusive> exclusives = eService.selectList(wrapper);
        return ResultGenerator.ok(exclusives);
    }

    @GetMapping("upload")
    @ApiOperation("返回题目上传视图")
    public String upload(HttpServletRequest request){
        List<ExclusiveGroup> egList = egService.selectList(new EntityWrapper<ExclusiveGroup>());
        request.setAttribute("egList",egList);
        return "uploadQuestions.btl";
    }

    @PostMapping("uploadQuestions")
    @ResponseBody
    @ApiOperation("上传题目")
    public Response uploadQuestion(@RequestBody QuestionsDTO questionsDTO){
        Questions questions = new Questions();
        questions.setAnalysisCode(questionsDTO.getAnalysisCode());
        questions.setDescription(questionsDTO.getDescription());
        questions.setExclusiveId(questionsDTO.getExclusiveId());
        service.insert(questions);
        EntityWrapper<Questions> wrapper = new EntityWrapper<>();
        List<Questions> questions1 = service.selectList(wrapper);
        Questions questions2 = questions1.get(questions1.size() - 1);

        Options optionsA = new Options();
        optionsA.setOptionText(questionsDTO.getOptionA());
        optionsA.setQuestionId(questions2.getId());
        oService.insert(optionsA);

        Options optionsB = new Options();
        optionsB.setOptionText(questionsDTO.getOptionB());
        optionsB.setQuestionId(questions2.getId());
        oService.insert(optionsB);

        Options optionsC = new Options();
        optionsC.setOptionText(questionsDTO.getOptionC());
        optionsC.setQuestionId(questions2.getId());
        oService.insert(optionsC);

        Options optionsD = new Options();
        optionsD.setOptionText(questionsDTO.getOptionD());
        optionsD.setQuestionId(questions2.getId());
        oService.insert(optionsD);

        List<Options> options = oService.selectList(new EntityWrapper<Options>());

        if (questionsDTO.getCorrectAnswer()==4){
            questions2.setAnswerId(options.get(options.size()-1).getId());
        }else if (questionsDTO.getCorrectAnswer()==3){
            questions2.setAnswerId(options.get(options.size()-2).getId());
        }else if (questionsDTO.getCorrectAnswer()==2){
            questions2.setAnswerId(options.get(options.size()-3).getId());
        }else if (questionsDTO.getCorrectAnswer()==1){
            questions2.setAnswerId(options.get(options.size()-4).getId());
        }
        EntityWrapper<Questions> wrapper1 = new EntityWrapper<>();
        wrapper1.eq("id",questions2.getId());
        service.update(questions2,wrapper1);
        return  ResultGenerator.ok();
    }

    @GetMapping("edit")
    @ApiOperation("返回题目修改视图")
    public String edit(@RequestParam Integer id, HttpServletRequest request){
        Questions question = service.selectById(id);
        Exclusive exclusive = eService.selectById(question.getExclusiveId());
        ExclusiveGroup exclusiveGroup = egService.selectById(exclusive.getCategoryGroupId());
        EntityWrapper<Options> Owrapper = new EntityWrapper<>();
        Owrapper.eq("question_id",question.getId());
        List<Options> options = oService.selectList(Owrapper);
        EditQuestionVO editQuestionVO = new EditQuestionVO();
        editQuestionVO.setExclusiveId(exclusive.getId());
        editQuestionVO.setCategoryGroupId(exclusive.getCategoryGroupId());
        editQuestionVO.setExclusiveTitle(exclusive.getExclusiveTitle());
        editQuestionVO.setEgTitle(exclusiveGroup.getTitle());
        editQuestionVO.setAnalysisCode(question.getAnalysisCode());
        editQuestionVO.setDescription(question.getDescription());
        editQuestionVO.setAnswerId(question.getAnswerId());
        editQuestionVO.setOptions(options);
        List list = new ArrayList();
        int count = 0;
        String Answer=null;
        for (Options option : options) {
            if (option.getId()==question.getAnswerId()){
                break;
            }
            count++;
        }
        if (count==0){
            Answer = "A";
        }else if (count==1){Answer="B";} else if (count==2){Answer="C";}else if (count==3){Answer="D";}
        editQuestionVO.setAnswer(Answer);
        List<ExclusiveGroup> egList = egService.selectList(new EntityWrapper<ExclusiveGroup>());
        request.setAttribute("egList",egList);
        request.setAttribute("question",editQuestionVO);
        request.setAttribute("id",id);
        return "editQuestions.btl";
    }

    @PostMapping("updateQuestions")
    @ResponseBody
    @ApiOperation("修改题目")
    public Response updateQuestions(@RequestBody QuestionsEditDTO questionsEditDTO){
        Questions questions = service.selectById(questionsEditDTO.getId());
        questions.setAnalysisCode(questionsEditDTO.getAnalysisCode());
        questions.setAnswerId(questionsEditDTO.getCorrectAnswer());
        questions.setExclusiveId(questionsEditDTO.getExclusiveId());
        questions.setDescription(questionsEditDTO.getDescription());
        EntityWrapper<Questions> questionsEntityWrapper = new EntityWrapper<>();
        questionsEntityWrapper.eq("id",questions.getId());
        service.update(questions,questionsEntityWrapper);

        //修改选项
        EntityWrapper<Options> wrapper = new EntityWrapper<>();
        wrapper.eq("question_id",questionsEditDTO.getId());
        List<Options> options = oService.selectList(wrapper);
        options.get(0).setOptionText(questionsEditDTO.getOptionA());
        options.get(1).setOptionText(questionsEditDTO.getOptionB());
        options.get(2).setOptionText(questionsEditDTO.getOptionC());
        options.get(3).setOptionText(questionsEditDTO.getOptionD());

        EntityWrapper<Options> wrapper1 = new EntityWrapper<>();
        wrapper1.eq("id",options.get(0).getId());
        oService.update(options.get(0),wrapper1);

        EntityWrapper<Options> wrapper2 = new EntityWrapper<>();
        wrapper2.eq("id",options.get(1).getId());
        oService.update(options.get(1),wrapper2);

        EntityWrapper<Options> wrapper3 = new EntityWrapper<>();
        wrapper3.eq("id",options.get(2).getId());
        oService.update(options.get(2),wrapper3);

        EntityWrapper<Options> wrapper4 = new EntityWrapper<>();
        wrapper4.eq("id",options.get(3).getId());
        oService.update(options.get(3),wrapper4);

        return ResultGenerator.ok();
    }


    @GetMapping("/getOne")
    public Response<QuestionAndOptionsVO> GetOne(GetOneQuestionDto getOneQuestionDto){


        return null;
    }
}
