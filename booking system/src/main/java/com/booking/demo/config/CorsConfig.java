package com.booking.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {

    // 解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("------------adding corsMappings----------");
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("*")
        ;
//                .allowedHeaders("*");
    }

    @Bean
    public CORSInterceptor getCORSInterceptor() {
        return new CORSInterceptor();
    }

    // 解决请求头问题
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration cors = registry.addInterceptor(getCORSInterceptor());
        // 拦截配置
        cors.addPathPatterns("/**/**");
    }

    static private class CORSInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
            System.out.println("...Handling CORS ");
            // 允许token
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,token");
            response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
            return true;
        }
    }

}
