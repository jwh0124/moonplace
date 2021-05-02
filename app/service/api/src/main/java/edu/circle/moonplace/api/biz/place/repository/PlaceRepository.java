package edu.circle.moonplace.api.biz.place.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.circle.moonplace.api.biz.place.domain.Place;

public interface PlaceRepository extends JpaRepository<Place, Long>{
    
}
