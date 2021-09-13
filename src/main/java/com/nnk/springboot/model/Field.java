package com.nnk.springboot.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Field {
    public String key;
    public String name;
    public String type;
    public Object value;
}
