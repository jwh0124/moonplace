package edu.circle.moonplace.api.biz.tag.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import edu.circle.moonplace.api.common.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tag")
@Getter
@Setter
@NoArgsConstructor
public class Tag extends BaseEntity {

    @Column(name = "name")
    private String name;
}
