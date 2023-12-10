package org.stc.uploadDownloadFiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.stc.uploadDownloadFiles.model.Item;
import org.stc.uploadDownloadFiles.model.PermissionGroup;
import org.stc.uploadDownloadFiles.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;


    public Optional<Item> getItemByIdWithPermissions(Long id) {
        return itemRepository.findByIdWithPermissions(id);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(Long itemId) {
        return itemRepository.findById(itemId);
    }

    public List<Item> getItemsByType(String type) {
        return itemRepository.findByType(type);
    }

    public List<Item> getItemsByPermissionGroup(PermissionGroup permissionGroup) {
        return itemRepository.findByPermissionGroup(permissionGroup);
    }

    public List<Item> getItemsByPermissionGroupName(String groupName) {
        return itemRepository.findByPermissionGroupName(groupName);
    }

    public List<Item> getItemsByTypeAndPermissionGroup(String type, PermissionGroup permissionGroup) {
        return itemRepository.findByTypeAndPermissionGroup(type, permissionGroup);
    }

    public Item saveItem(Item item) {
        // Add any additional validation or business logic before saving
        return itemRepository.save(item);
    }

    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    // Additional business logic methods can be added based on your requirements

}
