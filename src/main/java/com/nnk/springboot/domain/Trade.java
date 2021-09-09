package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "trade")
@Getter
@Setter
@NoArgsConstructor
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer tradeId;

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
    Timestamp tradeDate;

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
    Timestamp creationDate;

    @Column
    String revisionName;

    @Column
    Timestamp revisionDate;

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
