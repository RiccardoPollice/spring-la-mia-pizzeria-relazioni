package org.learning.springlamiapizzeriacrud.controller;

import org.learning.springlamiapizzeriacrud.model.Pizzeria;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;
    @GetMapping
    public String index(Model model){
        List<Pizzeria> pizzeriaList = pizzaRepository.findAll();
        model.addAttribute("pizzeriaList", pizzeriaList);
        return "pizze/list";
    }
}
