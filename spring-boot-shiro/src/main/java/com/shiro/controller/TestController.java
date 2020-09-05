package com.shiro.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.shiro.pojo.SysRole;
import com.shiro.service.TestService;
import com.shiro.utils.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController  extends  AbstractController{

    @Autowired
    private TestService testService;

    @GetMapping("/testSearchar")
    public ResponseModel<List<SysRole>> searchPage(@RequestParam(value = "length") Integer length,
                                             @RequestParam(value = "start") Integer start,
                                             @RequestParam(value = "name") String name) {
        Page<SysRole> page = new Page<SysRole> (start, length);
        try {
            return new ResponseModel<List<SysRole>>("success","查询成功", testService.sysRoleForPageService(page, start, name));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseModel<List<SysRole>>("error", "错误");
        }
    }
}
