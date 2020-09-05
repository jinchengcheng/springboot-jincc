package com.common.inteceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

//@Component
//@Configuration
public class UrlRestrictInteceptor extends WebMvcConfigurerAdapter implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(UrlRestrictInteceptor.class);

    private static Set<String> RESTRICT_URL = new HashSet<>();
    static {
        RESTRICT_URL.add("需要填写的url");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //再进入之前需要做某些事
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        //视图渲染之前，需要做什么，如：向model中放入数据等等。
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        //视图已经渲染完成之后，需要清理什么，如：。。。
    }
}
