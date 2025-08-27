package com.train.basic.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    private String fullName;

    private String phoneNumber;

    private LocalDate dob;

    private String gender;

    private String address;

    private String city;

    private String state;

    private String country;

    private String pincode;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] profileImage;

    private boolean active;
}