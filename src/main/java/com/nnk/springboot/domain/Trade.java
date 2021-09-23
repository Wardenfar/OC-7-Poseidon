package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    String account;

    @Column
    String type;

    @Column
    Double buyQuantity;

    @Column
    Double sellQuantity;

    @Column
    Double buyPrice;

    @Column
    Double sellPrice;

    @Column
    String benchmark;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate tradeDate;

    @Column
    String security;

    @Column
    String status;

    @Column
    String trader;

    @Column
    String book;

    @Column
    String creationName;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate creationDate;

    @Column
    String revisionName;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate revisionDate;

    @Column
    String dealName;

    @Column
    String dealType;

    @Column
    String sourceListId;

    @Column
    String side;

    public Trade(String account, String type) {
        setAccount(account);
        setType(type);
    }
}
