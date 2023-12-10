package org.stc.uploadDownloadFiles.model;

import javax.persistence.*;

@Entity
public class Permissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userEmail;
    private String permissionLevel;

    @ManyToOne
    private PermissionGroup group;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public Permissions() {
        // Default constructor
    }

    public Permissions(String userEmail, String permissionLevel, PermissionGroup group) {
        this.userEmail = userEmail;
        this.permissionLevel = permissionLevel;
        this.group = group;
    }

    public Permissions(String userEmail, String permissionLevel, Item item) {
        this.userEmail = userEmail;
        this.permissionLevel = permissionLevel;
        this.item = item;
    }


    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public PermissionGroup getGroup() {
        return group;
    }

    public void setGroup(PermissionGroup group) {
        this.group = group;
    }
}