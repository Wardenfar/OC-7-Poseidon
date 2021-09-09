package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.error.EntityNotValidException;
import com.nnk.springboot.model.ActionError;
import com.nnk.springboot.service.BidService;
import com.nnk.springboot.util.RedirectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class BidController {

    @Autowired
    BidService bidService;

    @RequestMapping("/bid/list")
    public String home(Model model) {
        model.addAttribute("bids", bidService.findAll());
        return "bid/list";
    }

    @GetMapping("/bid/add")
    public String addBidForm(Model model, @RequestParam Optional<String> msg, @RequestParam Optional<String> error) {
        msg.ifPresent((m) -> model.addAttribute("msg", m));
        model.addAttribute("error", ActionError.fromJsonParam(error));
        model.addAttribute("bid", new Bid());
        model.addAttribute("fields", bidService.listFields());
        return "bid/add";
    }

    @PostMapping("/bid/validate")
    public RedirectView validate(@Valid @ModelAttribute("bid") Bid bid, BindingResult bindingResult) throws EntityNotValidException {
        if(bindingResult.hasErrors()) {
            ActionError error = ActionError.fromBindingResult(bindingResult);
            return RedirectUtil.redirectTo("/bid/add", null, error);
        }
        bidService.save(bid);
        return RedirectUtil.redirectTo("/bid/list", null, null);
    }

    @GetMapping("/bid/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("bid", new Bid());
        return "bid/update";
    }

    @PostMapping("/bid/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid Bid bid,
                            BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid
        return "redirect:/bid/list";
    }

    @GetMapping("/bid/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        return "redirect:/bid/list";
    }
}
