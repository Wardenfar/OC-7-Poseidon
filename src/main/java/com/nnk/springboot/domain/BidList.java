package com.nnk.springboot.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bidlist")
@Getter
@Setter
@NoArgsConstructor
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer bidListId;

    @Column
    String account;
 
    @Column
    String type;
 
    @Column
    Double bidQuantity;
 
    @Column
    Double bid;
 
    @Column
    Double ask;
 
    @Column
    String benchmark;
 
    @Column
    Timestamp bidListDate;
 
    @Column
    String commentary;
 
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

    public BidList(String account, String type, double bidQuantity) {
        this.setAccount(account);
        this.setType(type);
        this.setBidQuantity(bidQuantity);
    }
}
