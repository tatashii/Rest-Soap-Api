package org.api.rest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "work")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "StartTime")
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DeadLine")
    private Date deadLine;

    @Column(name = "Status", length = 50)
    private String status;

    @Column(name = "Type", length = 50)
    private String type;

}