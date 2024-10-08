package com.example.gymServer.classes;

import com.example.gymServer.authorization.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/classes")
@RequiredArgsConstructor
public class FitnessClassController {

    private final FitnessClassService fitnessClassService;

    @GetMapping
    public List<FitnessClass> getAllClasses() {
        return fitnessClassService.getAllClasses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FitnessClass> getClassById(@PathVariable Long id) {
        Optional<FitnessClass> fitnessClass = fitnessClassService.getClassById(id);
        return fitnessClass.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public FitnessClass createClass(@RequestBody FitnessClass fitnessClass) {
        return fitnessClassService.createClass(fitnessClass);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FitnessClass> updateClass(@PathVariable Long id, @RequestBody FitnessClass fitnessClass) {
        FitnessClass updatedClass = fitnessClassService.updateClass(id, fitnessClass);
        return updatedClass != null ? ResponseEntity.ok(updatedClass) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        fitnessClassService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/enroll")
    public ResponseEntity<FitnessClass> enrollUser(@PathVariable Long id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<FitnessClass> fitnessClass = fitnessClassService.enrollUser(id, currentUser.getId());
        return fitnessClass.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/{id}/cancel-enrollment")
    public ResponseEntity<FitnessClass> cancelEnrollment(@PathVariable Long id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<FitnessClass> fitnessClass = fitnessClassService.cancelEnrollment(id, currentUser.getId());
        return fitnessClass.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/enrolled-users")
    public ResponseEntity<List<User>> getEnrolledUsers(@PathVariable Long id) {
        List<User> enrolledUsers = fitnessClassService.getEnrolledUsers(id);
        return ResponseEntity.ok(enrolledUsers);
    }

    @GetMapping("/enrolled")
    public ResponseEntity<List<FitnessClass>> getEnrolledClassesForCurrentUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<FitnessClass> classes = fitnessClassService.getEnrolledClassesForUser(currentUser.getId());
        return ResponseEntity.ok(classes);
    }
}
