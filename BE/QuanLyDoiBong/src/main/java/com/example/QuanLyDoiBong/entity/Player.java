package com.example.QuanLyDoiBong.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPlayer")
    private int IDPlayer;
    @Column(name = "FullName")
    private String fullName;
    @Column(name= "DateOfBirth")
    private Date dateOfBirth;
    @Column(name= "Country")
    private String country;
    @Column(name= "Position")
    private String position;
    @Column(name= "JerseyNumber")
    private String jerseyNumber;
    @Column(name= "Photo")
    private String photo;
    @Column(name= "Height")
    private String height;
    @Column(name= "Weight")
    private String weight;
    @Column(name= "Email")
    private String email;
    @Column(name= "Phone")
    private String phone;
    @ManyToOne
    @JoinColumn(name="IDTeam")
    private Team team;
    @Column(name = "shows")
    private boolean shows;
    @Column(name= "ContractStartDate")
    private Date contractStartDate;
    @Column(name= "ContractEndDate")
    private Date contractEndDate;
}
