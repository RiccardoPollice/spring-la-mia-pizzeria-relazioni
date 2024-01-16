package org.learning.springlamiapizzeriacrud.controller;

import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;
    @GetMapping
    public String index (Model model) {
        List<Pizza> pizzaList = pizzaRepository.findAll();
        model.addAttribute("pizzaList", pizzaList);
        return "pizze/list";
    }
    @GetMapping("show/{id}")
    public String show (@PathVariable Integer id, Model model) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            Pizza pizza = result.get();
            model.addAttribute("pizza", pizza);
            return "pizze/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id" + id + "not found");
        }
    }
    @GetMapping("/create")
    public String create (Model model) {
        Pizza pizza = new Pizza();
        model.addAttribute("pizza", pizza);
        return "pizze/create";
    }
    @PostMapping("/create")
    public String pizzeria(@Validated @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pizze/create";
        }
        Pizza savedPizza = pizzaRepository.save(formPizza);
        return "redirect:/pizze/show/" + savedPizza.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit (@PathVariable Integer id, Model model) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("pizza", result.get());
            return "pizze/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with id" + id + "not found");
        }
    }

    @PostMapping ("/edit/{id}")
    public String update(@PathVariable Integer id, @Validated @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            Pizza pizzaToEdit = result.get();
            if (bindingResult.hasErrors()) {
                return "/pizze/edit";
            }
          formPizza.setUrl(pizzaToEdit.getUrl());
          Pizza savedPizza = pizzaRepository.save(formPizza);
          return "redirect:/pizze/show/{id}";

        }

        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id" + id + "not found");
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            pizzaRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("redirectMessage", "Book" + result.get().getName() + "delated");
            return "redirect:/pizze";
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Pizza with id" + id + "not found");
        }
    }

}
