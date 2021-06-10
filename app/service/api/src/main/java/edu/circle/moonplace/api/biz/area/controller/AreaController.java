package edu.circle.moonplace.api.biz.area.controller;

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

import edu.circle.moonplace.api.biz.area.domain.Area;
import edu.circle.moonplace.api.biz.area.dto.AreaDto;
import edu.circle.moonplace.api.biz.area.service.AreaService;
import edu.circle.moonplace.api.common.ApiResponse;
import edu.circle.moonplace.api.common.enums.StatusEnum;

@RestController
@RequestMapping(value = "/areas", produces = "application/json")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AreaDto>>> getAreaList() {
        try {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.OK, areaService.retrieveAreaList().stream()
                    .map(area -> modelMapper.map(area, AreaDto.class)).collect(Collectors.toList())));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<AreaDto>> getArea(@PathVariable Long id) throws Exception {
        try {
            return ResponseEntity
                    .ok(new ApiResponse<>(StatusEnum.OK, modelMapper.map(areaService.retrieveArea(id), AreaDto.class)));
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.NOT_FOUND, nsee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Long>> postArea(@RequestBody AreaDto area) {
        try {
            return ResponseEntity
                    .ok(new ApiResponse<>(StatusEnum.OK, areaService.insertArea(modelMapper.map(area, Area.class))));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Long>> putArea(@PathVariable Long id, @RequestBody AreaDto area) {
        try {
            areaService.updateArea(id, modelMapper.map(area, Area.class));
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.OK, id));
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.NOT_FOUND, nsee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Long>> deleteArea(@PathVariable Long id) {
        try {
            areaService.deleteArea(id);
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.OK, id));
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.NOT_FOUND, nsee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }
}
