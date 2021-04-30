package edu.circle.moonplace.api.biz.setting.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingDto {
    private Long id;

    private String code;

    private String value;

    private Instant createDt;

    private Instant updateDt;
}
