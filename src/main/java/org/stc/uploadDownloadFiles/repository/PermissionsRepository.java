package org.stc.uploadDownloadFiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.stc.uploadDownloadFiles.model.Permissions;

import java.util.*;

public interface PermissionsRepository extends JpaRepository<Permissions, Long> {

    // Find permissions by user email
    List<Permissions> findByUserEmail(String userEmail);

    // Custom query to find permissions by user email and permission level
    @Query("SELECT p FROM Permissions p WHERE p.userEmail = :userEmail AND p.permissionLevel = :permissionLevel")
    List<Permissions> findByUserEmailAndPermissionLevel(@Param("userEmail") String userEmail, @Param("permissionLevel") String permissionLevel);

    // Additional custom queries can be added based on your specific needs

}
