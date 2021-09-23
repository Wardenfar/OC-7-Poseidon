package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.error.EntityNotFoundException;
import com.nnk.springboot.error.EntityNotValidException;
import com.nnk.springboot.model.ActionError;
import com.nnk.springboot.service.BidService;
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
public class BidController {

    @Autowired
    BidService bidService;

    @RequestMapping("/bid/list")
    public String home(Model model, @RequestParam Optional<String> msg) {
        msg.ifPresent((m) -> model.addAttribute("msg", m));
        model.addAttribute("bids", bidService.findAll());
        return "bid/list";
    }

    @GetMapping("/bid/add")
    public String addBidForm(Model model, @RequestParam Optional<String> msg, @RequestParam Optional<String> error) {
        msg.ifPresent((m) -> model.addAttribute("msg", m));
        model.addAttribute("error", ActionError.fromJsonParam(error));
        model.addAttribute("bid", new Bid());
        model.addAttribute("fields", bidService.listFields(new Bid()));
        return "bid/add";
    }

    @PostMapping("/bid/validate")
    public RedirectView validate(@Valid @ModelAttribute("bid") Bid bid, BindingResult bindingResult) throws EntityNotValidException {
        if (bindingResult.hasErrors()) {
            ActionError error = ActionError.fromBindingResult(bindingResult);
            return RedirectUtil.redirectTo("/bid/add", null, error);
        }
        bidService.save(bid);
        return RedirectUtil.redirectTo("/bid/list", "Bid successfully added !", null);
    }

    @GetMapping("/bid/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, @RequestParam Optional<String> msg, @RequestParam Optional<String> error) throws EntityNotFoundException {
        Optional<Bid> optional = bidService.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("Bid not found");
        }
        Bid e = optional.get();
        msg.ifPresent((m) -> model.addAttribute("msg", m));
        model.addAttribute("bid", e);
        model.addAttribute("fields", bidService.listFields(e));
        model.addAttribute("error", ActionError.fromJsonParam(error));
        return "bid/update";
    }

    @PostMapping("/bid/update/{id}")
    public RedirectView updateBid(@PathVariable("id") Integer id, @Valid @ModelAttribute("bid") Bid bid, BindingResult bindingResult) throws EntityNotValidException {
        if (bindingResult.hasErrors()) {
            ActionError error = ActionError.fromBindingResult(bindingResult);
            return RedirectUtil.redirectTo("/bid/update/" + id, null, error);
        }
        bid.setId(id);
        bidService.save(bid);
        return RedirectUtil.redirectTo("/bid/list", "Bid successfully updated !", null);
    }

    @GetMapping("/bid/delete/{id}")
    public RedirectView deleteBid(@PathVariable("id") Integer id) throws EntityNotFoundException {
        bidService.delete(getBidOrThrow(id));
        return RedirectUtil.redirectTo("/bid/list", "Bid successfully deleted !", null);
    }

    private Bid getBidOrThrow(Integer id) throws EntityNotFoundException {
        Optional<Bid> optional = bidService.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("Bid not found");
        }
        return optional.get();
    }
}