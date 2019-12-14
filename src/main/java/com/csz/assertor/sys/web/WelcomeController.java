package com.csz.assertor.sys.web;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.csz.assertor.Exception.OPException;
import com.csz.assertor.sys.Vo.index.IndexPieVO;
import com.csz.assertor.sys.entity.ExclusiveGroup;
import com.csz.assertor.sys.entity.TechCategory;
import com.csz.assertor.sys.service.*;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Assertor
 * @Description
 * @Date：2019/12/14
 */
@Controller
@Api(tags = "我的桌面模块")
public class WelcomeController {

    @Autowired
    private IExclusiveGroupService service;

    @Autowired
    private IRecommendedService rService;

    @Autowired
    private ITechCategoryService tService;

    @Autowired
    private ICollectService cService;

    @Autowired
    private IErrorQuestionsService eService;

    public static final int QUERY_COUNT=6;

    @GetMapping("/welcome")
    @ApiOperation("返回桌面视图并加载数据")
    public String welcome(HttpServletResponse response, HttpServletRequest request) throws OPException, ParseException {
        List<IndexPieVO> exclusiveGroup = service.getExclusiveGroup();
        String egLsit = JSON.toJSONString(exclusiveGroup);
        request.setAttribute("egList",egLsit);

        //获取一级专项列表
        List<ExclusiveGroup> exclusiveGroups = service.selectList(new EntityWrapper<>());
        List<String> Elist = Lists.newArrayList();
        for (int i =0 ;i<exclusiveGroups.size();i++){
            ExclusiveGroup group = exclusiveGroups.get(i);
            Elist.add("'"+group.getTitle()+"'");
        }
        request.setAttribute("Elist",Elist.toString());

        //套题模块
        List<IndexPieVO> recommended = rService.getRecommended();
        String rList = JSON.toJSONString(recommended);
        request.setAttribute("rList",rList);

        List<TechCategory> techCategories = tService.selectList(new EntityWrapper<>());
        List<String> Tlist = Lists.newArrayList();
        for (int i =0 ;i<techCategories.size();i++){
            TechCategory techCategory = techCategories.get(i);
            Elist.add("'"+techCategory.getName()+"'");
        }
        request.setAttribute("Tlist",Elist.toString());

        //收藏模块
        List<Integer> Clist = Lists.newArrayList();
        List<Integer> ErrorList = Lists.newArrayList();

        for (int i =QUERY_COUNT;i>-1;i--){
            Date date = new Date(System.currentTimeMillis()-1000*60*60*24*i);
            SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String format = sp.format(date);
            Date date1 = sp.parse(format);
            java.sql.Date sqlDate =  new java.sql.Date(date1.getTime());
            Clist.add(cService.selectOneDayCollect(sqlDate));
            //错题模块
            ErrorList.add(eService.selectOneDayError(sqlDate));
        }
        request.setAttribute("CollectCountList",Clist.toString());
        request.setAttribute("ErrorCountList",ErrorList.toString());


        List<String> Datelist = Lists.newArrayList();
        for (int i =QUERY_COUNT;i>-1;i--){
            Date date = new Date(System.currentTimeMillis()-1000*60*60*24*i);
            SimpleDateFormat sp = new SimpleDateFormat("MM-dd");
            String format = sp.format(date);
            Datelist.add("'"+format+"'");
        }
        request.setAttribute("DateList",Datelist.toString());

        return "welcome.btl";
    }

}
