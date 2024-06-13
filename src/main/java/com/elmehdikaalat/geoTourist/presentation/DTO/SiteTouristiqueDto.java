package com.elmehdikaalat.geoTourist.presentation.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SiteTouristiqueDto {
    private String description;
    private String geom;
    private String name;
    private Long idUser;
    private List<String> images;
    private List<Long> categoryIds;
}
