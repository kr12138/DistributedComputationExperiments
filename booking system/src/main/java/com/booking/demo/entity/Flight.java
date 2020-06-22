package com.booking.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "flight")
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    String id;

    @Column
    String departure;

    @Column
    String destination;

    @Column
    String time;

    @Column
    long price;

    @Column
    long count;
}
