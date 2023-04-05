package tech.itmentors.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tech.itmentors.crud.model.Role;
import tech.itmentors.crud.model.User;
import tech.itmentors.crud.security.UserDetailsImpl;
import tech.itmentors.crud.service.RoleService;
import tech.itmentors.crud.service.UserService;

import java.util.Collection;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public String showUserInfo(ModelMap model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userDetails", userDetails);
        return "user";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String admin(ModelMap model) {
        List<User> users = userService.findAll();
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Role> roles = roleService.findAll();
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roles);
        model.addAttribute("users", users);
        model.addAttribute("userDetails", userDetails);
        return "admin";
    }

    @PostMapping("user/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String create(@ModelAttribute("user") User user, @RequestParam("user-roles") Collection<Role> roles, BindingResult result) {
        user.setRoles(roles);
        userService.save(user);
        return "redirect:/admin";
    }

    @PutMapping("user/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String update(@ModelAttribute("user") User user, @RequestParam("user-roles") Collection<Role> roles) {
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("user/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@PathVariable long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
