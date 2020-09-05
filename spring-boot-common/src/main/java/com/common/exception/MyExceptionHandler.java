package com.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
//@Component
public class MyExceptionHandler implements HandlerExceptionResolver {
    @Nullable
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @Nullable Object handler, Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        if(e instanceof MyException) {
            MyException exp = (MyException) e;
            mv.setStatus(exp.getStatus());
            mv.addObject(exp.getErrorCode(), exp.getMessage());
        } else {
            mv.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            mv.addObject("500", "Internal Server Error");
        }

        return mv;
    }
}
