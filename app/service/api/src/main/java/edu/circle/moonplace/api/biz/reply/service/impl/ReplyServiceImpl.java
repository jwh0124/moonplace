package edu.circle.moonplace.api.biz.reply.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.circle.moonplace.api.biz.reply.domain.Reply;
import edu.circle.moonplace.api.biz.reply.repository.ReplyRepository;
import edu.circle.moonplace.api.biz.reply.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    ReplyRepository replyRepository;

    @Override
    public List<Reply> retrieveReplyList() {
        return replyRepository.findAll();
    }

    @Override
    public Reply retrieveReply(Long id) {
        return replyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("not found replyId : " + id));
    }

    @Override
    public Long insertReply(Reply reply) {
        replyRepository.save(reply);
        return reply.getId();
    }

    @Override
    public void updateReply(Long id, Reply reply) {
        replyRepository.findById(id).ifPresentOrElse(getReply -> {
            reply.setId(id);
            replyRepository.save(reply);
        }, () -> {
            throw new NoSuchElementException("not found replyId : " + id);
        });
    }

    @Override
    public void deleteReply(Long id) {
        replyRepository.findById(id).ifPresentOrElse(getReply -> replyRepository.deleteById(id), () -> {
            throw new NoSuchElementException("not found replyId : " + id);
        });
    }

}
