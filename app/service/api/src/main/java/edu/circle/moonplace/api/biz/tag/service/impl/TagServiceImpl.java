package edu.circle.moonplace.api.biz.tag.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.circle.moonplace.api.biz.tag.domain.Tag;
import edu.circle.moonplace.api.biz.tag.repository.TagRepository;
import edu.circle.moonplace.api.biz.tag.service.TagService;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> retrieveTagList() {
        return tagRepository.findAll();
    }

    @Override
    public Optional<Tag> retrieveTag(Long tagId) {
        return tagRepository.findById(tagId);
    }

    @Override
    public Long insertTag(Tag tag) {
        Tag saveTag = tagRepository.save(tag);
        return saveTag.getId();
    }

    @Override
    public void updateTag(Long tagId, Tag tag) {
        if (tagRepository.existsById(tagId)) {
            tag.setId(tagId);
            tagRepository.save(tag);
        }
    }

    @Override
    public void deleteTag(Long tagId) {
        if (tagRepository.existsById(tagId)) {
            tagRepository.deleteById(tagId);
        }
    }

}
