package org.shrinidhi.placementbackendportal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("OFFICER")
@Getter @Setter
public class Officer extends User {
    // Add officer-specific fields here in the future if needed.
}
