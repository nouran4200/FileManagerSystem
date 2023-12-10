package org.stc.uploadDownloadFiles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stc.uploadDownloadFiles.model.Item;
import org.stc.uploadDownloadFiles.model.PermissionGroup;
import org.stc.uploadDownloadFiles.model.Permissions;
import org.stc.uploadDownloadFiles.service.ItemService;
import org.stc.uploadDownloadFiles.service.PermissionGroupService;
import org.stc.uploadDownloadFiles.service.PermissionsService;

import java.util.List;

@RestController
@RequestMapping("/api/space")
public class SpaceController {

    @Autowired
    private PermissionGroupService permissionGroupService;

    @Autowired
    private PermissionsService permissionService;

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Void> createSpace() {
        // Create "stc-assessments" space and assign a permission group
        PermissionGroup adminGroup = new PermissionGroup();
        adminGroup.setGroupName("admin");
        adminGroup = permissionGroupService.savePermissionGroup(adminGroup);

        // Create a space item
        Item spaceItem = new Item();
        spaceItem.setType("Space");
        spaceItem.setName("stc-assessments");
        spaceItem.setPermissionGroup(adminGroup);
        spaceItem = itemService.saveItem(spaceItem); // Save and get the persisted item

        // Assign VIEW and EDIT access to users in the admin group
        Permissions viewPermission = new Permissions("guest@example.com", "VIEW", spaceItem);
        Permissions editPermission = new Permissions("admin@example.com", "EDIT", spaceItem);
        permissionService.savePermissions(viewPermission);
        permissionService.savePermissions(editPermission);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        // Get all items in the space
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    // Additional APIs for space management can be added based on your requirements

}
