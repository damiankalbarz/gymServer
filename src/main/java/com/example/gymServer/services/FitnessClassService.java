package com.example.gymServer.services;

import com.example.gymServer.authorization.user.User;
import com.example.gymServer.authorization.user.UserRepository;
import com.example.gymServer.dto.FitnessClassDTO;
import com.example.gymServer.mapper.FitnessClassMapper;
import com.example.gymServer.models.FitnessClass;
import com.example.gymServer.repository.FitnessClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FitnessClassService {

    private final FitnessClassRepository fitnessClassRepository;
    private final UserRepository userRepository;
    private final FitnessClassMapper fitnessClassMapper;

    @Autowired
    public FitnessClassService(FitnessClassRepository fitnessClassRepository, UserRepository userRepository, FitnessClassMapper fitnessClassMapper) {
        this.fitnessClassRepository = fitnessClassRepository;
        this.userRepository = userRepository;
        this.fitnessClassMapper = fitnessClassMapper;
    }

    public ResponseEntity<List<FitnessClassDTO>> getAllClasses() {
        List<FitnessClass> classes = fitnessClassRepository.findAll();
        return new ResponseEntity<>(fitnessClassMapper.toFitnessClassDTOs(classes), HttpStatus.OK);
    }

    public ResponseEntity<FitnessClassDTO> getClassById(Long id) {
        FitnessClass fitnessClass = fitnessClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found"));
        return new ResponseEntity<>(fitnessClassMapper.toFitnessClassDTO(fitnessClass), HttpStatus.OK);
    }

    public ResponseEntity<FitnessClassDTO> createClass(FitnessClassDTO fitnessClassDTO) {
        FitnessClass fitnessClass = fitnessClassMapper.toFitnessClass(fitnessClassDTO);
        FitnessClass savedClass = fitnessClassRepository.save(fitnessClass);
        return new ResponseEntity<>(fitnessClassMapper.toFitnessClassDTO(savedClass), HttpStatus.CREATED);
    }

    public ResponseEntity<Void> updateClass(Long id, FitnessClassDTO updatedClassDTO) {
        if (!fitnessClassRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        FitnessClass updatedClass = fitnessClassMapper.toFitnessClass(updatedClassDTO);
        updatedClass.setId(id);
        fitnessClassRepository.save(updatedClass);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Void> deleteClass(Long id) {
        if (!fitnessClassRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        fitnessClassRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public Optional<FitnessClassDTO> enrollUser(Long classId, Integer userId) {
        Optional<FitnessClass> fitnessClassOpt = fitnessClassRepository.findById(classId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (fitnessClassOpt.isPresent() && userOpt.isPresent()) {
            FitnessClass fitnessClass = fitnessClassOpt.get();
            User user = userOpt.get();

            if (fitnessClass.getEnrolledUsers().size() < fitnessClass.getMaxUsers()) {
                fitnessClass.getEnrolledUsers().add(user);
                fitnessClassRepository.save(fitnessClass);
                return Optional.of(fitnessClassMapper.toFitnessClassDTO(fitnessClass));
            }
        }
        return Optional.empty();
    }

    public Optional<FitnessClassDTO> cancelEnrollment(Long classId, Integer userId) {
        Optional<FitnessClass> fitnessClassOpt = fitnessClassRepository.findById(classId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (fitnessClassOpt.isPresent() && userOpt.isPresent()) {
            FitnessClass fitnessClass = fitnessClassOpt.get();
            User user = userOpt.get();
            fitnessClass.getEnrolledUsers().remove(user);
            fitnessClassRepository.save(fitnessClass);
            return Optional.of(fitnessClassMapper.toFitnessClassDTO(fitnessClass));
        }
        return Optional.empty();
    }

    public List<User> getEnrolledUsers(Long classId) {
        return fitnessClassRepository.findById(classId)
                .map(FitnessClass::getEnrolledUsers)
                .orElseThrow(() -> new RuntimeException("Class not found"));
    }

    public List<FitnessClassDTO> getEnrolledClassesForUser(Integer userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            List<FitnessClass> classes = user.getEnrolledClasses();
            return fitnessClassMapper.toFitnessClassDTOs(classes);
        }
        return List.of();
    }
}