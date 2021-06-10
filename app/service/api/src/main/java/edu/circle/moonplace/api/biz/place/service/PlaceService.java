package edu.circle.moonplace.api.biz.place.service;

import java.util.List;

import edu.circle.moonplace.api.biz.place.domain.Place;

public interface PlaceService {
    List<Place> retrievePlaceList();

    Place retrievePlace(Long id);

    Long insertPlace(Place place);

    void updatePlace(Long id, Place place);

    void deletePlace(Long id);
}
