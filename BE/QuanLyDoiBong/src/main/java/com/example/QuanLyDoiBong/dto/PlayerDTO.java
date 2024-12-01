package com.example.QuanLyDoiBong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private int IDPlayer;
    private String fullName;
    private Date dateOfBirth;
    private String country;
    private String position;
    private String jerseyNumber;
    private String photo;
    private String height;
    private String weight;
    private String email;
    private String phone;
    private int IDTeam;
    private Date contractStartDate;
    private Date contractEndDate;
}
