package org.stc.uploadDownloadFiles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.stc.uploadDownloadFiles.model.Item;
import org.stc.uploadDownloadFiles.model.Permissions;
import org.stc.uploadDownloadFiles.service.ItemService;
import org.stc.uploadDownloadFiles.service.PermissionsService;

import java.util.List;

@RestController
@RequestMapping("/api/folder")
public class FolderController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private PermissionsService permissionsService;

    @PostMapping("/create")
    public ResponseEntity<Void> createFolder(@RequestParam Long spaceItemId, @RequestParam String folderName, @RequestParam String userEmail, @RequestParam String permissionLevel) {
        // Check if the user has EDIT access on the space
        if (!permissionsService.hasPermission(userEmail, spaceItemId, "EDIT")) {
            return ResponseEntity.status(403).build(); // Forbidden
        }

        // Create a folder under the specified space
        Item spaceItem = itemService.getItemById(spaceItemId)
                .orElseThrow(() -> new IllegalArgumentException("Space not found with ID: " + spaceItemId));

        Item folderItem = new Item();
        folderItem.setType("Folder");
        folderItem.setName(folderName);
        folderItem.setPermissionGroup(spaceItem.getPermissionGroup());
        folderItem.setParent(spaceItem);
        itemService.saveItem(folderItem);

        // Assign permissions for the user on the new folder
        Permissions folderPermission = new Permissions(userEmail, permissionLevel, folderItem);
        permissionsService.savePermissions(folderPermission);

        return ResponseEntity.ok().build();
    }

    // Additional APIs for folder management can be added based on your requirements

}
