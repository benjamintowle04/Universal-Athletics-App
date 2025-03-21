package com.universalathletics.memberInfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.universalathletics.cloudStorage.service.GoogleCloudStorageService;
import com.universalathletics.memberInfo.entity.MemberInfoEntity;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;

@Service
public class MemberProfileService {

    @Autowired
    private MemberInfoService memberInfoService;

    @Autowired
    private GoogleCloudStorageService storageService;

    public MemberInfoEntity updateMemberProfile(String firebaseId, MemberInfoEntity updatedMember) {
        MemberInfoEntity existingMember = memberInfoService.findMemberByFirebaseId(firebaseId);
        
        existingMember.setFirstName(updatedMember.getFirstName());
        existingMember.setLastName(updatedMember.getLastName());
        existingMember.setEmail(updatedMember.getEmail());
        existingMember.setPhone(updatedMember.getPhone());
        existingMember.setBiography(updatedMember.getBiography());
        existingMember.setLocation(updatedMember.getLocation());
        
        return memberInfoService.saveMember(existingMember);
    }

    public MemberInfoEntity updateProfileImage(String firebaseId, MultipartFile newImage) throws IOException {
        MemberInfoEntity existingMember = memberInfoService.findMemberByFirebaseId(firebaseId);
        String newImageUrl = storageService.uploadFile(newImage, "profiles");
        existingMember.setProfilePic(newImageUrl);
        return memberInfoService.saveMember(existingMember);
    }
} 