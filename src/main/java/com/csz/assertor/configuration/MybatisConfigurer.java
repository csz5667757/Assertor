package com.csz.assertor.configuration;

import com.baomidou.mybatisplus.mapper.ISqlInjector;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Assertor
 * @Description
 * @Date：2019/11/24
 */

@Configuration
public class MybatisConfigurer {
    @Bean
    public ISqlInjector iSqlInjector(){
        return new LogicSqlInjector();
    }
}
