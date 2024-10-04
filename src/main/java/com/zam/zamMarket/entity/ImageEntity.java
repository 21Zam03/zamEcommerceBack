package com.zam.zamMarket.entity;

import com.zam.zamMarket.abstractClasses.AFile;
import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image")
public class ImageEntity extends AFile {

    @Column(name = "typeImage")
    private String typeImage;

    @Column(name = "height")
    private Double height;

    @Column(name = "width")
    private Double width;

    @Builder
    public ImageEntity(Integer fileId, String fileName, String description, Long fileSize, String extension, String url,
                       Boolean isActive, String typeImage, Double height, Double width) {
        super(fileId, fileName, description, fileSize, extension, url, isActive);
        this.typeImage = typeImage;
        this.height = height;
        this.width = width;
    }
}
