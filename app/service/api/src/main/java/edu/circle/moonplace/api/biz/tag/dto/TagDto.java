package edu.circle.moonplace.api.biz.tag.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TagDto {

    private Long id;

    private String name;

    private LocalDateTime createDt;

    private LocalDateTime updateDt;
}
