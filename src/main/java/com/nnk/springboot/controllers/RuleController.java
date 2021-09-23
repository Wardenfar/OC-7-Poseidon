package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.error.EntityNotFoundException;
import com.nnk.springboot.error.EntityNotValidException;
import com.nnk.springboot.model.ActionError;
import com.nnk.springboot.service.RuleService;
import com.nnk.springboot.util.RedirectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class RuleController {
    @Autowired
    RuleService ruleService;

    @RequestMapping("/rule/list")
    public String home(Model model, @RequestParam Optional<String> msg) {
        msg.ifPresent((m) -> model.addAttribute("msg", m));
        model.addAttribute("rules", ruleService.findAll());
        return "rule/list";
    }

    @GetMapping("/rule/add")
    public String addRuleForm(Model model, @RequestParam Optional<String> msg, @RequestParam Optional<String> error) {
        msg.ifPresent((m) -> model.addAttribute("msg", m));
        model.addAttribute("error", ActionError.fromJsonParam(error));
        model.addAttribute("rule", new Rule());
        model.addAttribute("fields", ruleService.listFields(new Rule()));
        return "rule/add";
    }

    @PostMapping("/rule/validate")
    public RedirectView validate(@Valid @ModelAttribute("rule") Rule rule, BindingResult bindingResult) throws EntityNotValidException {
        if (bindingResult.hasErrors()) {
            ActionError error = ActionError.fromBindingResult(bindingResult);
            return RedirectUtil.redirectTo("/rule/add", null, error);
        }
        ruleService.save(rule);
        return RedirectUtil.redirectTo("/rule/list", "Rule successfully added !", null);
    }

    @GetMapping("/rule/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, @RequestParam Optional<String> msg, @RequestParam Optional<String> error) throws EntityNotFoundException {
        Optional<Rule> optional = ruleService.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("Rule not found");
        }
        Rule e = optional.get();
        msg.ifPresent((m) -> model.addAttribute("msg", m));
        model.addAttribute("rule", e);
        model.addAttribute("fields", ruleService.listFields(e));
        model.addAttribute("error", ActionError.fromJsonParam(error));
        return "rule/update";
    }

    @PostMapping("/rule/update/{id}")
    public RedirectView updateRule(@PathVariable("id") Integer id, @Valid @ModelAttribute("rule") Rule rule, BindingResult bindingResult) throws EntityNotValidException {
        if (bindingResult.hasErrors()) {
            ActionError error = ActionError.fromBindingResult(bindingResult);
            return RedirectUtil.redirectTo("/rule/update/" + id, null, error);
        }
        rule.setId(id);
        ruleService.save(rule);
        return RedirectUtil.redirectTo("/rule/list", "Rule successfully updated !", null);
    }

    @GetMapping("/rule/delete/{id}")
    public RedirectView deleteRule(@PathVariable("id") Integer id) throws EntityNotFoundException {
        ruleService.delete(getRuleOrThrow(id));
        return RedirectUtil.redirectTo("/rule/list", "Rule successfully deleted !", null);
    }

    private Rule getRuleOrThrow(Integer id) throws EntityNotFoundException {
        Optional<Rule> optional = ruleService.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("Rule not found");
        }
        return optional.get();
    }
}
