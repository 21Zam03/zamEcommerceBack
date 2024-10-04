package com.zam.zamMarket.abstractClasses;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@NoArgsConstructor
public abstract class AFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fileId")
    private Integer fileId;

    @Column(name = "name")
    private String fileName;

    @Column(name = "description")
    private String description;

    @Column(name = "size")
    private Long fileSize;

    @Column(name = "extension")
    private String extension;

    @Column(name = "url")
    private String url;

    @Column(name = "active")
    private Boolean isActive;

    public AFile(Integer fileId, String fileName, String description, Long fileSize, String extension, String url,
                 Boolean isActive) {
        this.fileName = fileName;
        this.description = description;
        this.fileSize = fileSize;
        this.fileId = fileId;
        this.extension = extension;
        this.url = url;
        this.isActive = isActive;
    }

}
