package org.stc.uploadDownloadFiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.stc.uploadDownloadFiles.model.Item;
import org.stc.uploadDownloadFiles.model.Permissions;
import org.stc.uploadDownloadFiles.repository.PermissionsRepository;

import java.util.*;

@Service
@Transactional
public class PermissionsService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private PermissionsRepository permissionsRepository;

    public List<Permissions> getAllPermissions() {
        return permissionsRepository.findAll();
    }

    public Optional<Permissions> getPermissionsById(Long permissionsId) {
        return permissionsRepository.findById(permissionsId);
    }

    public List<Permissions> getPermissionsByUserEmail(String userEmail) {
        return permissionsRepository.findByUserEmail(userEmail);
    }

    public List<Permissions> getPermissionsByUserEmailAndPermissionLevel(String userEmail, String permissionLevel) {
        return permissionsRepository.findByUserEmailAndPermissionLevel(userEmail, permissionLevel);
    }

    public Permissions savePermissions(Permissions permissions) {
        // Add any additional validation or business logic before saving
        return permissionsRepository.save(permissions);
    }

    public void deletePermissions(Long permissionsId) {
        permissionsRepository.deleteById(permissionsId);
    }


    public boolean hasPermission(String userEmail, Long itemId, String requiredPermission) {
        // Retrieve the item by its ID along with its permissions
        Item item = itemService.getItemByIdWithPermissions(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with ID: " + itemId));

        // Check if the user has the required permission
        return item.getPermissions().stream()
                .anyMatch(permission -> permission.getUserEmail().equals(userEmail) && permission.getPermissionLevel().equals(requiredPermission));
    }
}
