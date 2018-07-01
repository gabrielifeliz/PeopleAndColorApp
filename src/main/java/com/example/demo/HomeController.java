package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class HomeController {
    @Autowired
    PersonRepository colorOwners;

    @Autowired
    ColorRepository colorList;

    @RequestMapping("/")
    public String index(Model model)
    {
        model.addAttribute("colorlist",colorList.findAll());
        model.addAttribute("ownerlist",colorOwners.findAll());
        return "index";
    }

    @GetMapping("/addcolor")
    public String addPersonColorInfo(Model model)
    {
        model.addAttribute("person", new Person());
        model.addAttribute("colorlist",colorList.findAll());
        return "color";
    }

    @PostMapping("/savecolor")
    public String savePersonColorInfo(@ModelAttribute("person") Person person)
    {
        colorOwners.save(person);
        return "redirect:/";
    }

  @GetMapping("/updatecolorlist")
    public String updateColorList(Model model)
    {
        model.addAttribute("color", new Color());
        return "colorslist";
    }

  @PostMapping("/savecolorlist")
    public String updateColorList(@ModelAttribute("color") Color color)
    {
        colorList.save(color);
        return "redirect:/addcolor";
    }

    @PostConstruct
    public void fillTables()
    {
        Color c;
        ArrayList<String> someColors = new ArrayList<>(Arrays
                .asList("White", "Black", "Grey", "Yellow", "Red",
                "Blue", "Green", "Brown", "Pink", "Orange", "Purple"));

        for (String s: someColors) {
            c = new Color();
            c.setColorName(s);
            colorList.save(c);
        }
    }

    @RequestMapping("/search")
    public String showSearchResults(HttpServletRequest request, Model model)
    {
        //Get the search string from the result form
        String searchString = request.getParameter("search");
        model.addAttribute("search", searchString);
        model.addAttribute("ownerlist",
                colorOwners.findAllByMyNameContainingIgnoreCase(searchString));
        return "index";
    }
}
