package org.shrinidhi.placementbackendportal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("STUDENT")
@Getter @Setter
public class Student extends User {
    private Double cgpa;
    private String skills;
}
