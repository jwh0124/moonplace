package edu.circle.moonplace.api.biz.tag.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import edu.circle.moonplace.api.common.base.BaseResponse;
import edu.circle.moonplace.api.common.base.response.FailureResponse;
import edu.circle.moonplace.api.common.base.response.SuccessResponse;
import edu.circle.moonplace.api.common.enums.StatusEnum;

@RestController
@RequestMapping(value = "/tags", produces = "application/json")
public class TagController {

    @Autowired
    TagService tagService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<BaseResponse> getTagList() {
        return ResponseEntity.ok(new SuccessResponse<>(tagService.retrieveTagList().stream()
                .map(tag -> modelMapper.map(tag, TagDto.class)).collect(Collectors.toList())));
    }

    @GetMapping(path = "/{tagId}")
    public ResponseEntity<BaseResponse> getTag(@PathVariable Long tagId) throws Exception {

        Optional<Tag> tag = tagService.retrieveTag(tagId);
        if (!tag.isPresent()) {
            return ResponseEntity.ok(new FailureResponse(StatusEnum.NOT_FOUND, "Not found tag"));
        }

        return ResponseEntity.ok(new SuccessResponse<>(modelMapper.map(tag.get(), TagDto.class)));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> postTag(@RequestBody TagDto tag) {
        return ResponseEntity.ok(new SuccessResponse<>(tagService.insertTag(modelMapper.map(tag, Tag.class))));
    }

    @PutMapping(path = "/{tagId}")
    public ResponseEntity<BaseResponse> putTag(@PathVariable Long tagId, @RequestBody TagDto tag) {
        tagService.updateTag(tagId, modelMapper.map(tag, Tag.class));
        return ResponseEntity.ok(new SuccessResponse<>(tagId));
    }

    @DeleteMapping(path = "/{tagId}")
    public ResponseEntity<BaseResponse> deleteTag(@PathVariable Long tagId) {
        tagService.deleteTag(tagId);
        return ResponseEntity.ok(new SuccessResponse<>(tagId));
    }
}
