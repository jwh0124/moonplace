package edu.circle.moonplace.api.biz.tag.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.circle.moonplace.api.biz.tag.domain.Tag;
import edu.circle.moonplace.api.biz.tag.dto.TagDto;
import edu.circle.moonplace.api.biz.tag.service.TagService;

@RestController
@RequestMapping(value = "/tags", produces = "application/json")
public class TagController {

    @Autowired
    TagService tagService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<TagDto> getTagList() {
        return tagService.retrieveTagList().stream().map(tag -> modelMapper.map(tag, TagDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{tagId}")
    public TagDto getTag(@PathVariable Long tagId) throws Exception {

        Optional<Tag> tag = tagService.retrieveTag(tagId);
        if (!tag.isPresent()) {
            throw new Exception("no such data");
        }

        return modelMapper.map(tag.get(), TagDto.class);
    }

    @PostMapping
    public void postTag(@RequestBody TagDto tag) {
        tagService.insertTag(modelMapper.map(tag, Tag.class));
    }

    @PutMapping(path = "/{tagId}")
    public void putTag(@PathVariable Long tagId, @RequestBody TagDto tag) {
        tagService.updateTag(tagId, modelMapper.map(tag, Tag.class));
    }

    @DeleteMapping(path = "/{tagId}")
    public void deleteTag(@PathVariable Long tagId) {
        tagService.deleteTag(tagId);
    }
}
