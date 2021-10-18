package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "rule")
@Getter
@Setter
@NoArgsConstructor
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column
    @NotBlank
    String name;

    @Column
    @NotBlank
    String description;

    @Column
    @NotBlank
    String json;

    @Column
    @NotBlank
    String template;

    @Column
    @NotBlank
    String sqlStr;

    @Column
    @NotBlank
    String sqlPart;

    public Rule(String name, String description, String json, String template, String sql, String sqlPart) {
        setName(name);
        setDescription(description);
        setJson(json);
        setTemplate(template);
        setSqlStr(sql);
        setSqlPart(sqlPart);
    }
}


