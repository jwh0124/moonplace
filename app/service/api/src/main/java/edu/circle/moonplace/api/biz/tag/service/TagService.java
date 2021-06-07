package edu.circle.moonplace.api.biz.tag.service;

import java.util.List;

import edu.circle.moonplace.api.biz.tag.domain.Tag;

public interface TagService {
    List<Tag> retrieveTagList();

    Tag retrieveTag(Long tagId);

    Long insertTag(Tag tag);

    void updateTag(Long tagId, Tag tag);

    void deleteTag(Long tagId);
}
