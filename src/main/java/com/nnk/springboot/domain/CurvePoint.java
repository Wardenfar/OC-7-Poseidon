package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "curvepoint")
@Getter
@Setter
@NoArgsConstructor
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    LocalDate asOfDate;

    @Column
    @NotNull
    Double term;

    @Column
    @NotNull
    Double value;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    LocalDate creationDate;

    public CurvePoint(int id, double term, double value) {
        setId(id);
        setTerm(term);
        setValue(value);
    }
}
