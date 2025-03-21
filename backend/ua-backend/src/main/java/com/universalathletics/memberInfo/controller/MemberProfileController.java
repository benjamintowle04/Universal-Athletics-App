package com.universalathletics.memberInfo.controller;

//------------------------------- imports ------------------------------------//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.universalathletics.cloudStorage.service.GoogleCloudStorageService;
import com.universalathletics.memberInfo.entity.MemberInfoEntity;
import com.universalathletics.memberInfo.service.MemberProfileService;

import java.io.IOException;

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
     * 
     * @param firebaseId The Firebase ID of the member to update
     * @param memberProfile The updated profile information
     * @return ResponseEntity containing the updated member profile
     */
    @PutMapping("/{firebaseId}")
    public ResponseEntity<MemberInfoEntity> updateMemberProfile(
            @PathVariable String firebaseId,
            @RequestBody MemberInfoEntity memberProfile) {
        MemberInfoEntity updatedMember = memberProfileService.updateMemberProfile(firebaseId, memberProfile);
        return ResponseEntity.ok(updatedMember);
    }

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
    @PutMapping("/{firebaseId}/profile-image")
    public ResponseEntity<MemberInfoEntity> updateProfileImage(
            @PathVariable String firebaseId,
            @RequestParam("profilePic") MultipartFile profilePic) {
        try {
            MemberInfoEntity updatedMember = memberProfileService.updateProfileImage(firebaseId, profilePic);
            return ResponseEntity.ok(updatedMember);
        } catch (IOException e) {
            // Handle image upload failure
            return ResponseEntity.badRequest().build();
        }
    }

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