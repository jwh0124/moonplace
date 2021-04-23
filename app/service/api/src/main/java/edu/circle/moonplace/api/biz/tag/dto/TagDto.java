package edu.circle.moonplace.api.biz.tag.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagDto {

    private Long id;

    private String name;

    private LocalDateTime createDt;

    private LocalDateTime updateDt;
}
