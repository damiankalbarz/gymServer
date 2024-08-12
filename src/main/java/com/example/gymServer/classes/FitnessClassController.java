package com.example.gymServer.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
public class FitnessClassController {

    private final FitnessClassService fitnessClassService;

    @Autowired
    public FitnessClassController(FitnessClassService fitnessClassService) {
        this.fitnessClassService = fitnessClassService;
    }

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
}
