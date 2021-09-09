package com.nnk.springboot.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nnk.springboot.util.RedirectUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActionError {

    public Map<String, String> formErrors;

    public static ActionError fromJsonParam(Optional<String> json) {
        if (json.isPresent()) {
            try {
                return RedirectUtil.mapper.readValue(json.get(), ActionError.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static ActionError fromBindingResult(BindingResult br) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError e : br.getFieldErrors()) {
            errors.put(e.getField(), e.getDefaultMessage());
        }
        return new ActionError(errors);
    }
}
