package com.csz.assertor.sys.DTO;

import com.csz.assertor.rest.request.QueryRequest;
import lombok.Data;

/**
 *@Author: luoluo
 *@date: 2019/12/2
 *获取一道推荐题目入参
 */
@Data
public class GetOneQuestionDto extends QueryRequest {

    private Integer exclusiveId;

}
