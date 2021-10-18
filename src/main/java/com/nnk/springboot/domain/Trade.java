package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "trade")
@Getter
@Setter
@NoArgsConstructor
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column
    @NotBlank
    String account;

    @Column
    @NotBlank
    String type;

    @Column
    @NotNull
    Double buyQuantity;

    @Column
    @NotNull
    Double sellQuantity;

    @Column
    @NotNull
    Double buyPrice;

    @Column
    @NotNull
    Double sellPrice;

    @Column
    @NotBlank
    String benchmark;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    LocalDate tradeDate;

    @Column
    @NotBlank
    String security;

    @Column
    @NotBlank
    String status;

    @Column
    @NotBlank
    String trader;

    @Column
    @NotBlank
    String book;

    @Column
    @NotBlank
    String creationName;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    LocalDate creationDate;

    @Column
    @NotBlank
    String revisionName;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    LocalDate revisionDate;

    @Column
    @NotBlank
    String dealName;

    @Column
    @NotBlank
    String dealType;

    @Column
    @NotBlank
    String sourceListId;

    @Column
    @NotBlank
    String side;

    public Trade(String account, String type) {
        setAccount(account);
        setType(type);
    }
}
