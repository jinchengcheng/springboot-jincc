package com.shiro.config;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 *
 */
public class MyExceptionHandler implements HandlerExceptionResolver {

    @Nullable
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @Nullable Object o, Exception ex) {
        ModelAndView mv = new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView ();

//        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> attributes = new HashMap<String, Object>();
        if (ex instanceof UnauthenticatedException) {
            attributes.put("code", "1000001");
            attributes.put("msg", "token错误");
        } else if (ex instanceof UnauthorizedException) {
            attributes.put("code", "1000002");
            attributes.put("msg", "用户无权限");
        } else {
            attributes.put("code", "1000003");
            attributes.put("msg", ex.getMessage());
        }
        view.setAttributesMap(attributes);
        mv.setView(view);
        return mv;

        /****
         *
         *  ModelAndView mv = new ModelAndView();
         *  mv.setView(new MappingJackson2JsonView());
         *  if(e instanceof MyException) {
         *      MyException exp = (MyException) e;
         *      mv.setStatus(exp.getStatus());
         *      mv.addObject(exp.getErrorCode(), exp.getMessage());
         *  } else {
         *      mv.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
         *      mv.addObject("500","Internal Server Error");
         *  }
         */
    }
}
