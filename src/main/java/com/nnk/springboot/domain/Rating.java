package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotBlank
    String moodysRating;

    @Column
    @NotBlank
    String sandPRating;

    @Column
    @NotBlank
    String fitchRating;

    @Column
    @NotNull
    Integer orderNumber;

    public Rating(String moodysRating, String sandPRating, String fitchRating, int orderNumber) {
        setMoodysRating(moodysRating);
        setSandPRating(sandPRating);
        setFitchRating(fitchRating);
        setOrderNumber(orderNumber);
    }
}
