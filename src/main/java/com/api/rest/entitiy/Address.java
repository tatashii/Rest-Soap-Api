package com.api.rest.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "HouseNo", length = 50)
    private String houseNo;

    @Column(name = "Street", nullable = false)
    private String street;

    @Column(name = "City", length = 100)
    private String city;

    @Column(name = "Pincode", length = 20)
    private String pincode;

    @Column(name = "State", length = 100)
    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EmployeeId")
    private Employee employee;

}