package edu.circle.moonplace.api.biz.reply.service;

import java.util.List;
import java.util.Optional;

import edu.circle.moonplace.api.biz.reply.domain.Reply;

public interface ReplyService {
    List<Reply> retrieveReplyList();

    Optional<Reply> retrieveReply(Long replyId);

    Long insertReply(Reply reply);

    void updateReply(Long replyId, Reply reply);

    void deleteReply(Long replyId);
}