package com.nnk.springboot.controllers;

import java.util.Optional;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.error.EntityNotFoundException;
import com.nnk.springboot.error.EntityNotValidException;
import com.nnk.springboot.model.ActionError;
import com.nnk.springboot.service.CurvePointService;
import com.nnk.springboot.util.RedirectUtil;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CurvePointController {

    @Autowired
    CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        model.addAttribute("curvePoints", curvePointService.findAll());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(Model model, @RequestParam Optional<String> msg, @RequestParam Optional<String> error) {
        msg.ifPresent((m) -> model.addAttribute("msg", m));
        model.addAttribute("error", ActionError.fromJsonParam(error));
        model.addAttribute("curvePoint", new CurvePoint());
        model.addAttribute("fields", curvePointService.listFields(new CurvePoint()));
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public RedirectView validate(@Valid @ModelAttribute("curvePoint") CurvePoint curvePoint, BindingResult bindingResult) throws EntityNotValidException {
        if (bindingResult.hasErrors()) {
            ActionError error = ActionError.fromBindingResult(bindingResult);
            return RedirectUtil.redirectTo("/curvePoint/add", null, error);
        }
        curvePointService.save(curvePoint);
        return RedirectUtil.redirectTo("/curvePoint/list", "CurvePoint succesfully added !", null);
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, @RequestParam Optional<String> msg, @RequestParam Optional<String> error) throws EntityNotFoundException {
        Optional<CurvePoint> optional = this.curvePointService.findById(id);
        if(optional.isEmpty()) {
            throw new EntityNotFoundException("CurvePoint not found");
        }
        CurvePoint e = optional.get();
        msg.ifPresent((m) -> model.addAttribute("msg", m));
        model.addAttribute("error", ActionError.fromJsonParam(error));
        model.addAttribute("curvePoint", e);
        model.addAttribute("fields", curvePointService.listFields(e));
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public RedirectView updateBid(@PathVariable("id") Integer id, @ModelAttribute("CurvePoint") @Valid CurvePoint curvePoint, BindingResult bindingResult) throws EntityNotValidException {
        if (bindingResult.hasErrors()) {
            ActionError error = ActionError.fromBindingResult(bindingResult);
            return RedirectUtil.redirectTo("/curvePoint/update/" + id, null, error);
        }
        curvePoint.setId(id);
        this.curvePointService.save(curvePoint);
        return RedirectUtil.redirectTo("/curvePoint/list", "CurvePoint succesfully updated !", null);
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        return "redirect:/curvePoint/list";
    }
}
