package com.mybatis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractController {
    private static Logger logger = LoggerFactory.getLogger(AbstractController.class);

    /**
     * 计算当前页数
     *
     * @param start
     *            偏移量
     * @param length
     *            页大小
     * @return pageNo 当前页数
     */
    public static Integer getPageNo(Integer start, Integer length) {
        Integer pageNo = 1;
        if (++start > 1) {
            pageNo = start / length;
            if (start % length > 0)
                pageNo++;
        }
        return pageNo;
    }
}
