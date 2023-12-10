package org.stc.uploadDownloadFiles.model;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "file")
    private byte[] binary;

    @OneToOne
    private Item item;

    // Constructors
    public Files() {
        // Default constructor
    }

    public Files(byte[] binary, Item item) {
        this.binary = binary;
        this.item = item;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getBinary() {
        return binary;
    }

    public void setBinary(byte[] binary) {
        this.binary = binary;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}