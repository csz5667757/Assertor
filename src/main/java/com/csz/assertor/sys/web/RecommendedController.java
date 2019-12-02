package com.csz.assertor.sys.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.csz.assertor.rest.ResultGenerator;
import com.csz.assertor.rest.response.Response;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    public String addRecommended(HttpServletRequest request){
        Recommended recommended = new Recommended();
        recommended.setTitle("demo");
        service.insert(recommended);
        List<Recommended> recommendedList = service.selectList(new EntityWrapper<>());
        Recommended recommended1 = recommendedList.get(recommendedList.size() - 1);
        request.setAttribute("id",recommended1.getId());
        List<TechCategory> techCategoryList = tcService.selectList(new EntityWrapper<>());
        request.setAttribute("tcg",techCategoryList);
        return "uploadRecommended.btl";
    }

    @GetMapping("upload")
    @ApiOperation("返回题目上传套题题目视图")
    public String upload(@RequestParam Integer recommendedId , HttpServletRequest request){
        request.setAttribute("recommendedId",recommendedId);
        return "uploadRecommendedQuestions.btl";
    }

    @PostMapping("uploadRecommendedQuestions")
    @ResponseBody
    @ApiOperation("上传套题题目")
    public Response uploadQuestion(@RequestBody RecommendedQuestionsDTO recommendedQuestionsDTO){
        Questions questions = new Questions();
        questions.setAnalysisCode(recommendedQuestionsDTO.getAnalysisCode());
        questions.setDescription(recommendedQuestionsDTO.getDescription());
        questions.setRecommendedId(recommendedQuestionsDTO.getRecommenedId());
        qService.insert(questions);
        EntityWrapper<Questions> wrapper = new EntityWrapper<>();
        List<Questions> questions1 = qService.selectList(wrapper);
        Questions questions2 = questions1.get(questions1.size() - 1);

        Options optionsA = new Options();
        optionsA.setOptionText(recommendedQuestionsDTO.getOptionA());
        optionsA.setQuestionId(questions2.getId());
        oService.insert(optionsA);

        Options optionsB = new Options();
        optionsB.setOptionText(recommendedQuestionsDTO.getOptionB());
        optionsB.setQuestionId(questions2.getId());
        oService.insert(optionsB);

        Options optionsC = new Options();
        optionsC.setOptionText(recommendedQuestionsDTO.getOptionC());
        optionsC.setQuestionId(questions2.getId());
        oService.insert(optionsC);

        Options optionsD = new Options();
        optionsD.setOptionText(recommendedQuestionsDTO.getOptionD());
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
    public String edit(@RequestParam Integer id, HttpServletRequest request){
        Questions question = qService.selectById(id);
        EntityWrapper<Options> Owrapper = new EntityWrapper<>();
        Owrapper.eq("question_id",question.getId());
        List<Options> options = oService.selectList(Owrapper);
        EditRecommendedVO editQuestionVO = new EditRecommendedVO();
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
        request.setAttribute("question",editQuestionVO);
        request.setAttribute("id",id);
        return "editQuestions.btl";
    }

    @PostMapping("updateRecommendedQuestions")
    @ResponseBody
    @ApiOperation("修改套题题目")
    public Response updateQuestions(@RequestBody RecommendedQuestionsEditDTO recommendedQuestionsEditDTO){
        Questions questions = qService.selectById(recommendedQuestionsEditDTO.getId());
        questions.setAnalysisCode(recommendedQuestionsEditDTO.getAnalysisCode());
        questions.setAnswerId(recommendedQuestionsEditDTO.getCorrectAnswer());
        questions.setDescription(recommendedQuestionsEditDTO.getDescription());
        EntityWrapper<Questions> questionsEntityWrapper = new EntityWrapper<>();
        questionsEntityWrapper.eq("id",questions.getId());
        qService.update(questions,questionsEntityWrapper);

        //修改选项
        EntityWrapper<Options> wrapper = new EntityWrapper<>();
        wrapper.eq("question_id",recommendedQuestionsEditDTO.getId());
        List<Options> options = oService.selectList(wrapper);
        options.get(0).setOptionText(recommendedQuestionsEditDTO.getOptionA());
        options.get(1).setOptionText(recommendedQuestionsEditDTO.getOptionB());
        options.get(2).setOptionText(recommendedQuestionsEditDTO.getOptionC());
        options.get(3).setOptionText(recommendedQuestionsEditDTO.getOptionD());

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
