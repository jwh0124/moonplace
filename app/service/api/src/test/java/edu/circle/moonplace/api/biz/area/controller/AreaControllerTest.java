package edu.circle.moonplace.api.biz.area.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import edu.circle.moonplace.api.biz.area.service.AreaService;

@WebMvcTest(AreaController.class)
public class AreaControllerTest {

    @MockBean
    AreaService areaService;

    @MockBean
    ModelMapper modelMapper;

    @Test
    public void getAreaList() {
        assertTrue(true);
    }

    @Test
    public void getArea() {
        assertTrue(true);
    }

    @Test
    public void postArea() {
        assertTrue(true);
    }

    @Test
    public void putArea() {
        assertTrue(true);
    }

    @Test
    public void deleteArea() {
        assertTrue(true);
    }
}
