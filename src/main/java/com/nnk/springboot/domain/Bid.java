package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Table(name = "bid")
@Getter
@Setter
@NoArgsConstructor
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Positive
    Integer id;

    @Column
    @NotBlank
    String account;

    @Column
    @NotBlank
    String type;

    @Column
    @NotNull
    Double bidQuantity;

    @Column
    @NotNull
    Double askQuantity;

    @Column
    @NotNull
    Double bidValue;

    @Column
    @NotNull
    Double askValue;

    @Column
    @NotBlank
    String benchmark;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    LocalDate bidDate;

    @Column
    @NotBlank
    String commentary;

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

    public Bid(String account, String type, double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
        this.askQuantity = 1.0;
        this.bidValue = 1.0;
        this.askValue = 1.0;
        this.benchmark = "fake";
        this.bidDate = LocalDate.now();
        this.commentary = "fake";
        this.security = "fake";
        this.status = "fake";
        this.trader = "fake";
        this.book = "fake";
        this.creationName = "fake";
        this.creationDate = LocalDate.now();
        this.revisionName = "fake";
        this.revisionDate = LocalDate.now();
        this.dealName = "fake";
        this.dealType = "fake";
        this.sourceListId = "fake";
        this.side = "fake";
    }
}
