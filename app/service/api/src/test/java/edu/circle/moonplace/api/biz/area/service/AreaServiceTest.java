package edu.circle.moonplace.api.biz.area.service;

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

import edu.circle.moonplace.api.biz.area.domain.Area;
import edu.circle.moonplace.api.biz.area.repository.AreaRepository;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@Transactional
public class AreaServiceTest {

    @Autowired
    private AreaService areaService;

    @Autowired
    private AreaRepository areaRepository;

    private Area saveArea;

    @BeforeEach
    public void beforeEach() {
        saveArea = areaRepository.save(Area.builder().name("name").build());
    }

    @AfterEach
    public void afterEach() {
        areaRepository.deleteAll();
    }

    @Test
    public void retrieveAreaTest() {
        // given - beforeEach

        // when
        List<Area> areaList = areaService.retrieveAreaList();

        // then
        Assertions.assertThat(areaList.size()).isEqualTo(1);
    }

    @Test
    public void retrieveArea() {
        // given - beforeEach

        // when
        Optional<Area> area = areaService.retrieveArea(saveArea.getId());

        // then
        Assertions.assertThat(area).isNotNull();
    }

    @Test
    public void insertArea() {
        // given
        Area area = Area.builder().name("name").build();

        // when
        Long insertAreaId = areaService.insertArea(area);
        Area findArea = areaRepository.findById(insertAreaId).get();

        // then
        Assertions.assertThat(findArea.getId()).isEqualTo(area.getId());
    }

    @Test
    public void updateArea() {
        // given - beforeEach
        Area area = Area.builder().name("name").build();

        // when
        areaService.updateArea(saveArea.getId(), area);
        Area findArea = areaRepository.findById(saveArea.getId()).get();

        // then
        Assertions.assertThat(findArea.getName()).isEqualTo(area.getName());
    }

    @Test
    public void deleteArea() {
        // given - beforeEach

        // when
        areaService.deleteArea(saveArea.getId());

        // then
        Assertions.assertThat(areaRepository.count()).isZero();
    }
}
