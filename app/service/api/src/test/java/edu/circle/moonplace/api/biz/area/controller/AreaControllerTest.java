package edu.circle.moonplace.api.biz.area.controller;

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

import edu.circle.moonplace.api.biz.area.dto.AreaDto;
import edu.circle.moonplace.api.biz.area.service.AreaService;

@ActiveProfiles("test")
@WebMvcTest(AreaController.class)
public class AreaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AreaService areaService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void getAreaList() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/areas").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getArea() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/areas/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postArea() throws Exception {
        AreaDto area = AreaDto.builder().name("name").build();

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/areas").content(objectMapper.writeValueAsString(area))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void putArea() throws Exception {
        AreaDto area = AreaDto.builder().name("name").build();

        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/areas/{id}", 1L).content(objectMapper.writeValueAsString(area))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteArea() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/areas/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}
