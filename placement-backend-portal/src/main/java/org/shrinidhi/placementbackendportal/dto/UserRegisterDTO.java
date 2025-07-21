package org.shrinidhi.placementbackendportal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRegisterDTO {
    private String name;
    private String email;
    private String password;
    private String role; // "STUDENT" or "OFFICER"
    private Double cgpa; // student only
    private String skills; // student only


}
