package edu.circle.moonplace.api.biz.tag.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.circle.moonplace.api.biz.tag.entity.Tag;
import edu.circle.moonplace.api.biz.tag.repository.TagRepository;
import edu.circle.moonplace.api.biz.tag.service.TagService;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepository tagRepository;

    @Override
    public List<Tag> retrieveTagList() {
        return tagRepository.findAll();
    }

    @Override
    public Tag retrieveTag(Long tagId) {
        return tagRepository.findById(tagId).orElse(null);
    }

}
