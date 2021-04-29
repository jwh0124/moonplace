package edu.circle.moonplace.api.biz.tag.service;

import java.util.List;
import java.util.Optional;

import edu.circle.moonplace.api.biz.tag.domain.Tag;

public interface TagService {
    List<Tag> retrieveTagList();

    Optional<Tag> retrieveTag(Long tagId);

    void insertTag(Tag tag);

    void updateTag(Long tagId, Tag tag);

    void deleteTag(Long tagId);
}
