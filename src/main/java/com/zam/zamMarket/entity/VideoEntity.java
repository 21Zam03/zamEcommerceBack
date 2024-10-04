package com.zam.zamMarket.entity;

import com.zam.zamMarket.abstractClasses.AFile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "video")
public class VideoEntity extends AFile {

    @Column(name = "duration")
    private Double duration;

    @Column(name = "quality")
    private String quality;

}
