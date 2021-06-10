package edu.circle.moonplace.api.biz.reply.service;

import java.util.List;

import edu.circle.moonplace.api.biz.reply.domain.Reply;

public interface ReplyService {
    List<Reply> retrieveReplyList();

    Reply retrieveReply(Long id);

    Long insertReply(Reply reply);

    void updateReply(Long id, Reply reply);

    void deleteReply(Long id);
}
