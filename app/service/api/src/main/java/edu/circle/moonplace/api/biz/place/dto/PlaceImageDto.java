package edu.circle.moonplace.api.biz.place.dto;

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
public class PlaceImageDto {
    private Long id;

    private String path;

    private LocalDateTime createDt;

    private LocalDateTime updateDt;
}
