package edu.circle.moonplace.api.biz.setting.service;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import edu.circle.moonplace.api.biz.setting.domain.Setting;
import edu.circle.moonplace.api.biz.setting.repository.SettingRepository;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class SettingServiceTest {

    @Autowired
    private SettingService settingService;

    @Autowired
    private SettingRepository settingRepository;

    private Setting saveSetting;

    @BeforeEach
    public void beforeEach() {
        saveSetting = settingRepository.save(Setting.builder().code("code").value("value").build());
    }

    @AfterEach
    public void afterEach() {
        settingRepository.deleteAll();
    }

    @Test
    public void retrieveSettingList() {
        // given - beforeEach

        // when
        List<Setting> settingList = settingService.retrieveSettingList();

        // then
        Assertions.assertThat(settingList.size()).isEqualTo(1);
    }

    @Test
    public void retrieveSetting() {
        // given - beforeEach

        // when
        Setting setting = settingService.retrieveSetting(saveSetting.getId());

        // then
        Assertions.assertThat(setting).isNotNull();
    }

    @Test
    public void insertSetting() {
        // given
        Setting setting = Setting.builder().code("code").value("value").build();

        // when
        Long insertSettingId = settingService.insertSetting(setting);
        Optional<Setting> findSetting = settingRepository.findById(insertSettingId);

        // then
        Assertions.assertThat(findSetting).isNotNull();
        Assertions.assertThat(findSetting.get().getCode()).isEqualTo(setting.getCode());
        Assertions.assertThat(findSetting.get().getValue()).isEqualTo(setting.getValue());
    }

    @Test
    public void updateSetting() {
        // given - beforeEach
        Setting setting = Setting.builder().code("code").value("value").build();
        // when
        settingService.updateSetting(saveSetting.getId(), setting);
        Optional<Setting> updateSetting = settingRepository.findById(saveSetting.getId());

        // then
        Assertions.assertThat(updateSetting).isNotNull();
        Assertions.assertThat(updateSetting.get().getCode()).isEqualTo(setting.getCode());
        Assertions.assertThat(updateSetting.get().getValue()).isEqualTo(setting.getValue());
    }

    @Test
    public void deleteSetting() {
        // given - beforeEach

        // when
        settingService.deleteSetting(saveSetting.getId());
        // then
        Assertions.assertThat(settingRepository.count()).isZero();
    }
}
