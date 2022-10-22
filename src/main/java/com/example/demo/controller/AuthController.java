package com.example.demo.controller;

import javax.validation.Valid;

import com.example.demo.entity.Admin;
import com.example.demo.model.JwtRequest;
import com.example.demo.model.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import com.example.demo.Utility.JwtTokenUtil;
import com.example.demo.entity.Customer;

@RestController
public class AuthController {
    @Autowired AuthenticationManager authManager;
    @Autowired JwtTokenUtil jwtUtil;

    @PostMapping("/admin/login")
    public ResponseEntity<?> login(@RequestBody @Valid JwtRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword())
            );

            Admin admin = (Admin) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(admin);
            JwtResponse response = new JwtResponse(admin.getUsername(), accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
