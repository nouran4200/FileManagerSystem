package org.stc.uploadDownloadFiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.stc.uploadDownloadFiles.model.PermissionGroup;
import org.stc.uploadDownloadFiles.repository.PermissionGroupRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PermissionGroupService {

    @Autowired
    private PermissionGroupRepository permissionGroupRepository;

    public List<PermissionGroup> getAllPermissionGroups() {
        return permissionGroupRepository.findAll();
    }

    public Optional<PermissionGroup> getPermissionGroupById(Long groupId) {
        return permissionGroupRepository.findById(groupId);
    }

    public Optional<PermissionGroup> getPermissionGroupByName(String groupName) {
        return permissionGroupRepository.findByGroupName(groupName);
    }

    public PermissionGroup savePermissionGroup(PermissionGroup permissionGroup) {
        // Add any additional validation or business logic before saving
        return permissionGroupRepository.save(permissionGroup);
    }

    public void deletePermissionGroup(Long groupId) {
        permissionGroupRepository.deleteById(groupId);
    }

    // Additional business logic methods can be added based on your requirements

}
