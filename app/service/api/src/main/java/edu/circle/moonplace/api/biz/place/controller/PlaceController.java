package edu.circle.moonplace.api.biz.place.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.circle.moonplace.api.biz.place.domain.Place;
import edu.circle.moonplace.api.biz.place.dto.PlaceDto;
import edu.circle.moonplace.api.biz.place.service.PlaceService;

@RestController
@RequestMapping(value = "/places", produces = "application/json")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<PlaceDto> getPlaceList() {
        return placeService.retrievePlaceList().stream().map(place -> modelMapper.map(place, PlaceDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public PlaceDto getPlace(@PathVariable Long id) throws Exception {
        Optional<Place> place = placeService.retrievePlace(id);

        if (!place.isPresent()) {
            throw new Exception("no such data");
        }

        return modelMapper.map(place.get(), PlaceDto.class);
    }

    @PostMapping
    public Long postPlace(@RequestBody PlaceDto place) {
        return placeService.insertPlace(modelMapper.map(place, Place.class));
    }

    @PutMapping(path = "/{id}")
    public void putPlace(@PathVariable Long id, @RequestBody PlaceDto place) {
        placeService.updatePlace(id, modelMapper.map(place, Place.class));
    }

    @DeleteMapping(path = "/{id}")
    public void deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id);
    }
}
