package edu.circle.moonplace.api.biz.setting.controller;

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

import edu.circle.moonplace.api.biz.setting.dto.SettingDto;
import edu.circle.moonplace.api.biz.setting.service.SettingService;

@ActiveProfiles("test")
@WebMvcTest(SettingController.class)
public class SettingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SettingService settingService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void getSettingList() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/settings").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getSetting() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/settings/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postSetting() throws Exception {
        SettingDto setting = SettingDto.builder().code("test").value("test").build();

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/settings").content(objectMapper.writeValueAsString(setting))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void putSetting() throws Exception {
        SettingDto setting = SettingDto.builder().code("test").value("test").build();

        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/settings/{id}", 1L)
                        .content(objectMapper.writeValueAsString(setting)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteSetting() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/settings/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}
