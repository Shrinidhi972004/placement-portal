package org.shrinidhi.placementbackendportal.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class StudentProfileDTO {
    private String name;
    private String email;
    private String phone;
    private Double cgpa;
    private String branch;
    private Integer yearOfStudy;
    private List<String> skills;
    private String resumeUrl;
    private String profilePhotoUrl;
    private List<String> projects;
    private List<String> achievements;
    private List<String> socialLinks;
}
