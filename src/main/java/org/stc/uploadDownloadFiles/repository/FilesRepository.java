package org.stc.uploadDownloadFiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.stc.uploadDownloadFiles.model.Files;
import org.stc.uploadDownloadFiles.model.Item;

import java.util.List;

public interface FilesRepository extends JpaRepository<Files, Long> {

    // Find files by item
    List<Files> findByItem(Item item);

    // Custom query to find files by item name
    @Query("SELECT f FROM Files f WHERE f.item.name = :itemName")
    List<Files> findByItemName(@Param("itemName") String itemName);

    // Custom query to find files by item type
    @Query("SELECT f FROM Files f WHERE f.item.type = :itemType")
    List<Files> findByItemType(@Param("itemType") String itemType);

    // Additional custom queries can be added based on your specific needs

}
