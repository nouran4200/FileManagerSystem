package org.stc.uploadDownloadFiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.stc.uploadDownloadFiles.model.Files;
import org.stc.uploadDownloadFiles.model.Item;
import org.stc.uploadDownloadFiles.repository.FilesRepository;

import java.util.*;

@Service
@Transactional
public class FilesService {

    @Autowired
    private FilesRepository filesRepository;

    @Autowired
    private ItemService itemService; // Assuming you have an ItemService

    public List<Files> getAllFiles() {
        return filesRepository.findAll();
    }

    public Optional<Files> getFileById(Long fileId) {
        return filesRepository.findById(fileId);
    }

    public List<Files> getFilesByItem(Item item) {
        return filesRepository.findByItem(item);
    }

    public List<Files> getFilesByItemName(String itemName) {
        return filesRepository.findByItemName(itemName);
    }

    public List<Files> getFilesByItemType(String itemType) {
        return filesRepository.findByItemType(itemType);
    }

    public Files saveFile(Files file, Long itemId) {
        Optional<Item> itemOptional = itemService.getItemById(itemId);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            file.setItem(item);
            // Add any additional validation or business logic before saving
            return filesRepository.save(file);
        } else {
            throw new IllegalArgumentException("Item not found with ID: " + itemId);
        }
    }

    public void deleteFile(Long fileId) {
        filesRepository.deleteById(fileId);
    }

    // Additional business logic methods can be added based on your requirements

}
