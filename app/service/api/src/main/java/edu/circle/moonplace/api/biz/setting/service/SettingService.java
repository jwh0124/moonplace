package edu.circle.moonplace.api.biz.setting.service;

import java.util.List;
import java.util.Optional;

import edu.circle.moonplace.api.biz.setting.domain.Setting;

public interface SettingService {
    List<Setting> retrieveSettingList();

    Optional<Setting> retrieveSetting(Long settingId);

    Long insertSetting(Setting setting);

    void updateSetting(Long settingId, Setting setting);

    void deleteSetting(Long settingId);
}
