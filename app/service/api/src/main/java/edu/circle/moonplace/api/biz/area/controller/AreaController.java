package edu.circle.moonplace.api.biz.area.controller;

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

import edu.circle.moonplace.api.biz.area.domain.Area;
import edu.circle.moonplace.api.biz.area.dto.AreaDto;
import edu.circle.moonplace.api.biz.area.service.AreaService;

@RestController
@RequestMapping(value = "/areas", produces = "application/json")
public class AreaController {

    @Autowired
    AreaService areaService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<AreaDto> getAreaList() {
        return areaService.retrieveAreaList().stream().map(area -> modelMapper.map(area, AreaDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{areaId}")
    public AreaDto getArea(@PathVariable Long areaId) throws Exception {
        Optional<Area> area = areaService.retrieveArea(areaId);
        if (!area.isPresent()) {
            throw new Exception("No such data");
        }
        return modelMapper.map(area.get(), AreaDto.class);
    }

    @PostMapping
    public Long postArea(@RequestBody AreaDto area) {
        return areaService.insertArea(modelMapper.map(area, Area.class));
    }

    @PutMapping(path = "/{areaId}")
    public void putArea(@PathVariable Long areaId, @RequestBody AreaDto area) {
        areaService.updateArea(areaId, modelMapper.map(area, Area.class));
    }

    @DeleteMapping(path = "/{areaId}")
    public void deleteArea(@PathVariable Long areaId) {
        areaService.deleteArea(areaId);
    }
}
