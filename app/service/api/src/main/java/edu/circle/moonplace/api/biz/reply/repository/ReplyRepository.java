package edu.circle.moonplace.api.biz.reply.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.circle.moonplace.api.biz.reply.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
