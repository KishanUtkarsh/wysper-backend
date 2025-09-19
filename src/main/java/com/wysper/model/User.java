package com.wysper.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Column(unique = true, nullable = false, length = 16)
    private String username;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    private String passwordHashed;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    @ManyToMany
    @JoinTable(
            name = "contacts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_user_id")
    )
    private Set<User> contacts = new HashSet<>();

    @NotBlank
    private String tOtpSecret;
    @NotBlank
    private String enPubKey;
    @NotBlank
    private String enPriKey;
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String dToken;
    private String dName;
    private String dType;
    private String deviceId;

    private boolean active;
    private boolean deactivated;
    private boolean deleted;
    private boolean eVerified;


    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

}
