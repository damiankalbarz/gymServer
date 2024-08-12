package com.example.gymServer.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FitnessClassService {

    private final FitnessClassRepository fitnessClassRepository;

    @Autowired
    public FitnessClassService(FitnessClassRepository fitnessClassRepository) {
        this.fitnessClassRepository = fitnessClassRepository;
    }

    public List<FitnessClass> getAllClasses() {
        return fitnessClassRepository.findAll();
    }

    public Optional<FitnessClass> getClassById(Long id) {
        return fitnessClassRepository.findById(id);
    }

    public FitnessClass createClass(FitnessClass fitnessClass) {
        return fitnessClassRepository.save(fitnessClass);
    }

    public FitnessClass updateClass(Long id, FitnessClass updatedClass) {
        if (fitnessClassRepository.existsById(id)) {
            updatedClass.setId(id);
            return fitnessClassRepository.save(updatedClass);
        }
        return null;
    }

    public void deleteClass(Long id) {
        fitnessClassRepository.deleteById(id);
    }
}
