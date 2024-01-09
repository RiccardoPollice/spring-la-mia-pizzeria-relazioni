package org.learning.springlamiapizzeriacrud.controller;

import org.learning.springlamiapizzeriacrud.model.Pizzeria;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model) {
        List<Pizzeria> pizzeriaList = pizzaRepository.findAll();
        model.addAttribute("pizzeriaList", pizzeriaList);
        return "pizze/list";
    }

    @GetMapping
    public String show (@PathVariable String name, Model model) {
        Optional<Pizzeria> result = pizzaRepository.findById(name);
        if (result.isPresent()) {
            model.addAttribute("pizzeria", pizzeria);
            return "pizze/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizzeria" + name + "not found");
        }
    }

}
