package com.i18n.controller;

import com.i18n.util.AbstractLocaleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试国际化
 * @author  jincc
 */
@Controller
public class I18nTestControll extends AbstractLocaleMessageSource {
    /*@GetMapping("/i18n")
    public String index() {
        return "message_index.html";
    }*/
    @RequestMapping("/getI18n")
    @ResponseBody
    public String getI18n() {
        return this.getMessage("welcome.msg");
    }
}
