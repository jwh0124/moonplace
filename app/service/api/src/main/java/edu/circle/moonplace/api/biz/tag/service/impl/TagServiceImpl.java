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
    public Tag retrieveTag(Long id) {
        return tagRepository.findById(id).orElseThrow(() -> new NoSuchElementException("not found tagId : " + id));
    }

    @Override
    public Long insertTag(Tag tag) {
        tagRepository.save(tag);
        return tag.getId();
    }

    @Override
    public void updateTag(Long id, Tag tag) {
        tagRepository.findById(id).ifPresentOrElse(getTag -> {
            tag.setId(id);
            tagRepository.save(tag);
        }, () -> {
            throw new NoSuchElementException("not found tagId : " + id);
        });
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.findById(id).ifPresentOrElse(tag -> tagRepository.deleteById(id), () -> {
            throw new NoSuchElementException("not found tagId : " + id);
        });
    }

}
