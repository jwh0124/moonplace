package edu.circle.moonplace.api.biz.tag.service;

import java.util.List;
import java.util.Optional;

import edu.circle.moonplace.api.biz.tag.entity.Tag;

public interface TagService {
    List<Tag> retrieveTagList();

    Optional<Tag> retrieveTag(Long id);
}
