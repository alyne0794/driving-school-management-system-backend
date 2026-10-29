package com.auca.drivingschool.service;

import com.auca.drivingschool.model.Learner;
import com.auca.drivingschool.repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LearnerService {

    @Autowired
    private LearnerRepository learnerRepository;

    public String saveLearner(Learner learner) {
        learnerRepository.save(learner);
        return "Learner saved successfully";
    }

    public List<Learner> getAllLearners() {
        return learnerRepository.findAll();
    }

    public Optional<Learner> getLearnerById(UUID id) {
        return learnerRepository.findById(id);
    }

    public String updateLearner(UUID id, Learner newLearner) {
        Optional<Learner> existing = learnerRepository.findById(id);
        if (existing.isEmpty()) {
            return "The learner with that id does not exist";
        }
        Learner learner = existing.get();
        learner.setFirstName(newLearner.getFirstName());
        learner.setLastName(newLearner.getLastName());
        learner.setEnrollmentNumber(newLearner.getEnrollmentNumber());
        learner.setPhone(newLearner.getPhone());
        learner.setEmail(newLearner.getEmail());
        learnerRepository.save(learner);
        return "Learner updated successfully";
    }

    public String deleteLearner(UUID id) {
        if (!learnerRepository.existsById(id)) {
            return "The learner with that id does not exist";
        }
        learnerRepository.deleteById(id);
        return "Learner deleted successfully";
    }
   public List<Learner> getLearnersByLastName(String lastName) {
        return learnerRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    } 
}
