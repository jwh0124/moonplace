package edu.circle.moonplace.api.biz.setting.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.circle.moonplace.api.biz.setting.domain.Setting;
import edu.circle.moonplace.api.biz.setting.repository.SettingRepository;
import edu.circle.moonplace.api.biz.setting.service.SettingService;

@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    SettingRepository settingRepository;

    @Override
    public List<Setting> retrieveSettingList() {
        return settingRepository.findAll();
    }

    @Override
    public Optional<Setting> retrieveSetting(Long settingId) {
        return settingRepository.findById(settingId);
    }

    @Override
    public Long insertSetting(Setting setting) {
        settingRepository.save(setting);
        return setting.getId();
    }

    @Override
    public void updateSetting(Long settingId, Setting setting) {
        if (settingRepository.existsById(settingId)) {
            setting.setId(settingId);
            settingRepository.save(setting);
        }
    }

    @Override
    public void deleteSetting(Long settingId) {
        if (settingRepository.existsById(settingId)) {
            settingRepository.deleteById(settingId);
        }
    }

}
