package com.universalathletics.memberInfo.service;

//------------------------------- imports ------------------------------------//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universalathletics.memberInfo.entity.MemberInfoEntity;
import com.universalathletics.memberInfo.repository.MemberInfoRepository;
import com.universalathletics.skill.entity.SkillEntity;
import com.universalathletics.skill.repository.SkillRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;

//--------------------- MemberInfo Service Class -----------------------------//
/**
 * MemberInfoService handles all business logic related to member operations in
 * the
 * Universal Athletics application.
 * This service layer acts as an intermediary between the controller and
 * repository layers.
 * 
 * Responsibilities:
 * - Member creation and management
 * - Member data validation
 * - Member information processing
 */
@Service
public class MemberInfoService {

    /**
     * Autowired instance of MemberInfoRepository for database operations.
     * Following Spring best practices for dependency injection.
     */
    @Autowired
    private MemberInfoRepository memberInfoRepository;

    /**
     * Autowired instance of SkillRepository for database operations.
     */
    @Autowired
    private SkillRepository skillRepository;

    // -------------------------------- Create Member ----------------------------//
    /**
     * Creates or updates a member in the database.(POST)
     * 
     * @param memberInfo The MemberInfoEntity object containing member information
     * @return MemberInfoEntity The saved member object with generated ID
     * @throws IllegalArgumentException if memberInfo is null
     */
    public MemberInfoEntity saveMember(MemberInfoEntity memberInfo) {
        if (memberInfo == null) {
            throw new IllegalArgumentException("Member information cannot be null");
        }

        // List to hold valid skills for the member
        List<SkillEntity> validSkills = new ArrayList<>();

        // Ensure skills are managed before saving member
        if (memberInfo.getSkills() != null) {
            for (SkillEntity skill : memberInfo.getSkills()) {
                // Check if the skill exists in the repository
                Optional<SkillEntity> existingSkill = skillRepository.findById(skill.getSkill_id());

                if (existingSkill.isPresent()) {
                    // If the skill exists, add it to the validSkills list
                    validSkills.add(existingSkill.get());
                } else {
                    // If the skill does not exist, throw an exception
                    throw new EntityNotFoundException("Skill not found with id: " + skill.getSkill_id());
                }
            }
            // Set the valid skills to the member
            memberInfo.setSkills(validSkills);
        }

        return memberInfoRepository.save(memberInfo);
    }

    // -------------------------------- Get Member By ID -------------------------//
    /**
     * Retrieves a member by their ID.(GET)
     *
     * @param id The ID of the member to find
     * @return MemberInfoEntity if found
     * @throws EntityNotFoundException if member not found
     */
    public MemberInfoEntity findMemberById(Integer id) {
        return memberInfoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + id));
    }

    // --------------------------------- Get All Members --------------------------//
    /**
     * Retrieves all members from the database.(GET)
     *
     * @return List<MemberInfoEntity> containing all members
     */
    public List<MemberInfoEntity> findAllMembers() {
        return memberInfoRepository.findAll();
    }

    // --------------------------------- Get Member By Firebase Id ----------------------------//
    /**
     * Retrieves a member by their Firebase ID.(GET)
     *  
     * @param firebaseId The Firebase ID of the member to find
     * @return MemberInfoEntity if found
     * @throws EntityNotFoundException if member not found
     */
    public MemberInfoEntity findMemberByFirebaseId(String firebaseId) {
        return memberInfoRepository.findByFirebaseID(firebaseId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with firebaseId: " + firebaseId));
    }


    // --------------------------------- Delete Member ----------------------------//
    /**
     * Deletes a member from the database.(DELETE)
     * 
     * @param id The ID of the member to delete
     * @return String Success message indicating member deletion
     * @throws EntityNotFoundException if member not found
     */
    public String deleteMember(Integer id) {
        if (!memberInfoRepository.existsById(id)) {
            throw new EntityNotFoundException("Member not found with id: " + id);
        }
        memberInfoRepository.deleteById(id);
        return "Member with ID: " + id + " has been successfully deleted";
    }
}
