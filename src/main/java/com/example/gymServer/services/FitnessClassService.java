package com.example.gymServer.services;

import com.example.gymServer.authorization.user.User;
import com.example.gymServer.authorization.user.UserRepository;
import com.example.gymServer.models.FitnessClass;
import com.example.gymServer.repository.FitnessClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FitnessClassService {

    private final FitnessClassRepository fitnessClassRepository;
    private final UserRepository userRepository;

    @Autowired
    public FitnessClassService(FitnessClassRepository fitnessClassRepository, UserRepository userRepository) {
        this.fitnessClassRepository = fitnessClassRepository;
        this.userRepository = userRepository;
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

    public Optional<FitnessClass> enrollUser(Long classId, Integer userId) {
        Optional<FitnessClass> fitnessClassOpt = fitnessClassRepository.findById(classId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (fitnessClassOpt.isPresent() && userOpt.isPresent()) {
            FitnessClass fitnessClass = fitnessClassOpt.get();
            User user = userOpt.get();

            if (fitnessClass.getEnrolledUsers().size() < fitnessClass.getMaxUsers()) {
                fitnessClass.getEnrolledUsers().add(user);
                fitnessClassRepository.save(fitnessClass);
                return Optional.of(fitnessClass);
            }
        }
        return Optional.empty();
    }
    public Optional<FitnessClass> cancelEnrollment(Long classId, Integer userId) {
        Optional<FitnessClass> fitnessClassOpt = fitnessClassRepository.findById(classId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (fitnessClassOpt.isPresent() && userOpt.isPresent()) {
            FitnessClass fitnessClass = fitnessClassOpt.get();
            User user = userOpt.get();

            // Usunięcie użytkownika z listy zapisanych użytkowników
            fitnessClass.getEnrolledUsers().remove(user);
            fitnessClassRepository.save(fitnessClass);
            return Optional.of(fitnessClass);
        }
        return Optional.empty();
    }


    public List<User> getEnrolledUsers(Long classId) {
        return fitnessClassRepository.findById(classId)
                .map(FitnessClass::getEnrolledUsers)
                .orElseThrow(() -> new RuntimeException("Class not found"));
    }

    public List<FitnessClass> getEnrolledClassesForUser(Integer userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user.getEnrolledClasses();
        }
        return List.of();
    }

}
