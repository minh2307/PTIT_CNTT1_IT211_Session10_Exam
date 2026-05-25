package com.example.ss10exma.repository;

import com.example.ss10exma.entity.Student;
import org.hibernate.annotations.ConcreteProxy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface StudentRepository extends JpaRepository<Student, Long> {
}
