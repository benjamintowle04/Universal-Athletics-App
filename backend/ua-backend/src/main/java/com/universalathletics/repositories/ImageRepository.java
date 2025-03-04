package com.universalathletics.repositories;

//------------------------------- imports ------------------------------------//
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.universalathletics.entities.ImageEntity;

//--------------------- Image Repository Interface -------------------------//
/**
 * Repository interface for managing ImageEntity persistence operations.
 * Extends JpaRepository to provide standard CRUD operations and additional
 * query methods for image management.
 *
 * This repository handles all database interactions for stored images,
 * including creating, reading, updating, and deleting image records.
 */
@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
          // Inherits standard CRUD operations from JpaRepository
          // Additional custom query methods can be added here as needed
}
