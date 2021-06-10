package edu.circle.moonplace.api.biz.area.service;

import java.util.List;
import java.util.Optional;

import edu.circle.moonplace.api.biz.area.domain.Area;

public interface AreaService {
    List<Area> retrieveAreaList();

    Optional<Area> retrieveArea(Long id);

    Long insertArea(Area area);

    void updateArea(Long id, Area area);

    void deleteArea(Long id);
}
