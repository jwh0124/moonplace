package edu.circle.moonplace.api.biz.reply.controller;

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

import edu.circle.moonplace.api.biz.reply.domain.Reply;
import edu.circle.moonplace.api.biz.reply.dto.ReplyDto;
import edu.circle.moonplace.api.biz.reply.service.ReplyService;

@RestController
@RequestMapping(value = "/replies", produces = "application/json")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ReplyDto> getReplyList() {
        return replyService.retrieveReplyList().stream().map(reply -> modelMapper.map(reply, ReplyDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{replyId}")
    public ReplyDto getReply(@PathVariable Long replyId) throws Exception {
        Optional<Reply> reply = replyService.retrieveReply(replyId);
        if (!reply.isPresent()) {
            throw new Exception("No such data");
        }
        return modelMapper.map(reply.get(), ReplyDto.class);
    }

    @PostMapping
    public Long postReply(@RequestBody ReplyDto reply) {
        return replyService.insertReply(modelMapper.map(reply, Reply.class));
    }

    @PutMapping(path = "/{replyId}")
    public void putReply(@PathVariable Long replyId, @RequestBody ReplyDto reply) {
        replyService.updateReply(replyId, modelMapper.map(reply, Reply.class));
    }

    @DeleteMapping(path = "/{replyId}")
    public void deleteReply(@PathVariable Long replyId) {
        replyService.deleteReply(replyId);
    }

}
