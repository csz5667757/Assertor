package com.csz.assertor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
@MapperScan("com.csz.assertor.sys.mapper")
public class AssertorApplication extends WebMvcConfigurationSupport {


    public static void main(String[] args) {
        SpringApplication.run(AssertorApplication.class, args);
    }


    //添加静态文件访问
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        registry.addResourceHandler("/static/lib/layui/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/lib/layui/");
        //swagger拦截
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

    //添加登陆状态验证
//	@Override
//	protected void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login","/sys/user/register","/static/**");
//		super.addInterceptors(registry);
//	}
}

