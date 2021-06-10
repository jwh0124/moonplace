package edu.circle.moonplace.api.biz.reply.service.impl;

import java.util.List;
import java.util.Optional;

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
    public Optional<Reply> retrieveReply(Long id) {
        return replyRepository.findById(id);
    }

    @Override
    public Long insertReply(Reply reply) {
        replyRepository.save(reply);
        return reply.getId();
    }

    @Override
    public void updateReply(Long id, Reply reply) {
        if (replyRepository.existsById(id)) {
            reply.setId(id);
            replyRepository.save(reply);
        }
    }

    @Override
    public void deleteReply(Long id) {
        replyRepository.deleteById(id);
    }

}
