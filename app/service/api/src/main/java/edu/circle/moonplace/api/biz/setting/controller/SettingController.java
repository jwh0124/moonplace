package edu.circle.moonplace.api.biz.setting.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.circle.moonplace.api.biz.setting.domain.Setting;
import edu.circle.moonplace.api.biz.setting.dto.SettingDto;
import edu.circle.moonplace.api.biz.setting.service.SettingService;

@RestController
@RequestMapping(value = "/settings", produces = "application/json")
public class SettingController {

    @Autowired
    SettingService settingService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<SettingDto> getSettingList() {
        return settingService.retrieveSettingList().stream().map(setting -> modelMapper.map(setting, SettingDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public SettingDto getSetting(@PathVariable Long id) throws Exception {
        Optional<Setting> setting = settingService.retrieveSetting(id);
        if (!setting.isPresent()) {
            throw new Exception("No Such Data");
        }
        return modelMapper.map(setting.get(), SettingDto.class);
    }

    @PostMapping
    public Long postSetting(@RequestBody SettingDto setting) {
        return settingService.insertSetting(modelMapper.map(setting, Setting.class));
    }

    @PutMapping(path = "/{id}")
    public void putSetting(@PathVariable Long id, @RequestBody SettingDto setting) {
        settingService.updateSetting(id, modelMapper.map(setting, Setting.class));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteSetting(@PathVariable Long id) {
        settingService.deleteSetting(id);
    }
}
