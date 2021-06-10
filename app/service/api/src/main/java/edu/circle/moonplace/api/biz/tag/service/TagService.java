package edu.circle.moonplace.api.biz.tag.service;

import java.util.List;

import edu.circle.moonplace.api.biz.tag.domain.Tag;

public interface TagService {
    List<Tag> retrieveTagList();

    Tag retrieveTag(Long id);

    Long insertTag(Tag tag);

    void updateTag(Long id, Tag tag);

    void deleteTag(Long id);
}
