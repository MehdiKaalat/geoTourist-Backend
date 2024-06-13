package com.elmehdikaalat.geoTourist.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "image_st")
public class ImageST {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    @Column(name = "image_data")
    private byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "id_tourist_site", nullable = false)
    @JsonBackReference
    private SiteTouristique siteTouristique;

}
