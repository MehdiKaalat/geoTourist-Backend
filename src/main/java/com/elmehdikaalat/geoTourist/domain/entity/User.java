package com.elmehdikaalat.geoTourist.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String username;
    private String email;
    private String password;
    private String imagePdp;

    @OneToMany(mappedBy = "userSet")
    private List<Review> reviews;

    @OneToMany(mappedBy = "userSet")
    @JsonManagedReference
    private List<SiteTouristique> sitesTouristiques;

}
