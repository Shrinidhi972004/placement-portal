package org.shrinidhi.placementbackendportal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("STUDENT")
@Getter @Setter
public class Student extends User {
    private String phone;
    private Double cgpa;
    private String branch;
    private Integer yearOfStudy;
    private String resumeUrl;
    private String profilePhotoUrl;

    @ElementCollection
    private java.util.List<String> skills;

    @ElementCollection
    private java.util.List<String> projects;

    @ElementCollection
    private java.util.List<String> achievements;

    @ElementCollection
    private java.util.List<String> socialLinks;
}
