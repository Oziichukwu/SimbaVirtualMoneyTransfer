package com.example.simbavirtualmoneytransfer.data.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class AppUser {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();

    @CreationTimestamp
    @JsonFormat(pattern = "MMMM-dddd-yyyy  HH:mm:ss")
    private LocalDateTime CreationDate;

    @UpdateTimestamp
    @JsonFormat(pattern = "MMMM-dddd-yyyy  HH:mm:ss")
    private LocalDateTime updatedDate;

    @OneToOne(cascade = CascadeType.ALL)
    private final Account myAccount;

    private Currency baseCurrency;

    public AppUser(){
        this.myAccount = new Account();
        this.myAccount.setAccountBalance(1000);
        this.myAccount.setBaseCurrency(Currency.USD);
    }

}
