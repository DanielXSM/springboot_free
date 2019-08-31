package com.free.zdp.controller;

import com.free.zdp.common.BaseController;
import com.free.zdp.service.AdminService;
import com.free.zdp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AdminController extends BaseController {
    private static final Logger LOGGER= LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;


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

    @GetMapping("/welcome")
    public @ResponseBody
    String name(String name) {

        return adminService.welcome(name);
    }

    /**
     * 通过user 访问
     * @param name
     * @return
     */
    @GetMapping("/user")
    public @ResponseBody
    String show(String name) {

        return userService.show(name);
    }


    /**
     * 通过user 访问
     * @param id
     * @return
     */
    @GetMapping("/users")
    public @ResponseBody
    String shows(int id) {

        return userService.shows(id);
    }
}
