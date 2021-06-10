package edu.circle.moonplace.api.biz.place.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import edu.circle.moonplace.api.common.ApiResponse;
import edu.circle.moonplace.api.common.enums.StatusEnum;

@RestController
@RequestMapping(value = "/places", produces = "application/json")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PlaceDto>>> getPlaceList() {
        try {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.OK, placeService.retrievePlaceList().stream()
                    .map(place -> modelMapper.map(place, PlaceDto.class)).collect(Collectors.toList())));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<PlaceDto>> getPlace(@PathVariable Long id) throws Exception {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(StatusEnum.OK, modelMapper.map(placeService.retrievePlace(id), PlaceDto.class)));
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.NOT_FOUND, nsee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Long>> postPlace(@RequestBody PlaceDto place) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(StatusEnum.OK, placeService.insertPlace(modelMapper.map(place, Place.class))));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Long>> putPlace(@PathVariable Long id, @RequestBody PlaceDto place) {
        try {
            placeService.updatePlace(id, modelMapper.map(place, Place.class));
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.OK, id));
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.NOT_FOUND, nsee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Long>> deletePlace(@PathVariable Long id) {
        try {
            placeService.deletePlace(id);
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.OK, id));
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.NOT_FOUND, nsee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }
}
