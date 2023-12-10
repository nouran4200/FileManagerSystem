package org.stc.uploadDownloadFiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.stc.uploadDownloadFiles.model.Item;
import org.stc.uploadDownloadFiles.model.PermissionGroup;

import java.util.*;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // Find items by type
    List<Item> findByType(String type);

    // Find items by name
    List<Item> findByName(String name);

    // Find items by permission group
    List<Item> findByPermissionGroup(PermissionGroup permissionGroup);

    // Custom query to find items by permission group name
    @Query("SELECT i FROM Item i WHERE i.permissionGroup.groupName = :groupName")
    List<Item> findByPermissionGroupName(@Param("groupName") String groupName);

    // Custom query to find items by type and permission group
    @Query("SELECT i FROM Item i WHERE i.type = :type AND i.permissionGroup = :permissionGroup")
    List<Item> findByTypeAndPermissionGroup(@Param("type") String type, @Param("permissionGroup") PermissionGroup permissionGroup);

    @Query("SELECT i FROM Item i LEFT JOIN FETCH i.permissions WHERE i.id = :id")
    Optional<Item> findByIdWithPermissions(@Param("id") Long id);

}
