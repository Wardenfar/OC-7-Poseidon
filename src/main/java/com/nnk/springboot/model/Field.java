package com.nnk.springboot.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
public class Field {
    public String key;
    public String name;
    public String type;
    public Object value;


    public String textValue() {
        if (this.value == null) {
            return "";
        }
        return value.toString();
    }

    public String dateValue() {
        if (this.value == null) {
            return "";
        }
        LocalDate date = (LocalDate) this.value;
        return date.format(DateTimeFormatter.ISO_DATE);
    }
}
