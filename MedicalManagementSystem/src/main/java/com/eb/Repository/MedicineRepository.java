package com.eb.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eb.Entity.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
	
	
    
}

