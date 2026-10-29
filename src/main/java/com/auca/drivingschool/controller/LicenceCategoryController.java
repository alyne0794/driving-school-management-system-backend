package com.auca.drivingschool.controller;

import com.auca.drivingschool.model.LicenceCategory;
import com.auca.drivingschool.service.LicenceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/licence-categories")
public class LicenceCategoryController {

    @Autowired
    private LicenceCategoryService licenceCategoryService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody LicenceCategory licenceCategory) {
        String message = licenceCategoryService.saveLicenceCategory(licenceCategory);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LicenceCategory>> getAll() {
        return new ResponseEntity<>(licenceCategoryService.getAllLicenceCategories(), HttpStatus.OK);
    }

    @GetMapping("/unused")
    public ResponseEntity<List<LicenceCategory>> getUnused() {
        return new ResponseEntity<>(licenceCategoryService.getUnusedLicenceCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<LicenceCategory> licenceCategory = licenceCategoryService.getLicenceCategoryById(id);
        if (licenceCategory.isPresent()) {
            return new ResponseEntity<>(licenceCategory.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The licence category with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody LicenceCategory licenceCategory) {
        String message = licenceCategoryService.updateLicenceCategory(id, licenceCategory);
        if (message.equals("Licence category updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = licenceCategoryService.deleteLicenceCategory(id);
        if (message.equals("Licence category deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
