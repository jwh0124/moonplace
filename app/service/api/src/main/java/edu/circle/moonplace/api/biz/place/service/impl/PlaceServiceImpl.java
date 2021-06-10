package edu.circle.moonplace.api.biz.place.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.circle.moonplace.api.biz.place.domain.Place;
import edu.circle.moonplace.api.biz.place.repository.PlaceRepository;
import edu.circle.moonplace.api.biz.place.service.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public List<Place> retrievePlaceList() {
        return placeRepository.findAll();
    }

    @Override
    public Optional<Place> retrievePlace(Long id) {
        return placeRepository.findById(id);
    }

    @Override
    public Long insertPlace(Place place) {
        placeRepository.save(place);
        return place.getId();
    }

    @Override
    public void updatePlace(Long id, Place place) {
        if (placeRepository.existsById(id)) {
            place.setId(id);
            placeRepository.save(place);
        }
    }

    @Override
    public void deletePlace(Long id) {
        placeRepository.deleteById(id);
    }

}
