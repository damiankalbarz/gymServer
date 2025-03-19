package com.example.gymServer.controllers;

import com.example.gymServer.authorization.user.User;
import com.example.gymServer.dto.FitnessClassDTO;
import com.example.gymServer.services.FitnessClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/classes")
@RequiredArgsConstructor
public class FitnessClassController {

    private final FitnessClassService fitnessClassService;

    @GetMapping
    public ResponseEntity<List<FitnessClassDTO>> getAllClasses() {
        return fitnessClassService.getAllClasses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FitnessClassDTO> getClassById(@PathVariable Long id) {
        return fitnessClassService.getClassById(id);
    }

    @PostMapping
    public ResponseEntity<FitnessClassDTO> createClass(@RequestBody FitnessClassDTO fitnessClassDTO) {
        return fitnessClassService.createClass(fitnessClassDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClass(@PathVariable Long id, @RequestBody FitnessClassDTO fitnessClassDTO) {
        return fitnessClassService.updateClass(id, fitnessClassDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        return fitnessClassService.deleteClass(id);
    }

    @PostMapping("/{id}/enroll")
    public ResponseEntity<FitnessClassDTO> enrollUser(@PathVariable Long id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<FitnessClassDTO> fitnessClassDTO = fitnessClassService.enrollUser(id, currentUser.getId());
        return fitnessClassDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/cancel-enrollment")
    public ResponseEntity<FitnessClassDTO> cancelEnrollment(@PathVariable Long id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<FitnessClassDTO> fitnessClassDTO = fitnessClassService.cancelEnrollment(id, currentUser.getId());
        return fitnessClassDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/enrolled-users")
    public ResponseEntity<List<User>> getEnrolledUsers(@PathVariable Long id) {
        List<User> enrolledUsers = fitnessClassService.getEnrolledUsers(id);
        return ResponseEntity.ok(enrolledUsers);
    }

    @GetMapping("/enrolled")
    public ResponseEntity<List<FitnessClassDTO>> getEnrolledClassesForCurrentUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<FitnessClassDTO> classes = fitnessClassService.getEnrolledClassesForUser(currentUser.getId());
        return ResponseEntity.ok(classes);
    }
}
