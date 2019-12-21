package com.github.flyingglass.phoenix.boot;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.github.flyingglass.phoenix.api.PhoenixUpsert;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author fly
 * date 2019/12/16 15:51
 * desc:
 */
@Configuration
public class PhoenixAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ISqlInjector.class)
    ISqlInjector sqlInjector() {
        return new DefaultSqlInjector() {

            @Override
            public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
                List<AbstractMethod> methodList = super.getMethodList(mapperClass);
                methodList.add(new PhoenixUpsert());
                return methodList;
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean(PaginationInterceptor.class)
    PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor().setDialectClazz(
                "com.github.flyingglass.phoenix.api.PhoenixDialect"
        );
    }

}
