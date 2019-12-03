package com.csz.assertor.sys.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.csz.assertor.Exception.OPException;
import com.csz.assertor.rest.ResultGenerator;
import com.csz.assertor.rest.response.Response;
import com.csz.assertor.sys.DTO.RecommendedDTO;
import com.csz.assertor.sys.DTO.RecommendedQuestionsDTO;
import com.csz.assertor.sys.DTO.RecommendedQuestionsEditDTO;
import com.csz.assertor.sys.Vo.EditRecommendedVO;
import com.csz.assertor.sys.Vo.RecommendedVO;
import com.csz.assertor.sys.entity.Options;
import com.csz.assertor.sys.entity.Questions;
import com.csz.assertor.sys.entity.Recommended;
import com.csz.assertor.sys.entity.TechCategory;
import com.csz.assertor.sys.service.IOptionsService;
import com.csz.assertor.sys.service.IQuestionsService;
import com.csz.assertor.sys.service.IRecommendedService;
import com.csz.assertor.sys.service.ITechCategoryService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 套题上传、修改功能前端控制器
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

    @Autowired
    private IOptionsService oService;

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

    /**
     *@Author: luoluo
     *@date: 2019/12/2
     * 查看套题题目信息
     */
    @GetMapping("select")
    @ResponseBody
    public Response select(@RequestParam(required = false) Integer techCategoryId,
                           @RequestParam(required = false) Integer level,@RequestParam Integer current,@RequestParam Integer size){
        Page<RecommendedVO> voPage = service.SelectRecommendeds(techCategoryId, level, new Page<>(current, size));
        return ResultGenerator.table(voPage);
    }

    @GetMapping("selectList")
    @ResponseBody
    public Response selectList(@RequestParam Integer recommendedId){
        EntityWrapper<Questions> wrapper = new EntityWrapper<>();
        wrapper.eq("recommended_Id",recommendedId);
        List<Questions> questions = qService.selectList(wrapper);
        return ResultGenerator.table(questions);
    }

    @GetMapping("add")
    @ApiOperation("添加套题视图")
    public String Recommended(HttpServletRequest request){
        List<TechCategory> techCategoryList = tcService.selectList(new EntityWrapper<>());
        request.setAttribute("tcgs",techCategoryList);
        return "uploadRecommended.btl";
    }

    @PostMapping("addRecommended")
    @ApiOperation("添加套题")
    @ResponseBody
    public Response addRecommended(@RequestBody RecommendedDTO recommendedDTO)throws OPException {
        service.addRecommended(recommendedDTO);
        return ResultGenerator.ok();
    }

    @GetMapping("upload")
    @ApiOperation("返回题目上传套题题目视图")
    public String upload(@RequestParam Integer recommendedId , HttpServletRequest request){
        request.setAttribute("recommendedId",recommendedId);
        return "addRecommendedQuestions.btl";
    }

    @PostMapping("uploadRecommendedQuestions")
    @ResponseBody
    @ApiOperation("上传套题题目")
    public Response uploadQuestion(@RequestBody RecommendedQuestionsDTO recommendedQuestionsDTO) throws OPException{
        Questions questions = new Questions();
        if (StringUtils.isNotBlank(recommendedQuestionsDTO.getAnalysisCode())){
            questions.setAnalysisCode(recommendedQuestionsDTO.getAnalysisCode());
        }
        if (StringUtils.isNotBlank(recommendedQuestionsDTO.getDescription())){
            questions.setDescription(recommendedQuestionsDTO.getDescription());
        }
        if (StringUtils.isNotBlank(recommendedQuestionsDTO.getAnalysisText())){
            questions.setAnalysisText(recommendedQuestionsDTO.getAnalysisText());
        }
        if (StringUtils.isNotBlank(recommendedQuestionsDTO.getDescriptionCode())){
            questions.setCode(recommendedQuestionsDTO.getDescriptionCode());
        }
        if (StringUtils.isNotBlank(recommendedQuestionsDTO.getRecommenedId().toString())){
            questions.setRecommendedId(recommendedQuestionsDTO.getRecommenedId());
        }
        qService.insert(questions);
        EntityWrapper<Questions> wrapper = new EntityWrapper<>();
        List<Questions> questions1 = qService.selectList(wrapper);
        Questions questions2 = questions1.get(questions1.size() - 1);

        Options optionsA = new Options();
        if (StringUtils.isNotBlank(recommendedQuestionsDTO.getOptionA())){
            optionsA.setOptionText(recommendedQuestionsDTO.getOptionA());
        }
        if (StringUtils.isNotBlank(recommendedQuestionsDTO.getOptionACode())){
            optionsA.setOptionCode(recommendedQuestionsDTO.getOptionACode());
        }
        optionsA.setQuestionId(questions2.getId());
        oService.insert(optionsA);

        Options optionsB = new Options();
        if (StringUtils.isNotBlank(recommendedQuestionsDTO.getOptionB())){
            optionsB.setOptionText(recommendedQuestionsDTO.getOptionB());
        }
        if (StringUtils.isNotBlank(recommendedQuestionsDTO.getOptionBCode())){
            optionsB.setOptionCode(recommendedQuestionsDTO.getOptionBCode());
        }
        optionsB.setQuestionId(questions2.getId());
        oService.insert(optionsB);

        Options optionsC = new Options();
        if (StringUtils.isNotBlank(recommendedQuestionsDTO.getOptionC())){
            optionsC.setOptionText(recommendedQuestionsDTO.getOptionC());
        }
        if (StringUtils.isNotBlank(recommendedQuestionsDTO.getOptionCCode())){
            optionsC.setOptionCode(recommendedQuestionsDTO.getOptionCCode());
        }
        optionsC.setQuestionId(questions2.getId());
        oService.insert(optionsC);

        Options optionsD = new Options();
        if (StringUtils.isNotBlank(recommendedQuestionsDTO.getOptionD())){
            optionsD.setOptionText(recommendedQuestionsDTO.getOptionD());
        }
        if (StringUtils.isNotBlank(recommendedQuestionsDTO.getOptionDCode())){
            optionsD.setOptionCode(recommendedQuestionsDTO.getOptionDCode());
        }
        optionsD.setQuestionId(questions2.getId());
        oService.insert(optionsD);

        List<Options> options = oService.selectList(new EntityWrapper<Options>());

        if (recommendedQuestionsDTO.getCorrectAnswer()==4){
            questions2.setAnswerId(options.get(options.size()-1).getId());
        }else if (recommendedQuestionsDTO.getCorrectAnswer()==3){
            questions2.setAnswerId(options.get(options.size()-2).getId());
        }else if (recommendedQuestionsDTO.getCorrectAnswer()==2){
            questions2.setAnswerId(options.get(options.size()-3).getId());
        }else if (recommendedQuestionsDTO.getCorrectAnswer()==1){
            questions2.setAnswerId(options.get(options.size()-4).getId());
        }
        EntityWrapper<Questions> wrapper1 = new EntityWrapper<>();
        wrapper1.eq("id",questions2.getId());
        qService.update(questions2,wrapper1);
        return  ResultGenerator.ok();
    }

    @GetMapping("edit")
    @ApiOperation("返回套题题目修改视图")
    public String edit(@RequestParam Integer id,@RequestParam(required = false) Integer index, @RequestParam Integer questionNum, HttpServletRequest request){
        System.out.println(id);
        //查询套题题目列表
        EntityWrapper<Questions> wrapper = new EntityWrapper<>();
        wrapper.eq("recommended_id",id);
        List<Questions> questionsList = qService.selectList(wrapper);

        //查询套题第index个题目
        if (questionsList.size()>0){
            if (index == null){
                index = 1;
            }
            Questions question = questionsList.get(index-1);
            EntityWrapper<Options> Owrapper = new EntityWrapper<>();
            Owrapper.eq("question_id",question.getId());
            List<Options> options = oService.selectList(Owrapper);
            EditRecommendedVO editQuestionVO = new EditRecommendedVO();
            editQuestionVO.setQuestionId(question.getId());
            if (StringUtils.isNotBlank(question.getAnalysisCode())){
                editQuestionVO.setAnalysisCode(question.getAnalysisCode());
            }
            if (StringUtils.isNotBlank(question.getDescription())){
                editQuestionVO.setDescription(question.getDescription());
            }
            editQuestionVO.setAnswerId(question.getAnswerId());
            editQuestionVO.setOptions(options);
            if (StringUtils.isNotBlank(question.getCode())){
                editQuestionVO.setDescriptionCode(question.getCode());
            }
            if (StringUtils.isNotBlank(question.getAnalysisText())){
                editQuestionVO.setAnalysisText(question.getAnalysisText());
            }
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
            System.out.println(editQuestionVO.toString());
            request.setAttribute("question",editQuestionVO);
        }
        //如果没有题目就返回空对象
        else {request.setAttribute("question",new EditRecommendedVO());}
        request.setAttribute("id",id);
        request.setAttribute("index",index);
        request.setAttribute("questionNum",questionNum);
        request.setAttribute("recommendedId",id);
        return "uploadRecommendedQuestions.btl";
    }



    @PostMapping("updateRecommendedQuestions")
    @ResponseBody
    @ApiOperation("修改套题题目")
    public Response updateQuestions(@RequestBody RecommendedQuestionsEditDTO recommendedQuestionsEditDTO){
        Questions questions = qService.selectById(recommendedQuestionsEditDTO.getId());
        System.out.println(questions);
        if (StringUtils.isNotBlank(recommendedQuestionsEditDTO.getAnalysisText())){
            questions.setAnalysisText(recommendedQuestionsEditDTO.getAnalysisText());
        }
        if (StringUtils.isNotBlank(recommendedQuestionsEditDTO.getCorrectAnswer().toString())){
            questions.setAnswerId(recommendedQuestionsEditDTO.getCorrectAnswer());
        }
        if (StringUtils.isNotBlank(recommendedQuestionsEditDTO.getDescription())){
            questions.setDescription(recommendedQuestionsEditDTO.getDescription());
        }
        //富文本
        if (StringUtils.isNotBlank(recommendedQuestionsEditDTO.getAnalysisCode())){
            questions.setAnalysisCode(recommendedQuestionsEditDTO.getAnalysisCode());
        }

        if (StringUtils.isNotBlank(recommendedQuestionsEditDTO.getDescriptionCode())){
            questions.setCode(recommendedQuestionsEditDTO.getDescriptionCode());
        }

        EntityWrapper<Questions> questionsEntityWrapper = new EntityWrapper<>();
        questionsEntityWrapper.eq("id",questions.getId());
        qService.update(questions,questionsEntityWrapper);

        //修改选项
        EntityWrapper<Options> wrapper = new EntityWrapper<>();
        wrapper.eq("question_id",recommendedQuestionsEditDTO.getId());
        List<Options> options = oService.selectList(wrapper);
        if (StringUtils.isNotBlank(recommendedQuestionsEditDTO.getOptionA())){
            options.get(0).setOptionText(recommendedQuestionsEditDTO.getOptionA());
        }
        if (StringUtils.isNotBlank(recommendedQuestionsEditDTO.getOptionB())){
            options.get(1).setOptionText(recommendedQuestionsEditDTO.getOptionB());
        }
        if (StringUtils.isNotBlank(recommendedQuestionsEditDTO.getOptionC())){
            options.get(2).setOptionText(recommendedQuestionsEditDTO.getOptionC());

        }
        if (StringUtils.isNotBlank(recommendedQuestionsEditDTO.getOptionD())){
            options.get(3).setOptionText(recommendedQuestionsEditDTO.getOptionD());
        }

        //富文本
        if (StringUtils.isNotBlank(recommendedQuestionsEditDTO.getOptionACode())){
            options.get(0).setOptionCode(recommendedQuestionsEditDTO.getOptionACode());
        }
        if (StringUtils.isNotBlank(recommendedQuestionsEditDTO.getOptionB())){
            options.get(1).setOptionCode(recommendedQuestionsEditDTO.getOptionBCode());
        }
        if (StringUtils.isNotBlank(recommendedQuestionsEditDTO.getOptionC())){
            options.get(2).setOptionCode(recommendedQuestionsEditDTO.getOptionCCode());

        }
        if (StringUtils.isNotBlank(recommendedQuestionsEditDTO.getOptionD())){
            options.get(3).setOptionCode(recommendedQuestionsEditDTO.getOptionDCode());
        }


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

}
