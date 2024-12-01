package com.example.QuanLyDoiBong.services;

import org.springframework.http.ResponseEntity;

public interface AccountServices {
    ResponseEntity<Object> getAccount(String userName, String passWord);
}
