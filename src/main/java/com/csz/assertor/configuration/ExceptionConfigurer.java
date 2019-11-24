package com.csz.assertor.configuration;

import com.csz.assertor.GlobalExceptionResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Assertor
 * @Description 全局异常配置
 * @Date：2019/11/24
 */

@Configuration
public class ExceptionConfigurer implements WebMvcConfigurer {

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new GlobalExceptionResolver());
    }

}
