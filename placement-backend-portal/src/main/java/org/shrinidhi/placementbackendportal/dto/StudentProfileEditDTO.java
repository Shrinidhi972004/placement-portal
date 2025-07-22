package org.shrinidhi.placementbackendportal.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class StudentProfileEditDTO {
    private String name;
    private String phone;
    private Double cgpa;
    private String branch;
    private Integer yearOfStudy;
    private List<String> skills;
    private List<String> projects;
    private List<String> achievements;
    private List<String> socialLinks;
}
