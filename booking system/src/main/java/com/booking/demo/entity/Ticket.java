package com.booking.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "ticket")
@IdClass(TicketPK.class)
public class Ticket implements Serializable {
    @Id
    @JoinColumn(name = "uid")
    long uid;

    @Id
    @JoinColumn(name = "fid")
    String fid;

    @Column
    String time;
}
