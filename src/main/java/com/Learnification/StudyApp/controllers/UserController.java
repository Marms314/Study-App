package com.Learnification.StudyApp.controllers;

import com.Learnification.StudyApp.dtos.UserDto;
import com.Learnification.StudyApp.models.User;
import com.Learnification.StudyApp.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("list-all")
    public String renderManageCardDeckForm(Model model) {

        model.addAttribute("title", "All Users");
        model.addAttribute("users", userRepository.findAll());

        return "user/list";
    }

    @GetMapping("profile/me")
    public String renderCurrentUser(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Optional<User> currentUser = userRepository.findByUserName(username);

        if (currentUser.isPresent()) {
            model.addAttribute("userName", currentUser.get().getUserName());
            model.addAttribute("name", currentUser.get().getName());
            return "user/profile";
        } else {
            return "redirect:/user/list-all";
        }
    }

    @GetMapping("profile/{userId}")
    public String renderUser(Model model, @PathVariable int userId) {

        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            model.addAttribute("userName", user.get().getUserName());
            model.addAttribute("name", user.get().getName());
            return "user/profile";
        } else {
            return "redirect:/user/list-all";
        }
    }

    @GetMapping("registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "user/registration";
    }


}
