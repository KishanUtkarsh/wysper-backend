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
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid = UUID.randomUUID();

    @NotBlank
    private String fname;
    @NotBlank
    private String lname;

    private String smessage;

    @Column(unique = true)
    private String ppicid;

    @NotBlank
    @Column(unique = true, nullable = false, length = 16, name = "uname")
    private String username;

    @Column
    private String phone;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    private String totpsecret;
    @NotBlank
    private String enpubkey;
    @NotBlank
    private String enprivkey;
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String dtoken;

    private boolean isactive;
    private boolean deactivated;
    private boolean deleted;
    private boolean everified;


    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private Date createdat;

    @LastModifiedDate
    @Column(name = "last_seen", nullable = false)
    private Date lastseen;

}
