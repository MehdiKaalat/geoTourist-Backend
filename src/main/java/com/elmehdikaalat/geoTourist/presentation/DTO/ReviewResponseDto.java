package com.elmehdikaalat.geoTourist.presentation.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponseDto {
    private Long idReview;
    private String comment;
    private int rating;
}
