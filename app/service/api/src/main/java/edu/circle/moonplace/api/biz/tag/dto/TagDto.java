package edu.circle.moonplace.api.biz.tag.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagDto {

    private Long id;

    private String name;

    private Instant createDt;

    private Instant updateDt;
}
