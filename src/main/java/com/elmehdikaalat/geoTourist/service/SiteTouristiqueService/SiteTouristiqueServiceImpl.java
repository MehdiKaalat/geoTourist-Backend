package com.elmehdikaalat.geoTourist.service.SiteTouristiqueService;

import com.elmehdikaalat.geoTourist.domain.entity.Category;
import com.elmehdikaalat.geoTourist.domain.entity.ImageST;
import com.elmehdikaalat.geoTourist.domain.entity.SiteTouristique;
import com.elmehdikaalat.geoTourist.domain.entity.User;
import com.elmehdikaalat.geoTourist.domain.repository.CategoryRepository;
import com.elmehdikaalat.geoTourist.domain.repository.SiteTouristiqueRepository;
import com.elmehdikaalat.geoTourist.domain.repository.UserRepository;
import com.elmehdikaalat.geoTourist.presentation.DTO.SiteTouristiqueDto;
import com.elmehdikaalat.geoTourist.presentation.DTO.SiteTouristiqueResponseDto;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SiteTouristiqueServiceImpl implements SiteTouristiqueService {

    private final SiteTouristiqueRepository repository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public SiteTouristiqueServiceImpl(SiteTouristiqueRepository repository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<SiteTouristique> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional
    public SiteTouristique save(SiteTouristiqueDto siteTouristiqueDto) throws Exception {
        WKTReader reader = new WKTReader();
        Geometry geometry = reader.read(siteTouristiqueDto.getGeom());

        SiteTouristique siteTouristique = new SiteTouristique();
        siteTouristique.setDescription(siteTouristiqueDto.getDescription());
        siteTouristique.setGeom(geometry);
        siteTouristique.setName(siteTouristiqueDto.getName());

        List<Category> categories = categoryRepository.findAllById(siteTouristiqueDto.getCategoryIds());

        siteTouristique.setCategories(categories);

        User user = userRepository.findById(siteTouristiqueDto.getIdUser()).orElseThrow(() -> new Exception("User not found"));
        siteTouristique.setUserSet(user);

        for (String base64Image : siteTouristiqueDto.getImages()) {
            ImageST imageST = new ImageST();
            try {
                byte[] imageData = Base64.getDecoder().decode(base64Image.trim());
                imageST.setImageData(imageData);
            } catch (IllegalArgumentException e) {
                throw new Exception("Invalid base64 image data", e);
            }
            imageST.setSiteTouristique(siteTouristique);
            siteTouristique.getImages().add(imageST);
        }

        return repository.save(siteTouristique);
    }
    @Override
    public List<SiteTouristiqueResponseDto> findAllWithImages() {
        List<SiteTouristique> sites = repository.findAll();
        return sites.stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    private SiteTouristiqueResponseDto mapToResponseDto(SiteTouristique site) {
        SiteTouristiqueResponseDto responseDto = new SiteTouristiqueResponseDto();
        responseDto.setIdTouristSite(site.getIdTouristSite());
        responseDto.setName(site.getName());
        responseDto.setGeom(site.getGeom().toString());
        responseDto.setDescription(site.getDescription());
        responseDto.setIdUser(site.getUserSet().getIdUser());
        List<String> images = site.getImages().stream()
                .map(image -> Base64.getEncoder().encodeToString(image.getImageData()))
                .collect(Collectors.toList());
        responseDto.setImages(images);
        responseDto.setCategories(site.getCategories());
        return responseDto;
    }
    @Override
    @Transactional
    public SiteTouristique update(Long id, SiteTouristiqueDto siteTouristiqueDto) throws Exception {
        WKTReader reader = new WKTReader();
        Geometry geometry = reader.read(siteTouristiqueDto.getGeom());

        SiteTouristique siteTouristique = repository.findById(id).orElseThrow(() -> new Exception("Site not found"));

        siteTouristique.setDescription(siteTouristiqueDto.getDescription());
        siteTouristique.setGeom(geometry);
        siteTouristique.setName(siteTouristiqueDto.getName());

        User user = userRepository.findById(siteTouristiqueDto.getIdUser()).orElseThrow(() -> new Exception("User not found"));
        siteTouristique.setUserSet(user);

        List<Category> categories = categoryRepository.findAllById(siteTouristiqueDto.getCategoryIds());
        siteTouristique.setCategories(categories);

        siteTouristique.getImages().clear(); // Clear existing images
        for (String base64Image : siteTouristiqueDto.getImages()) {
            ImageST imageST = new ImageST();
            try {
                byte[] imageData = Base64.getDecoder().decode(base64Image.trim());
                imageST.setImageData(imageData);
            } catch (IllegalArgumentException e) {
                throw new Exception("Invalid base64 image data", e);
            }
            imageST.setSiteTouristique(siteTouristique);
            siteTouristique.getImages().add(imageST);
        }

        return repository.save(siteTouristique);
    }
    @Override
    @Transactional
    public void deleteById(Long id) throws Exception {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new Exception("Site not found");
        }
    }


}
