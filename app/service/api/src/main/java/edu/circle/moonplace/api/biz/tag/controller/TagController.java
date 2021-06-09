package edu.circle.moonplace.api.biz.tag.controller;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

// TODO:
// [] exception processing => ControllerAdvice
@RestController
@RequestMapping(value = "/tags", produces = "application/json")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<BaseResponse> getTagList() {
        try {
            return ResponseEntity.ok(new SuccessResponse<>(tagService.retrieveTagList().stream()
                .map(tag -> modelMapper.map(tag, TagDto.class)).collect(Collectors.toList())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new FailureResponse(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @GetMapping(path = "/{tagId}")
    public ResponseEntity<BaseResponse> getTag(@PathVariable Long tagId) {
        try {
            return ResponseEntity
                    .ok(new SuccessResponse<>(modelMapper.map(tagService.retrieveTag(tagId), TagDto.class)));
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.ok(new FailureResponse(StatusEnum.NOT_FOUND, nsee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new FailureResponse(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> postTag(@RequestBody TagDto tag) {
        try {
            return ResponseEntity.ok(new SuccessResponse<>(tagService.insertTag(modelMapper.map(tag, Tag.class))));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new FailureResponse(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @PutMapping(path = "/{tagId}")
    public ResponseEntity<BaseResponse> putTag(@PathVariable Long tagId, @RequestBody TagDto tag) {
        try {
            tagService.updateTag(tagId, modelMapper.map(tag, Tag.class));
            return ResponseEntity.ok(new SuccessResponse<>(tagId));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new FailureResponse(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @DeleteMapping(path = "/{tagId}")
    public ResponseEntity<BaseResponse> deleteTag(@PathVariable Long tagId) {
        try {
            tagService.deleteTag(tagId);
            return ResponseEntity.ok(new SuccessResponse<>(tagId));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new FailureResponse(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }
}
