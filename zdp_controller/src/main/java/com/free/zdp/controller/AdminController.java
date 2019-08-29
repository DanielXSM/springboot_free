package com.free.zdp.controller;

import com.free.zdp.common.BaseController;
import com.free.zdp.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AdminController extends BaseController {
    private static final Logger Log= LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;
    @GetMapping("/")
    public @ResponseBody
    String index() {
        return "hello world...";
    }

    @GetMapping("/cs")
    public @ResponseBody
    String cs() {
        return adminService.sayHello();
    }
}
