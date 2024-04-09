package org.api.rest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "UserName", nullable = false, length = 100)
    private String userName;

    @Column(name = "Password", nullable = false, length = 100)
    private String password;

    @NotNull
    @Column(name = "Email", nullable = false)
    private String email;

//    @Column(name = "Role", length = 100)
//    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RoleId")
    private Role role;

    @Column(name = "Gender", length = 10)
    private String gender;

    @Column(name = "Salary", precision = 10, scale = 2)
    private BigDecimal salary;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DOB")
    private Date dob;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Joining")
    private Date joining;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DepartmentId")
    private Department department;

    @OneToMany(mappedBy = "employee")
    private Set<Address> addresses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "employee")
    private Set<Leavee> leavees = new LinkedHashSet<>();



}