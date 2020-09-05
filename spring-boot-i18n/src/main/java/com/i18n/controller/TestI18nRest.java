package com.i18n.controller;

import com.i18n.util.AbstractLocaleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestI18nRest extends AbstractLocaleMessageSource {

    @GetMapping("/getI18nTwo")
    public String getI18nTwo() {
        return this.getMessage("welcome.msg");
    }
}
