package org.api.rest.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Role")
public class Role {
    public enum RoleName {
        MANAGER,
        DEVELOPER,
        EMPLOYEE,
        HR,

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Name", nullable = false, length = 100)
    private RoleName name;
}

