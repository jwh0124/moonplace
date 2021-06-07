package edu.circle.moonplace.api.biz.tag.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

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
    public Tag retrieveTag(Long tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new NoSuchElementException("not found tagId : " + tagId));
    }

    // TODO:
    // [] add validation tag name
    @Override
    public Long insertTag(Tag tag) {

        tagRepository.save(tag);
        return tag.getId();
    }

    // TODO:
    // [] existsById -> findById
    @Override
    public void updateTag(Long tagId, Tag tag) {
        if (tagRepository.existsById(tagId)) {
            tag.setId(tagId);
            tagRepository.save(tag);
        }
    }

    // TODO:
    // [] existsById -> findById
    @Override
    public void deleteTag(Long tagId) {
        if (tagRepository.existsById(tagId)) {
            tagRepository.deleteById(tagId);
        }
    }

}
