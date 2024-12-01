package com.example.QuanLyDoiBong.controller;

import com.example.QuanLyDoiBong.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccoutController {

    @Autowired
    private AccountServices accountServices;

    @PostMapping("/signin")
    public ResponseEntity<Object> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        return accountServices.getAccount(username, password);
    }

}
