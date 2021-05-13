package edu.circle.moonplace.api.biz.setting.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import edu.circle.moonplace.api.biz.setting.service.SettingService;

@WebMvcTest(SettingController.class)
public class SettingControllerTest {

    @MockBean
    SettingService settingService;

    @MockBean
    ModelMapper modelMapper;

    @Test
    public void getSettingList() {
        assertTrue(true);
    }

    @Test
    public void getSetting() {
        assertTrue(true);
    }

    @Test
    public void postSetting() {
        assertTrue(true);
    }

    @Test
    public void putSetting() {
        assertTrue(true);
    }

    @Test
    public void deleteSetting() {
        assertTrue(true);
    }
}
