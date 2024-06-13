package com.elmehdikaalat.geoTourist.service.SiteTouristiqueService;

import com.elmehdikaalat.geoTourist.domain.entity.SiteTouristique;
import com.elmehdikaalat.geoTourist.presentation.DTO.SiteTouristiqueDto;
import com.elmehdikaalat.geoTourist.presentation.DTO.SiteTouristiqueResponseDto;

import java.util.List;

public interface SiteTouristiqueService {
        List<SiteTouristique> findAll();
        SiteTouristique save(SiteTouristiqueDto siteTouristiqueDto) throws Exception;
        List<SiteTouristiqueResponseDto> findAllWithImages();
        SiteTouristique update(Long id, SiteTouristiqueDto siteTouristiqueDto) throws Exception;
        void deleteById(Long id) throws Exception ;
}
