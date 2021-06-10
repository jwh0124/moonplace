package edu.circle.moonplace.api.biz.place.controller;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import edu.circle.moonplace.api.biz.place.dto.PlaceDto;
import edu.circle.moonplace.api.biz.place.service.PlaceService;

@ActiveProfiles("test")
@WebMvcTest(PlaceController.class)
public class PlaceControllerTest {

    @MockBean
    private PlaceService placeService;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getPlaceList() throws Exception {
            this.mockMvc.perform(MockMvcRequestBuilders.get("/places").contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getPlace() throws Exception {
            this.mockMvc.perform(MockMvcRequestBuilders.get("/places/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postPlace() throws Exception {
            PlaceDto place = PlaceDto.builder().name("name").addr("addr").phone("phone").rate(5)
                            .description("description").visitDt(LocalDateTime.now()).build();

            this.mockMvc.perform(MockMvcRequestBuilders.post("/places").content(objectMapper.writeValueAsString(place))
                            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                            .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void putPlace() throws Exception {
            PlaceDto place = PlaceDto.builder().name("name").addr("addr").phone("phone").rate(5)
                            .description("description").visitDt(LocalDateTime.now()).build();

            this.mockMvc.perform(MockMvcRequestBuilders.put("/places/{id}", 1L)
                            .content(objectMapper.writeValueAsString(place)).contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deletePlace() throws Exception {
            this.mockMvc.perform(MockMvcRequestBuilders.delete("/places/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                            .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}
