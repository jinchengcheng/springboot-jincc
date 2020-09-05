package com.i18n.util;

import org.springframework.context.MessageSource;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * 改类用于后台获取国际化内容使用
 * @author  jincc
 */
public abstract class AbstractLocaleMessageSource {
	@Resource
	private MessageSource messageSource; // 自动注入此资源对象
	public String getMessage(String key, String... args) {
		return this.messageSource.getMessage(key, args, Locale.getDefault()); 
	}
}
