package com.example.ss10exma.controller;

import com.example.ss10exma.entity.Student;
import com.example.ss10exma.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;

    @RequestMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @RequestMapping("/{id}")
    private ResponseEntity<?> findById(@PathVariable Long id){
        return studentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody Student student){
        if(student.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        Student save = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFull(@PathVariable Long id, @RequestBody Student student){
        return studentRepository.findById(id)
                .map(s -> {
                        s.setEmail(student.getEmail());
                        s.setFullName(student.getFullName());
                        s.setGpa(student.getGpa());
                        Student save = studentRepository.save(s);
                return ResponseEntity.ok(save);})
                .orElse(ResponseEntity.notFound().build());
    }

}
