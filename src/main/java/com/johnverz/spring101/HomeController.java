package com.johnverz.spring101;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private String[] ngolors;

    public HomeController(){
        ngolors = new String[3];
        ngolors[0] = "mink";
        ngolors[1] = "miolet";
        ngolors[2] = "mlue";
    }

    @GetMapping("/wow")
    public String index(Model model){
        model.addAttribute("ngulays", ngolors);
        return "index";
    }
}
