package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/foundations")
public class FoundationsController {

    private final InstitutionRepository institutionRepository;


    @GetMapping("")
    public String foundations(Model model) {
        model.addAttribute("foundationsList", institutionRepository.findAll());
        return "/admin/foundations/foundationsList";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {
        model.addAttribute("foundation", institutionRepository.getById(id));
        return "/admin/foundations/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@Valid Institution institution, BindingResult result) {
        if (result.hasErrors()) {
            return "/admin/foundations/edit";
        }
        institutionRepository.save(institution);
        return "redirect:/admin/foundations";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("foundation", new Institution());
        return "/admin/foundations/edit";
    }

    @PostMapping(value = "/add")
    public String add(@Valid Institution institution, BindingResult result) {
        if (result.hasErrors()) {
            return "/admin/foundations/edit";
        }
        institutionRepository.save(institution);
        return "redirect:/admin/foundations";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("foundation", institutionRepository.getById(id));
        return "/admin/foundations/deleteConfirmation";
    }

    @GetMapping(value = "/delete")
    public String delete(@RequestParam Long id) {
        institutionRepository.deleteById(id);
        return "redirect:/admin/foundations";
    }
}

