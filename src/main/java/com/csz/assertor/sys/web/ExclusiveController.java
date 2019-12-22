package com.csz.assertor.sys.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.csz.assertor.rest.ResultGenerator;
import com.csz.assertor.rest.response.Response;
import com.csz.assertor.sys.DTO.ExclusiveDTO;
import com.csz.assertor.sys.DTO.ExclusiveEditDTO;
import com.csz.assertor.sys.Vo.ExclusiveVO;
import com.csz.assertor.sys.entity.Exclusive;
import com.csz.assertor.sys.entity.ExclusiveGroup;
import com.csz.assertor.sys.service.IExclusiveGroupService;
import com.csz.assertor.sys.service.IExclusiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Assertor
 * @Description
 * @Date：2019/12/11
 */

@Controller
@Api(tags = "专项类目模块")
@RequestMapping("/sys/exclusive")
public class ExclusiveController {
    @Autowired
    private IExclusiveService service;

    @Autowired
    private IExclusiveGroupService egService;

    @GetMapping("/select")
    @ResponseBody
    public Response selectExclusive() {
        List<ExclusiveVO> exclusiveVOS = service.selectExclusive();
        return ResultGenerator.table(exclusiveVOS);
    }

    @GetMapping("/add")
    public String addExclusive(HttpServletRequest request) {
        List<ExclusiveGroup> exclusiveGroupList = egService.selectList(new EntityWrapper<>());
        request.setAttribute("egList", exclusiveGroupList);
        return "addExclusive.btl";
    }

    @PostMapping("addExclusive")
    @ApiOperation("添加专项")
    @ResponseBody
    @Transactional
    public Response add(@RequestBody ExclusiveDTO exclusiveDTO) {
        if (exclusiveDTO.getId() == null) {
            ExclusiveGroup exclusiveGroup = new ExclusiveGroup();
            exclusiveGroup.setTitle(exclusiveDTO.getTitle());
            egService.insert(exclusiveGroup);
        } else {
            Exclusive exclusive = new Exclusive();
            exclusive.setCategoryGroupId(exclusiveDTO.getId());
            exclusive.setExclusiveTitle(exclusiveDTO.getTitle());
            service.insert(exclusive);
        }
        return ResultGenerator.ok("添加成功！");
    }

    @GetMapping("edit")
    public String edit(@RequestParam String title, @RequestParam String id, @RequestParam String pid, HttpServletRequest request) {
        List<ExclusiveGroup> exclusiveGroupList = egService.selectList(new EntityWrapper<>());
        request.setAttribute("egList", exclusiveGroupList);
        request.setAttribute("title", title);
        request.setAttribute("pid", Integer.valueOf(pid));
        request.setAttribute("id", Integer.valueOf(id));
        return "editExclusive.btl";
    }

    @PostMapping("editExclusive")
    @ApiOperation("修改专项")
    @ResponseBody
    @Transactional
    public Response editExclusive(@RequestBody ExclusiveEditDTO exclusiveEditDTO) {
        if (Integer.valueOf(exclusiveEditDTO.getPid()) != 0) {
            Exclusive exclusive = new Exclusive();
            if (StringUtils.isNotBlank(exclusiveEditDTO.getTitle())) {
                exclusive.setExclusiveTitle(exclusiveEditDTO.getTitle());
            }
            exclusive.setCategoryGroupId(Integer.valueOf(exclusiveEditDTO.getPid()));
            EntityWrapper<Exclusive> wrapper = new EntityWrapper<>();
            wrapper.eq("id",Integer.valueOf(exclusiveEditDTO.getId())-1000);
            service.update(exclusive,wrapper);
        }
        else {
            ExclusiveGroup exclusiveGroup = new ExclusiveGroup();
            exclusiveGroup.setTitle(exclusiveEditDTO.getTitle());
            EntityWrapper<ExclusiveGroup> wrapper = new EntityWrapper<>();
            wrapper.eq("id",Integer.valueOf(exclusiveEditDTO.getId()));
            egService.update(exclusiveGroup,wrapper);
        }
        return ResultGenerator.ok("修改成功！");
    }

    @GetMapping("/move")
    public String moveExclusive(){
        return "moveExclusive.btl";
    }


}
