package edu.circle.moonplace.api.biz.place.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import edu.circle.moonplace.api.biz.area.domain.Area;
import edu.circle.moonplace.api.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "place")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "addr")
    private String addr;

    @Column(name = "phone")
    private String phone;

    @Column(name = "rate")
    private Integer rate;

    @Column(name = "description")
    private String description;

    @Column(name = "visitDt")
    private LocalDateTime visitDt;

    @OneToOne
    @JoinColumn(name = "areaId")
    private Area area;

    // TODO : Lazy loading
    @OneToMany(mappedBy = "place", fetch = FetchType.EAGER)
    private List<PlaceImage> placeImage;
}
