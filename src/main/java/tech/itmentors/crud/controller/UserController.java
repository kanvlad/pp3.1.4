package tech.itmentors.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tech.itmentors.crud.model.Role;
import tech.itmentors.crud.model.User;
import tech.itmentors.crud.security.UserDetailsImpl;
import tech.itmentors.crud.service.RoleService;
import tech.itmentors.crud.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/user")
    public String showUserInfo(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        model.addAttribute("user", user);

        return "user-info";
    }

    @GetMapping("/admin")
    public String showAllUsers(ModelMap model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("user/add-page")
    public String addUserPage(@ModelAttribute User user, ModelMap model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("allRoles", roles);
        return "user-add";
    }

    @PostMapping("user/save")
    public String create(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("user/update-page/{id}")
    public String updateUserPage(ModelMap model, @PathVariable("id") long id) {
        User user = userService.findById(id);
        List<Role> roles = roleService.findAll();
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("allRoles", roles);
        }
        return "user-update";
    }

    @PutMapping("user/update")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("user/delete/{id}")
    public String delete(@PathVariable long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
