package edu.circle.moonplace.api.biz.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.circle.moonplace.api.biz.tag.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
