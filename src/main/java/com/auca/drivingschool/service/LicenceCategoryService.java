package com.auca.drivingschool.service;

import com.auca.drivingschool.model.LicenceCategory;
import com.auca.drivingschool.repository.LicenceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LicenceCategoryService {

    @Autowired
    private LicenceCategoryRepository licenceCategoryRepository;

    public String saveLicenceCategory(LicenceCategory licenceCategory) {
        licenceCategoryRepository.save(licenceCategory);
        return "Licence category saved successfully";
    }

    public List<LicenceCategory> getAllLicenceCategories() {
        return licenceCategoryRepository.findAll();
    }

    public Optional<LicenceCategory> getLicenceCategoryById(UUID id) {
        return licenceCategoryRepository.findById(id);
    }

    public String updateLicenceCategory(UUID id, LicenceCategory newLicenceCategory) {
        Optional<LicenceCategory> existing = licenceCategoryRepository.findById(id);
        if (existing.isEmpty()) {
            return "The licence category with that id does not exist";
        }
        LicenceCategory licenceCategory = existing.get();
        licenceCategory.setName(newLicenceCategory.getName());
        licenceCategoryRepository.save(licenceCategory);
        return "Licence category updated successfully";
    }

    public String deleteLicenceCategory(UUID id) {
        if (!licenceCategoryRepository.existsById(id)) {
            return "The licence category with that id does not exist";
        }
        licenceCategoryRepository.deleteById(id);
        return "Licence category deleted successfully";
    }
}