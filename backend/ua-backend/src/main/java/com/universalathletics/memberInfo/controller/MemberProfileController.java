package com.universalathletics.memberInfo.controller;

//------------------------------- imports ------------------------------------//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.universalathletics.cloudStorage.service.GoogleCloudStorageService;
import com.universalathletics.memberInfo.entity.MemberInfoEntity;
import com.universalathletics.memberInfo.service.MemberProfileService;

//------------------------ MemberProfile Controller Class ----------------------//
/**
 * REST Controller for handling member profile operations.
 * Provides endpoints for managing member profile data in the Universal Athletics
 * system.
 *
 * Responsibilities:
 * - Handle HTTP requests for profile updates
 * - Delegate business logic to MemberProfileService
 * - Process and return appropriate responses
 * - Handle profile image uploads
 */
@RestController
@RequestMapping("/api/members/profile")
@CrossOrigin(origins = "*")
public class MemberProfileController {

    /**
     * Autowired instance of MemberProfileService for handling business logic.
     */
    @Autowired
    private MemberProfileService memberProfileService;

    /**
     * Autowired instance of GoogleCloudStorageService for handling profile images.
     */
    @Autowired
    private GoogleCloudStorageService storageService;

    // ------------------------- Update Member Profile Endpoint --------------------------//
    /**
     * Updates a member's profile information.
     * Handles validation and processing of profile data updates.
     * 
     * TODO: Implement endpoint for updating member profile
     * - Validate input data
     * - Process updates
     * - Handle errors
     * - Return updated profile
     */

    // ------------------------- Update Profile Image Endpoint --------------------------//
    /**
     * Handles member profile image updates.
     * Processes image uploads and updates profile picture URL.
     * 
     * TODO: Implement endpoint for updating profile image
     * - Handle image upload
     * - Validate image format/size
     * - Update profile picture URL
     * - Return updated profile
     */

    // ------------------------- Validate Profile Fields Endpoint ----------------------//
    /**
     * Validates individual profile field updates.
     * Used for real-time validation during form editing.
     * 
     * TODO: Implement endpoint for field validation
     * - Validate specific fields
     * - Return validation results
     * - Handle validation errors
     */
}
