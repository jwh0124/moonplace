package edu.circle.moonplace.api.biz.setting.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import edu.circle.moonplace.api.common.ApiResponse;
import edu.circle.moonplace.api.common.enums.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/settings", produces = "application/json")
@Api(tags = "Setting")
public class SettingController {

    @Autowired
    private SettingService settingService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ApiOperation(value = "설정 리스트 조회")
    public ResponseEntity<ApiResponse<List<SettingDto>>> getSettingList() {
        try {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.OK, settingService.retrieveSettingList().stream()
                    .map(setting -> modelMapper.map(setting, SettingDto.class)).collect(Collectors.toList())));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "설정 조회")
    public ResponseEntity<ApiResponse<SettingDto>> getSetting(@PathVariable Long id) throws Exception {
        try {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.OK,
                    modelMapper.map(settingService.retrieveSetting(id), SettingDto.class)));
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.NOT_FOUND, nsee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @PostMapping
    @ApiOperation(value = "설정 등록")
    public ResponseEntity<ApiResponse<Long>> postSetting(@RequestBody SettingDto setting) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.OK,
                    settingService.insertSetting(modelMapper.map(setting, Setting.class))));
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.NOT_FOUND, nsee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "설정 정보 수정")
    public ResponseEntity<ApiResponse<Long>> putSetting(@PathVariable Long id, @RequestBody SettingDto setting) {
        try {
            settingService.updateSetting(id, modelMapper.map(setting, Setting.class));
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.OK, id));
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.NOT_FOUND, nsee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "설정 삭제")
    public ResponseEntity<ApiResponse<Long>> deleteSetting(@PathVariable Long id) {
        try {
            settingService.deleteSetting(id);
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.OK, id));
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.NOT_FOUND, nsee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }

    }
}
