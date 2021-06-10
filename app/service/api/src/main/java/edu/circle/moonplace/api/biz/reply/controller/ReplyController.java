package edu.circle.moonplace.api.biz.reply.controller;

import java.util.List;
import java.util.NoSuchElementException;
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

import edu.circle.moonplace.api.biz.reply.domain.Reply;
import edu.circle.moonplace.api.biz.reply.dto.ReplyDto;
import edu.circle.moonplace.api.biz.reply.service.ReplyService;
import edu.circle.moonplace.api.common.ApiResponse;
import edu.circle.moonplace.api.common.enums.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/replies", produces = "application/json")
@Api(tags = "Reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ApiOperation(value = "댓글 리스트 조회")
    public ResponseEntity<ApiResponse<List<ReplyDto>>> getReplyList() {
        try {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.OK, replyService.retrieveReplyList().stream()
                    .map(reply -> modelMapper.map(reply, ReplyDto.class)).collect(Collectors.toList())));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "댓글 조회")
    public ResponseEntity<ApiResponse<ReplyDto>> getReply(@PathVariable Long id) throws Exception {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(StatusEnum.OK, modelMapper.map(replyService.retrieveReply(id), ReplyDto.class)));
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.NOT_FOUND, nsee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @PostMapping
    @ApiOperation(value = "댓글 등록")
    public ResponseEntity<ApiResponse<Long>> postReply(@RequestBody ReplyDto reply) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(StatusEnum.OK, replyService.insertReply(modelMapper.map(reply, Reply.class))));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "댓글 정보 수정")
    public ResponseEntity<ApiResponse<Long>> putReply(@PathVariable Long id, @RequestBody ReplyDto reply) {
        try {
            replyService.updateReply(id, modelMapper.map(reply, Reply.class));
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.OK, id));
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.NOT_FOUND, nsee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "댓글 삭제")
    public ResponseEntity<ApiResponse<Long>> deleteReply(@PathVariable Long id) {
        try {
            replyService.deleteReply(id);
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.OK, id));
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.ok(new ApiResponse<>(StatusEnum.NOT_FOUND, nsee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(StatusEnum.INTERNAL_SERER_ERROR, e.getMessage()));
        }
    }

}
