package com.eb.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eb.Entity.Medicine;
import com.eb.Entity.Responce;
import com.eb.Repository.MedicineRepository;
import com.eb.exception.UserNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    @Autowired
    private MedicineRepository medicineRepository;

    @PostMapping
    public ResponseEntity<Medicine> addMedicine(@RequestBody Medicine medicine) {
        Medicine savedMedicine = medicineRepository.save(medicine);
        return ResponseEntity.ok(savedMedicine);
    }

    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> medicines = medicineRepository.findAll();
        return ResponseEntity.ok(medicines);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Responce> deleteMedicine(@PathVariable Long id) {
    	Responce response = new Responce("Deleted Successfully");
        Optional<Medicine> optionalMedicine = medicineRepository.findById(id);
        if (optionalMedicine.isPresent()) {
            medicineRepository.delete(optionalMedicine.get());
            return ResponseEntity.created(null).body(response);
        } else {
        	throw new UserNotFoundException("Id not found");
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Responce> updateMedicine(@PathVariable Long id, @RequestBody Medicine updatedMedicine) {
    	Responce response = new Responce("Updated Successfully");
        Optional<Medicine> optionalMedicine = medicineRepository.findById(id);
        if (optionalMedicine.isPresent()) {
            Medicine medicine = optionalMedicine.get();
            medicine.setName(updatedMedicine.getName());
            medicine.setCompany(updatedMedicine.getCompany());
            medicine.setQuantity(updatedMedicine.getQuantity());
            medicine.setDescription(updatedMedicine.getDescription());
            medicineRepository.save(medicine);
            return ResponseEntity.created(null).body(response);
        } else {
        	throw new UserNotFoundException("Not Updated");
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) {
        Medicine medicine = medicineRepository.findById(id)
        		.orElseThrow(() -> new UserNotFoundException("Id not Found "));
        return ResponseEntity.created(null).body(medicine);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

}

