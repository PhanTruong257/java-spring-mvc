package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    // Trang home
    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUsers = this.userService.getAllUsersByEmail("123@gmail.com");
        System.out.println(arrUsers);
        model.addAttribute("hello", "Phan Trường");
        return "hello";
    }

    // view danh sách tài khoản
    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users1", users);
        return "admin/user/table-user";
    }

    // tạo
    @RequestMapping("/admin/user/create") // GET
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    // Lúc tạo xong thì điều hướng tới trang chủ và render ra danh sách tài khoản
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User hoidanit) {
        System.out.println("run here:  " + hoidanit);
        this.userService.handleSaveUser(hoidanit);
        return "redirect:/admin/user";
    }

    // khi nhấn vào tạo thì sẽ truyền sang id
    @RequestMapping("/admin/user/{id}") // GET
    public String getUserDetailPage(Model model, @PathVariable long id) {
        System.out.println("Check path id = " + id);
        model.addAttribute("id", id);
        model.addAttribute("user", this.userService.getUserById(id));

        return "admin/user/show";
    }

    // update tài khoản: Lấy tài khoản
    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("newUser", this.userService.getUserById(id));

        return "admin/user/update";
    }

    // Xử lý update tài khoản
    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User userForm) {
        // this.userService.handleSaveUser(userForm);
        User currentUser = this.userService.getUserById(userForm.getId());
        if (currentUser != null) {
            currentUser.setFullName(userForm.getFullName());
            currentUser.setAddress(userForm.getAddress());
            currentUser.setEmail(userForm.getEmail());
            currentUser.setPhone(userForm.getPhone());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    // delete Tài Khoản
    @RequestMapping(value = "/admin/user/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(Model model, @PathVariable long id) {
        // TODO: process POST request
        this.userService.deleteUserById(id);
        return "redirect:/admin/user";

    }

}
