package com.jin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ModelTest1 {
    @RequestMapping("/m1/t1")
    public String test(Model model) {
        model.addAttribute("msg","ModelTest1");
        return "redirect:/index.jsp";
    }
}
