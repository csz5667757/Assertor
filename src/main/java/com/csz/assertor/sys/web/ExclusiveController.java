package com.csz.assertor.sys.web;

import com.csz.assertor.rest.ResultGenerator;
import com.csz.assertor.rest.response.Response;
import com.csz.assertor.sys.Vo.ExclusiveVO;
import com.csz.assertor.sys.service.IExclusiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Assertor
 * @Description
 * @Date：2019/12/11
 */

@Controller
@RequestMapping("/sys/exclusive")
public class ExclusiveController {
    @Autowired
    private IExclusiveService service;

    @GetMapping("/select")
    @ResponseBody
    public Response selectExclusive(){
        List<ExclusiveVO> exclusiveVOS = service.selectExclusive();
        return ResultGenerator.table(exclusiveVOS);
    }
}
