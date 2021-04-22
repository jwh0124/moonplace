package edu.circle.moonplace.api.biz.tag.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.circle.moonplace.api.biz.tag.dto.TagDto;
import edu.circle.moonplace.api.biz.tag.service.TagService;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagService tagService;

    ModelMapper modelMapper;

    @GetMapping
    public List<TagDto> getTagList() {
        return tagService.retrieveTagList().stream().map(tag -> modelMapper.map(tag, TagDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{tagId}")
    public TagDto getTag(@PathVariable Long tagId) {
        return modelMapper.map(tagService.retrieveTag(tagId), TagDto.class);
    }
}
