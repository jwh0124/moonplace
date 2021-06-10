package edu.circle.moonplace.api.biz.area.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

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
    public Area retrieveArea(Long id) {
        return areaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("not found areaId : " + id));
    }

    @Override
    public Long insertArea(Area area) {
        areaRepository.save(area);
        return area.getId();
    }

    @Override
    public void updateArea(Long id, Area area) {
        areaRepository.findById(id).ifPresentOrElse(getArea -> {
            area.setId(id);
            areaRepository.save(area);
        }, () -> {
            throw new NoSuchElementException("not found areaId : " + id);
        });
    }

    @Override
    public void deleteArea(Long id) {
        areaRepository.findById(id).ifPresentOrElse(getArea -> areaRepository.deleteById(id), () -> {
            throw new NoSuchElementException("not found areaId : " + id);
        });

    }

}
