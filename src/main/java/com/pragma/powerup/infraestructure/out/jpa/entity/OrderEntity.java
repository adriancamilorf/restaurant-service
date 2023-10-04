package com.pragma.powerup.infraestructure.out.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
    private LocalDateTime date;
    @Column(nullable = false)
    private String state;
    @ManyToOne
    @JoinColumn(name = "id_chef")
    private EmployeeRestaurantEntity chefId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_restaurant",nullable = false)
    private RestaurantEntity restaurant;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDishEntity> orderDishes;
}
