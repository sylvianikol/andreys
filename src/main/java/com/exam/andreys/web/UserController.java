package com.exam.andreys.web;

import com.exam.andreys.model.binding.UserRegisterBindingModel;
import com.exam.andreys.model.service.UserServiceModel;
import com.exam.andreys.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;


    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("exists", false);
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
       if (bindingResult.hasErrors() || !this.passwordsMatch(userRegisterBindingModel)) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegisterBindingModel",
                    bindingResult);
           return "redirect:register";
       }

        boolean isRegistered = this.userService.register(this.modelMapper.map(
                userRegisterBindingModel, UserServiceModel.class));

        if (!isRegistered) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("exists", true);

            return "redirect:register";
        }

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm() {

    }

    private boolean passwordsMatch(UserRegisterBindingModel userRegisterBindingModel) {
        return userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword());

    }
}
