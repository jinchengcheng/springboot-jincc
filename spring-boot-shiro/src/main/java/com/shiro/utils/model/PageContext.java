package com.shiro.utils.model;

import com.shiro.utils.Pager;

public class PageContext {
    private static ThreadLocal<Pager> context = new ThreadLocal<>();

    public static Pager getPager() {
        return context.get();
    }
    public static void setPager(Pager pager) {
        context.set(pager);
    }

    public static void release() {
        context.remove();
    }
}
