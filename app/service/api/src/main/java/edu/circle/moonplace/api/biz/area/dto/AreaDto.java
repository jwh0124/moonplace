package edu.circle.moonplace.api.biz.area.dto;

import java.time.LocalDateTime;

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

    private LocalDateTime createDt;

    private LocalDateTime updateDt;
}
