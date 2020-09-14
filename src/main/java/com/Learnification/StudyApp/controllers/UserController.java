package com.Learnification.StudyApp.controllers;

import com.Learnification.StudyApp.models.User;
import com.Learnification.StudyApp.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("profile/me")
    public String renderUser(Model model) {
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
            return "redirect:";
        }
    }


}
