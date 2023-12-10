package org.stc.uploadDownloadFiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.stc.uploadDownloadFiles.model.PermissionGroup;

import java.util.*;


public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {

    // Find permission group by group name
    Optional<PermissionGroup> findByGroupName(String groupName);

    // Custom query to find permission groups by user email
    @Query("SELECT pg FROM PermissionGroup pg JOIN pg.permissions p WHERE p.userEmail = :userEmail")
    List<PermissionGroup> findByUserEmail(@Param("userEmail") String userEmail);

}
