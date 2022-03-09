package com.example.simbavirtualmoneytransfer.data.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private RoleType roleName;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private LocalDateTime updatedDate;

    public Role(RoleType roleName) {
        this.roleName = roleName;
    }
}
