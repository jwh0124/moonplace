package edu.circle.moonplace.api.biz.area.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.circle.moonplace.api.biz.area.domain.Area;

public interface AreaRepository extends JpaRepository<Area, Long> {

}
