package edu.circle.moonplace.api.biz.place.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

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
    public Place retrievePlace(Long id) {
        return placeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("not found placeId : " + id));
    }

    @Override
    public Long insertPlace(Place place) {
        placeRepository.save(place);
        return place.getId();
    }

    @Override
    public void updatePlace(Long id, Place place) {
        placeRepository.findById(id).ifPresentOrElse(getPlace -> {
            place.setId(id);
            placeRepository.save(place);
        }, () -> {
            throw new NoSuchElementException("not found placeId :" + id);
        });
    }

    @Override
    public void deletePlace(Long id) {
        placeRepository.findById(id).ifPresentOrElse(getPlace -> placeRepository.deleteById(id), () -> {
            throw new NoSuchElementException("not found placeId :" + id);
        });
    }
}
