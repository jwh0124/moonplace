package edu.circle.moonplace.api.biz.tag.controller;

import java.util.List;
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
import edu.circle.moonplace.api.common.ApiResponse;
import edu.circle.moonplace.api.common.enums.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// TODO:
// [] exception processing => ControllerAdvice
@RestController
@RequestMapping(value = "/tags", produces = "application/json")
@Api(tags = "Tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ApiOperation(value = "태그 리스트 조회")
    public ResponseEntity<ApiResponse<List<TagDto>>> getTagList() {
        try {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.OK, tagService.retrieveTagList().stream()
                .map(tag -> modelMapper.map(tag, TagDto.class)).collect(Collectors.toList())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "태그 조회")
    public ResponseEntity<ApiResponse<TagDto>> getTag(@PathVariable Long id) {
        try {
            return ResponseEntity
                    .ok(new ApiResponse<>(modelMapper.map(tagService.retrieveTag(id), TagDto.class)));
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.NOT_FOUND, nsee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @PostMapping
    @ApiOperation(value = "태그 등록")
    public ResponseEntity<ApiResponse<Long>> postTag(@RequestBody TagDto tag) {
        try {
            return ResponseEntity
                    .ok(new ApiResponse<>(StatusEnum.OK, tagService.insertTag(modelMapper.map(tag, Tag.class))));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "태그 정보 수정")
    public ResponseEntity<ApiResponse<Long>> putTag(@PathVariable Long id, @RequestBody TagDto tag) {
        try {
            tagService.updateTag(id, modelMapper.map(tag, Tag.class));
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.OK, id));
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.NOT_FOUND, nsee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "태그 삭제")
    public ResponseEntity<ApiResponse<Long>> deleteTag(@PathVariable Long id) {
        try {
            tagService.deleteTag(id);
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.OK, id));
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.NOT_FOUND, nsee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }
}
