package org.shrinidhi.placementbackendportal.repository;

import org.shrinidhi.placementbackendportal.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}
