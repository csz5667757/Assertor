package com.csz.assertor;

import com.alibaba.fastjson.JSON;
import com.csz.assertor.Exception.OPException;
import com.csz.assertor.rest.ResultEnum;
import com.csz.assertor.rest.ResultGenerator;
import com.csz.assertor.rest.response.Response;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 系统全局异常
 */
@RestControllerAdvice
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object o, Exception ex) {
        Response entity = null;
        //业务接口的异常，如用户账号或密码错误
        if (ex instanceof OPException){
            int code = ((OPException)ex).getCode();
            entity = ResultGenerator.failure(code,ex.getMessage());
            logger.info(ex.getMessage());

            //返回错误信息
            request.setAttribute("errors",ex.getMessage());
            try {
                response.sendRedirect("login?error=1");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //请求了不存在的接口
        else if(ex instanceof NoHandlerFoundException){
            ResultEnum error = ResultEnum.NOT_FOUND;
            entity = ResultGenerator.failure(error.getCode(),error.getMessage());
        }
        //servlet异常
        else if (ex instanceof ServletException){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        //请求参数异常
        else if (ex instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException c = (MethodArgumentNotValidException) ex;
            List<ObjectError> errors =c.getBindingResult().getAllErrors();
            Map emap = Maps.newHashMap();
            for (ObjectError objectError : errors) {
                if (objectError instanceof FieldError){
                        FieldError fieldError =(FieldError) objectError;
                        emap.put(fieldError.getField(),fieldError.getDefaultMessage());
                }
            }
            ResultEnum error =ResultEnum.BAD_REQUEST;
            entity = ResultGenerator.failure(error.getMessage(),error.getCode(),emap);
        }
        //请求参数异常
        else if (ex instanceof ConstraintViolationException){
                Set<ConstraintViolation<?>> set =((ConstraintViolationException) ex).getConstraintViolations();
                Iterator<ConstraintViolation<?>> it =set.iterator();
                Map eMap =Maps.newHashMap();
                while (it.hasNext()){
                    ConstraintViolation<?> cv = it.next();
                    String property = cv.getPropertyPath().toString();
                    eMap.put(property.split("\\.")[1], cv.getMessage());
                }
            ResultEnum error = ResultEnum.BAD_REQUEST;
            entity = ResultGenerator.failure(error.getMessage(),error.getCode(), eMap);
        }
        // 解析请求消息体异常
        else if (ex instanceof HttpMessageNotReadableException) {
            HttpMessageNotReadableException tmpEx = (HttpMessageNotReadableException) ex;
            ResultEnum error = ResultEnum.BAD_REQUEST;
            entity = ResultGenerator.failure(error.getMessage(),error.getCode(),  tmpEx.getMessage());
        }
        // 请求消息数据类型异常
        else if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException tmpEx = ((MethodArgumentTypeMismatchException) ex);
            Map eMap = Maps.newHashMap();
            eMap.put(tmpEx.getName(), String.format("请求参数 [%s] 类型错误", tmpEx.getValue()));
            ResultEnum error = ResultEnum.BAD_REQUEST;
            entity = ResultGenerator.failure( error.getMessage(),error.getCode(), eMap);
        }
        // 上传文件异常
        else if (ex instanceof MaxUploadSizeExceededException) {
            entity = ResultGenerator.failure("上传文件超出最大值");
        }
        // 业务异常
        else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            String message;
            if (o instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) o;
                message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                        request.getRequestURI(),
                        handlerMethod.getBean().getClass().getName(),
                        handlerMethod.getMethod().getName(),
                        ex.getMessage());
                entity = ResultGenerator.failure();
            } else {
                message = ex.getMessage();
                entity = ResultGenerator.failure();
            }
            logger.error(message, ex);
        }

        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(entity));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return new ModelAndView();
    }
}
