package edu.circle.moonplace.api.biz.place.service;

import java.util.List;
import java.util.Optional;

import edu.circle.moonplace.api.biz.place.domain.Place;

public interface PlaceService {
    List<Place> retrievePlaceList();

    Optional<Place> retrievePlace(Long placeId);

    Long insertPlace(Place place);

    void updatePlace(Long placeId, Place place);

    void deletePlace(Long placeId);
}
