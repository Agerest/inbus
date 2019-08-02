package com.vozili.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Data
@ToString
@Entity
@Table(name = "contract")
@EqualsAndHashCode
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_route")
    private String numberRoute;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "date")
    private Time date;

    @Column(name = "number_auto")
    private String numberAuto;

    private Customer customer;
}
