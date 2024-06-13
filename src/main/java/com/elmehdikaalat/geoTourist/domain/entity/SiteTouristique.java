package com.elmehdikaalat.geoTourist.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "site_touristique")
public class SiteTouristique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTouristSite;

    private String name;

    @Column(columnDefinition = "Geometry")
    private Geometry geom;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    @JsonBackReference
    private User userSet;

    @OneToMany(mappedBy = "siteTouristique", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ImageST> images = new ArrayList<>();

    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "site_category",
            joinColumns = @JoinColumn(name = "site_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SiteTouristique that = (SiteTouristique) o;
        return getIdTouristSite() != null && Objects.equals(getIdTouristSite(), that.getIdTouristSite());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
