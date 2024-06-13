package com.elmehdikaalat.geoTourist.presentation.DTO;


import com.elmehdikaalat.geoTourist.domain.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SiteTouristiqueResponseDto {
    private Long idTouristSite;
    private String name;
    private String description;
    private String geom;
    private List<Category> reviews;
    private List<Category> categories;
    private Long idUser;
    private List<String> images;
}

