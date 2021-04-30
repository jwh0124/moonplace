package edu.circle.moonplace.api.biz.area.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.circle.moonplace.api.biz.area.domain.Area;
import edu.circle.moonplace.api.biz.area.repository.AreaRepository;
import edu.circle.moonplace.api.biz.area.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    AreaRepository areaRepository;

    @Override
    public List<Area> retrieveAreaList() {
        return areaRepository.findAll();
    }

    @Override
    public Optional<Area> retrieveArea(Long areaId) {
        return areaRepository.findById(areaId);
    }

    @Override
    public void insertArea(Area area) {
        areaRepository.save(area);
    }

    @Override
    public void updateArea(Long areaId, Area area) {
        if (areaRepository.existsById(areaId)) {
            area.setId(areaId);
            areaRepository.save(area);
        }
    }

    @Override
    public void deleteArea(Long areaId) {
        areaRepository.deleteById(areaId);
    }

}
