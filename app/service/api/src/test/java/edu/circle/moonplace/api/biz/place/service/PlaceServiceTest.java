package edu.circle.moonplace.api.biz.place.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import edu.circle.moonplace.api.biz.place.domain.Place;
import edu.circle.moonplace.api.biz.place.repository.PlaceRepository;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@Transactional
public class PlaceServiceTest {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PlaceRepository placeRepository;

    private Place savePlace;

    @BeforeEach
    public void beforeEach() {
        savePlace = placeRepository.save(Place.builder().name("name").addr("addr").phone("phone").rate(5)
                .description("description").visitDt(Instant.now()).build());
    }

    @AfterEach
    public void afterEach() {
        placeRepository.deleteAll();
    }

    @Test
    public void retrievePlaceList() {
        // given - beforeEach

        // when
        List<Place> placeList = placeService.retrievePlaceList();

        // then
        Assertions.assertThat(placeList.size()).isEqualTo(1);
    }

    @Test
    public void retrievePlace() {
        // given - beforeEach

        // when
        Optional<Place> place = placeService.retrievePlace(savePlace.getId());

        // then
        Assertions.assertThat(place).isNotNull();
    }

    @Test
    public void insertPlace() {
        // given
        Place place = Place.builder().name("name").addr("addr").phone("phone").rate(5).description("description")
                .visitDt(Instant.now()).build();

        // when
        Long insertPlaceId = placeService.insertPlace(place);
        Place findPlace = placeRepository.findById(insertPlaceId).get();

        // then
        Assertions.assertThat(place.getId()).isEqualTo(findPlace.getId());
    }

    @Test
    public void updatePlace() {
        // given - beforeEach
        Place place = Place.builder().name("name").addr("addr").phone("phone").rate(5).description("description")
                .visitDt(Instant.now()).build();

        // when
        placeService.updatePlace(savePlace.getId(), place);
        Place updatePlace = placeRepository.findById(savePlace.getId()).get();

        // then
        Assertions.assertThat(updatePlace.getName()).isEqualTo(place.getName());
    }

    @Test
    public void deletePlace() {
        // given - beforeEach

        // when
        placeService.deletePlace(savePlace.getId());

        // then
        Assertions.assertThat(placeRepository.count()).isZero();
    }
}