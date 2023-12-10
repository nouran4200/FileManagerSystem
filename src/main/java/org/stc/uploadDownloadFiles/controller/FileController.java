package org.stc.uploadDownloadFiles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.stc.uploadDownloadFiles.model.Files;
import org.stc.uploadDownloadFiles.model.Item;
import org.stc.uploadDownloadFiles.model.Permissions;
import org.stc.uploadDownloadFiles.service.FilesService;
import org.stc.uploadDownloadFiles.service.ItemService;
import org.stc.uploadDownloadFiles.service.PermissionsService;

import java.io.*;
import java.util.*;


@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private FilesService filesService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private PermissionsService permissionsService;

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadFile(@RequestParam Long folderItemId, @RequestParam String userEmail, @RequestParam String permissionLevel, @RequestParam("file") MultipartFile file) throws IOException {
        // Check if the user has EDIT access on the folder
        if (!permissionsService.hasPermission(userEmail, folderItemId, "EDIT")) {
            return ResponseEntity.status(403).build(); // Forbidden
        }

        // Create a file under the specified folder
        Item folderItem = itemService.getItemById(folderItemId)
                .orElseThrow(() -> new IllegalArgumentException("Folder not found with ID: " + folderItemId));

        Files fileEntity = new Files();
        fileEntity.setBinary(file.getBytes());
        fileEntity.setItem(folderItem);
        filesService.saveFile(fileEntity, folderItemId);

        // Assign permissions for the user on the new file
        Permissions filePermission = new Permissions(userEmail, permissionLevel, folderItem.getPermissionGroup());
        permissionsService.savePermissions(filePermission);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/metadata/{fileId}")
    public ResponseEntity<String> viewFileMetadata(@PathVariable Long fileId, @RequestParam String userEmail) {
        // Check if the user has VIEW access on the file
        if (!permissionsService.hasPermission(userEmail, fileId, "VIEW")) {
            return ResponseEntity.status(403).build(); // Forbidden
        }

        // Implement logic to retrieve file metadata (replace the string with your logic)
        String metadata = "File metadata for file with ID: " + fileId;
        return ResponseEntity.ok(metadata);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId, @RequestParam String userEmail) throws IOException {
        // Check if the user has VIEW access on the file
        if (!permissionsService.hasPermission(userEmail, fileId, "VIEW")) {
            return ResponseEntity.status(403).build(); // Forbidden
        }

        // Retrieve file entity by ID
        Files fileEntity = filesService.getFileById(fileId)
                .orElseThrow(() -> new IllegalArgumentException("File not found with ID: " + fileId));

        // Prepare file response for download
        ByteArrayResource resource = new ByteArrayResource(fileEntity.getBinary());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileEntity.getItem().getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(fileEntity.getBinary().length)
                .body(resource);
    }

    // Additional APIs for file management can be added based on your requirements

}
