package com.johnverz.student;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

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
    public String authenticate(@ModelAttribute("user") @Valid AppUser user, BindingResult bindingResult, HttpSession session, Model model){
        if(bindingResult.hasErrors()){
            return "login";
        }

        //authenticate
        String dummyUserName ="smoshi02";
        String password ="ilovejasmin";

        if(user.getUsername().equals(dummyUserName) && user.getPassword().equals(password)){
            //yehey you are not a hacker. I can now let you in access me
            //just enjoy, promise?
            //initiate a session
            session.setAttribute("user", user);

            return "redirect:/";
        }

        String error ="Hacker ka noh? Tawag na ako ng pulis";
        model.addAttribute("error", error);

        return "login";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }


}
