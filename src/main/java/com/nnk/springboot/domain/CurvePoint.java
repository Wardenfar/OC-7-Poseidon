package com.nnk.springboot.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


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
    Integer curveId;
 
    @Column
    Timestamp asOfDate;
 
    @Column
    Double term;
 
    @Column
    Double value;
 
    @Column
    Timestamp creationDate;

    public CurvePoint(int curveId, double term, double value) {
        setCurveId(curveId);
        setTerm(term);
        setValue(value);
    }
}
