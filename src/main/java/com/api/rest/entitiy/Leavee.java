package com.api.rest.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "leavee")
public class Leavee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Reason", nullable = false)
    private String reason;

    @Column(name = "Status", nullable = false, length = 50)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LeaveStartDate", nullable = false)
    private Date leaveStartDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LeaveEndDate", nullable = false)
    private Date leaveEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EmployeeId")
    private Employee employee;

}