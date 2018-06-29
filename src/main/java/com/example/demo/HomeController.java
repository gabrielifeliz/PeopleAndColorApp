package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

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

    @RequestMapping("/addcolor")
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

/*  @RequestMapping("/updatecolorlist")
    public void updateColorList()
    {
        Color c;
        c = new Color();
        c.setColorName("");
        colorList.save(c);
    }*/

    @PostConstruct
    public void fillTables()
    {
        Color c;

        c = new Color();
        c.setColorName("red");
        colorList.save(c);

        c = new Color();
        c.setColorName("orange");
        colorList.save(c);

        c = new Color();
        c.setColorName("yellow");
        colorList.save(c);

        c = new Color();
        c.setColorName("green");
        colorList.save(c);

        c = new Color();
        c.setColorName("blue");
        colorList.save(c);

        c = new Color();
        c.setColorName("indigo");
        colorList.save(c);

        c = new Color();
        c.setColorName("violet");
        colorList.save(c);
    }
}
