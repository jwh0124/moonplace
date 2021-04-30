package edu.circle.moonplace.api.biz.area.dto;

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
public class AreaDto {
    private Long id;

    private String name;

    private Instant createDt;

    private Instant updateDt;
}
