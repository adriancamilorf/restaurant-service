package com.pragma.powerup.infraestructure.out.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import com.pragma.powerup.infraestructure.out.jpa.entity.RestaurantEntity;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,name = "id_client")
    private Long clientId;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private String state;
    @Column(name = "id_chef")
    private Long chefId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_restaurant",nullable = false)
    private RestaurantEntity restaurant;
}
