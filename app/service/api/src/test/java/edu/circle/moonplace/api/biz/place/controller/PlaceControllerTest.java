package edu.circle.moonplace.api.biz.place.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import edu.circle.moonplace.api.biz.place.service.PlaceService;

@WebMvcTest(PlaceController.class)
public class PlaceControllerTest {

    @MockBean
    PlaceService placeService;

    @MockBean
    ModelMapper modelMapper;

    @Test
    public void getPlaceList() {
        assertTrue(true);
    }

    @Test
    public void getPlace() {
        assertTrue(true);
    }

    @Test
    public void postPlace() {
        assertTrue(true);
    }

    @Test
    public void putPlace() {
        assertTrue(true);
    }

    @Test
    public void deletePlace() {
        assertTrue(true);
    }
}
