package com.pragma.powerup.infraestructure.out.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employees_restaurants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,name = "id_employee")
    private Long employeeId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_restaurant")
    private RestaurantEntity restaurant;
}
