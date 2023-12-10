package org.stc.uploadDownloadFiles.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String name;


    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Item parent;

    public List<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }

    @ManyToOne
    @JoinColumn(name = "permission_group_id")
    private PermissionGroup permissionGroup;


    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Permissions> permissions;

    // Constructors
    public Item() {
        // Default constructor
    }

    public Item(String type, String name, PermissionGroup permissionGroup, Item parent) {
        this.type = type;
        this.name = name;
        this.permissionGroup = permissionGroup;
        this.parent = parent;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PermissionGroup getPermissionGroup() {
        return permissionGroup;
    }

    public void setPermissionGroup(PermissionGroup permissionGroup) {
        this.permissionGroup = permissionGroup;
    }

    public Item getParent() {
        return parent;
    }

    public void setParent(Item parent) {
        this.parent = parent;
    }

}