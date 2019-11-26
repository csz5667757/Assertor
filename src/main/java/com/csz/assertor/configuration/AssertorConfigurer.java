package com.csz.assertor.configuration;

import com.csz.assertor.HttpLoggingFilter;
import com.ibeetl.starter.BeetlTemplateCustomize;
import org.beetl.core.GroupTemplate;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Assertor
 * @Description
 * @Date：2019/11/24
 */

@Configuration
public class AssertorConfigurer {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean<HttpLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new HttpLoggingFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("HttpLoggingFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    //beetl模板引擎
    @Bean
    public BeetlTemplateCustomize beetlTemplateCustomize(){
        return new BeetlTemplateCustomize(){
            public void customize(GroupTemplate groupTemplate){
            }
        };
    }







}
