package org.stc.uploadDownloadFiles.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class PermissionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;

    @OneToMany(mappedBy = "group")
    private List<Permissions> permissions;

    public PermissionGroup() {
        // Default constructor
    }

    public PermissionGroup(String groupName) {
        this.groupName = groupName;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}