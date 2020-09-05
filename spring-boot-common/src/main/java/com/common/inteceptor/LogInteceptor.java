package com.common.inteceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.NamedThreadLocal;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

//@Component
//@Configuration
public class LogInteceptor extends WebMvcConfigurerAdapter implements HandlerInterceptor {

    private static final ThreadLocal<Long> startTimeTheadLocal = new NamedThreadLocal<>("ThreadLocal StartTime");

    private static final Logger logger = LoggerFactory.getLogger(LogInteceptor.class);



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(logger.isInfoEnabled()) {
            long beginTime = System.currentTimeMillis();
            startTimeTheadLocal.set(beginTime);
            StringBuffer sb = new StringBuffer();
            sb.append("URI: ").append(request.getRequestURI()).append("\\n开始计时： ")
            .append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(beginTime));
            logger.info(sb.toString());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        if(null != modelAndView) {
            logger.info("viewname: " + modelAndView.getViewName());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        if(logger.isInfoEnabled()){
            long beginTime = startTimeTheadLocal.get();
            long endTime = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            StringBuffer sb = new StringBuffer();
            sb.append("URI: ").append(request.getRequestURI()).append("\\n Controller(handle): ")
            .append(handler).append("\\n开始计时：").append(sdf.format(beginTime))
            .append("\\n结束时间： ").append(sdf.format(endTime))
            .append("\\n耗时： ").append(endTime - beginTime).append("ms")
            .append("\\n最大内存： ").append(Runtime.getRuntime().maxMemory()/1024/1024).append("M")
            .append("\\n已分配内存：").append(Runtime.getRuntime().totalMemory()/1024/1024).append("M")
            .append("\\n已分配内存的剩余空间： ").append(Runtime.getRuntime().freeMemory()/1024/1024).append("M")
            .append("\\n最大可用内存： ").append(Runtime.getRuntime().freeMemory()/1024/1024).append("M");
            logger.info(sb.toString());
        }

    }
}
