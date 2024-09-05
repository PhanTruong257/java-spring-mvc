package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")

    public String getHomePage(Model model) {

        String test = this.userService.handleHello();
        model.addAttribute("eric", test);
        model.addAttribute("hello", "Phan Trường");
        return "hello";
    }

    @RequestMapping("/create")

    public String getUser(Model model) {

        return "admin/user/create";
    }
}

// @RestController
// public class UserController {
// //DI : dependency injection
// private UserService userService;

// public UserController(UserService userService) {
// this.userService = userService;
// }

// @GetMapping("")
// public String getHomePage() {
// return this.userService.handleHello();
// }
// }
