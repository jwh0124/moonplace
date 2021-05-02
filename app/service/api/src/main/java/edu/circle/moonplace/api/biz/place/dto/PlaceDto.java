package edu.circle.moonplace.api.biz.place.dto;

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
public class PlaceDto {
    private Long id;

    private String name;

    private String addr;

    private String phone;

    private Integer rate;

    private String desc;

    private Instant visitDt;

    private Instant createDt;

    private Instant updateDt;
}
