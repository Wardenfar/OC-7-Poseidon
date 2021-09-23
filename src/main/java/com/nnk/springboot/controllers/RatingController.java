package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.error.EntityNotFoundException;
import com.nnk.springboot.error.EntityNotValidException;
import com.nnk.springboot.model.ActionError;
import com.nnk.springboot.service.RatingService;
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
public class RatingController {

    @Autowired
    RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model, @RequestParam Optional<String> msg) {
        msg.ifPresent((m) -> model.addAttribute("msg", m));
        model.addAttribute("ratings", ratingService.findAll());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Model model, @RequestParam Optional<String> msg, @RequestParam Optional<String> error) {
        msg.ifPresent((m) -> model.addAttribute("msg", m));
        model.addAttribute("error", ActionError.fromJsonParam(error));
        model.addAttribute("rating", new Rating());
        model.addAttribute("fields", ratingService.listFields(new Rating()));
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public RedirectView validate(@Valid @ModelAttribute("rating") Rating rating, BindingResult bindingResult) throws EntityNotValidException {
        if (bindingResult.hasErrors()) {
            ActionError error = ActionError.fromBindingResult(bindingResult);
            return RedirectUtil.redirectTo("/rating/add", null, error);
        }
        ratingService.save(rating);
        return RedirectUtil.redirectTo("/rating/list", "Rating successfully added !", null);
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, @RequestParam Optional<String> msg, @RequestParam Optional<String> error) throws EntityNotFoundException {
        Optional<Rating> optional = ratingService.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("Rating not found");
        }
        Rating e = optional.get();
        msg.ifPresent((m) -> model.addAttribute("msg", m));
        model.addAttribute("rating", e);
        model.addAttribute("fields", ratingService.listFields(e));
        model.addAttribute("error", ActionError.fromJsonParam(error));
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public RedirectView updateRating(@PathVariable("id") Integer id, @Valid @ModelAttribute("rating") Rating rating, BindingResult bindingResult) throws EntityNotValidException {
        if (bindingResult.hasErrors()) {
            ActionError error = ActionError.fromBindingResult(bindingResult);
            return RedirectUtil.redirectTo("/rating/update/" + id, null, error);
        }
        rating.setId(id);
        ratingService.save(rating);
        return RedirectUtil.redirectTo("/rating/list", "Rating successfully updated !", null);
    }

    @GetMapping("/rating/delete/{id}")
    public RedirectView deleteRating(@PathVariable("id") Integer id) throws EntityNotFoundException {
        ratingService.delete(getRatingOrThrow(id));
        return RedirectUtil.redirectTo("/rating/list", "Rating successfully deleted !", null);
    }

    private Rating getRatingOrThrow(Integer id) throws EntityNotFoundException {
        Optional<Rating> optional = ratingService.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("Rating not found");
        }
        return optional.get();
    }
}
