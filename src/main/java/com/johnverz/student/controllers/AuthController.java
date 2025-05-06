package com.johnverz.student.controllers;

import com.johnverz.student.services.AppUserService;
import com.johnverz.student.models.AppUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    AppUserService appUserService;

//    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
//    public String login(@RequestParam(required = false) String username, @RequestParam(required = false) String password, HttpServletRequest request) {
//        if(request.getMethod().equalsIgnoreCase("GET")) {
//            return "login";
//        } else if (request.getMethod().equalsIgnoreCase("POST")) {
//
//
//        }
//        return "";
//    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new AppUser());

        return "login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("user") @Valid AppUser formUser, BindingResult bindingResult, HttpSession session, Model model){
        if(bindingResult.hasErrors()){
            return "login";
        }

        //authenticate
        AppUser foundUser = appUserService.findByUsername(formUser.getUsername());
        if(foundUser != null && new BCryptPasswordEncoder().matches(formUser.getPassword(), foundUser.getPassword())){
            session.setAttribute("user", foundUser);
            return "redirect:/";
        }else{
            String error ="Invalid credentials";
            model.addAttribute("error", error);
        }


        return "login";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }


}
