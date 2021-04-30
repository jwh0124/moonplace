package edu.circle.moonplace.api.biz.setting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.circle.moonplace.api.biz.setting.domain.Setting;

public interface SettingRepository extends JpaRepository<Setting, Long> {
    Optional<Setting> findByCode(String code);
}
