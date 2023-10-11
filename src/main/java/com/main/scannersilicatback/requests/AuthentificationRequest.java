package com.main.scannersilicatback.requests;

import lombok.Data;

@Data
public class AuthentificationRequest {
    private String email;
    private String password;
}
