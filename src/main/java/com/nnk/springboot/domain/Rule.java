package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    String name;

    @Column
    String description;

    @Column
    String json;

    @Column
    String template;

    @Column
    String sqlStr;

    @Column
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
