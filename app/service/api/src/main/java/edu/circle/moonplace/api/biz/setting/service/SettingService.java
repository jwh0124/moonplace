package edu.circle.moonplace.api.biz.setting.service;

import java.util.List;

import edu.circle.moonplace.api.biz.setting.domain.Setting;

public interface SettingService {
    List<Setting> retrieveSettingList();

    Setting retrieveSetting(Long id);

    Long insertSetting(Setting setting);

    void updateSetting(Long id, Setting setting);

    void deleteSetting(Long id);
}
