package com.elmehdikaalat.geoTourist.presentation.controller;

import com.elmehdikaalat.geoTourist.domain.entity.SiteTouristique;
import com.elmehdikaalat.geoTourist.presentation.DTO.SiteTouristiqueDto;
import com.elmehdikaalat.geoTourist.presentation.DTO.SiteTouristiqueResponseDto;
import com.elmehdikaalat.geoTourist.service.SiteTouristiqueService.SiteTouristiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/siteTouristiques")
public class SiteTouristiqueController {

    private final SiteTouristiqueService siteTouristiqueService;

    @Autowired
    public SiteTouristiqueController(SiteTouristiqueService siteTouristiqueService) {
        this.siteTouristiqueService = siteTouristiqueService;
    }
    @PostMapping("/createSite")
    public ResponseEntity<SiteTouristique> createSite(@RequestBody SiteTouristiqueDto siteTouristiqueDto) throws Exception {
        SiteTouristique createdSite = siteTouristiqueService.save(siteTouristiqueDto);
        return ResponseEntity.ok(createdSite);
    }
    @GetMapping
    public List<SiteTouristiqueResponseDto> getAllSitesWithImages() {
        return siteTouristiqueService.findAllWithImages();
    }
    @PutMapping("/updateSite/{id}")
    public ResponseEntity<SiteTouristique> updateSite(@PathVariable Long id, @RequestBody SiteTouristiqueDto siteTouristiqueDto) throws Exception {
        SiteTouristique updatedSite = siteTouristiqueService.update(id, siteTouristiqueDto);
        return ResponseEntity.ok(updatedSite);
    }
    @DeleteMapping("/deleteSite/{id}")
    public ResponseEntity<Void> deleteSite(@PathVariable Long id) {
        try {
            siteTouristiqueService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
