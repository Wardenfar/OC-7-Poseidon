package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.error.EntityNotFoundException;
import com.nnk.springboot.error.EntityNotValidException;
import com.nnk.springboot.model.ActionError;
import com.nnk.springboot.service.TradeService;
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
public class TradeController {

    @Autowired
    TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model, @RequestParam Optional<String> msg) {
        msg.ifPresent((m) -> model.addAttribute("msg", m));
        model.addAttribute("trades", tradeService.findAll());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTradeForm(Model model, @RequestParam Optional<String> msg, @RequestParam Optional<String> error) {
        msg.ifPresent((m) -> model.addAttribute("msg", m));
        model.addAttribute("error", ActionError.fromJsonParam(error));
        model.addAttribute("trade", new Trade());
        model.addAttribute("fields", tradeService.listFields(new Trade()));
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public RedirectView validate(@Valid @ModelAttribute("trade") Trade trade, BindingResult bindingResult) throws EntityNotValidException {
        if (bindingResult.hasErrors()) {
            ActionError error = ActionError.fromBindingResult(bindingResult);
            return RedirectUtil.redirectTo("/trade/add", null, error);
        }
        tradeService.save(trade);
        return RedirectUtil.redirectTo("/trade/list", "Trade successfully added !", null);
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, @RequestParam Optional<String> msg, @RequestParam Optional<String> error) throws EntityNotFoundException {
        Optional<Trade> optional = tradeService.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("Trade not found");
        }
        Trade e = optional.get();
        msg.ifPresent((m) -> model.addAttribute("msg", m));
        model.addAttribute("trade", e);
        model.addAttribute("fields", tradeService.listFields(e));
        model.addAttribute("error", ActionError.fromJsonParam(error));
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public RedirectView updateTrade(@PathVariable("id") Integer id, @Valid @ModelAttribute("trade") Trade trade, BindingResult bindingResult) throws EntityNotValidException {
        if (bindingResult.hasErrors()) {
            ActionError error = ActionError.fromBindingResult(bindingResult);
            return RedirectUtil.redirectTo("/trade/update/" + id, null, error);
        }
        trade.setId(id);
        tradeService.save(trade);
        return RedirectUtil.redirectTo("/trade/list", "Trade successfully updated !", null);
    }

    @GetMapping("/trade/delete/{id}")
    public RedirectView deleteTrade(@PathVariable("id") Integer id) throws EntityNotFoundException {
        tradeService.delete(getTradeOrThrow(id));
        return RedirectUtil.redirectTo("/trade/list", "Trade successfully deleted !", null);
    }

    private Trade getTradeOrThrow(Integer id) throws EntityNotFoundException {
        Optional<Trade> optional = tradeService.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("Trade not found");
        }
        return optional.get();
    }
}