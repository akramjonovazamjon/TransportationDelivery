package com.example.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "transport_items")
public class TransportItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departure_time")
    private Timestamp departureTime;
    @Column(name = "departure_address")
    private String departureAddress;

    @Column(name = "arrival_time")
    private Timestamp arrivalTime;

    @Column(name = "arrival_address")
    private String arrivalAddress;

    @Column(name = "distance")
    private Long distance;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @ManyToOne
    @JoinColumn(name = "transport_id")
    private Transport transport;

}
