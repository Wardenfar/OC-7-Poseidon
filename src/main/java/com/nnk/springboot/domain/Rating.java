package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rating")
@Getter
@Setter
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column
    String moodysRating;

    @Column
    String sandPRating;

    @Column
    String fitchRating;

    @Column
    Integer orderNumber;

    public Rating(String moodysRating, String sandPRating, String fitchRating, int orderNumber) {
        setMoodysRating(moodysRating);
        setSandPRating(sandPRating);
        setFitchRating(fitchRating);
        setOrderNumber(orderNumber);
    }
}
