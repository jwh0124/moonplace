package edu.circle.moonplace.api.biz.tag.service;

import java.util.List;

import edu.circle.moonplace.api.biz.tag.entity.Tag;

public interface TagService {
    List<Tag> retrieveTagList();

    Tag retrieveTag(Long tagId);
}
