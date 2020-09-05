package com.shiro.interceptor;

import com.shiro.utils.Pager;
import com.shiro.utils.model.PageContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PagerInterceptor extends HandlerInterceptorAdapter implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this).addPathPatterns("/**");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("11111111111");
        //前台送过来的当前页码
        Integer pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 1);
        //前台当前每页的大小
        Integer pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 10);
        if(pageNo >= 0 && pageSize >= 0) {
            Pager pager = new Pager();
            pager.setPageNo(pageNo);
            pager.setPageSize(pageSize);
            PageContext.setPager(pager);
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //该方法不论preHandle返回false，都会执行
        //todo 处理异常，判断ex是否为null，不为null说明有异常
        try {
            /*if(null == ex) {
                Pager pager = PageContext.getPager();
                request.setAttribute("pager", pager);
            }*/
            super.afterCompletion(request, response, handler, ex);
        } finally {
            PageContext.release();
        }
    }
}
