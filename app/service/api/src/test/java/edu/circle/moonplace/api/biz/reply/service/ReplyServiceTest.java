package edu.circle.moonplace.api.biz.reply.service;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import edu.circle.moonplace.api.biz.reply.domain.Reply;
import edu.circle.moonplace.api.biz.reply.repository.ReplyRepository;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class ReplyServiceTest {
    
    @Autowired
    private ReplyService replyService;

    @Autowired
    private ReplyRepository replyRepository;

    private Reply saveReply;

    @BeforeEach
    public void beforeEach(){
        saveReply = replyRepository.save(Reply.builder().text("text").build());
    }

    @AfterEach
    public void afterEach() {
        replyRepository.deleteAll();
    }
    
    @Test
    public void retrieveReplyList(){
        // given - beforeEach

        // when
        List<Reply> replyList = replyService.retrieveReplyList();

        // then
        Assertions.assertThat(replyList.size()).isEqualTo(1);
    }

    @Test
    public void retrieveReply() {
        // given - beforeEach

        // when
        Optional<Reply> reply = replyService.retrieveReply(saveReply.getId());

        // then
        Assertions.assertThat(reply).isNotNull();
    }

    @Test
    public void insertReply() {
        // given
        Reply reply = Reply.builder().text("text").build();
        // when
        Long insertReply = replyService.insertReply(reply);
        // then
        Optional<Reply> findReply = replyRepository.findById(insertReply);
        Assertions.assertThat(findReply).isNotNull();
        Assertions.assertThat(reply.getText()).isEqualTo(findReply.get().getText());
    }

    @Test
    public void updateReply() {
        // given
        Reply reply = Reply.builder().text("text1").build();
        // when
        replyService.updateReply(saveReply.getId(), reply);
        Optional<Reply> findReply = replyRepository.findById(saveReply.getId());
        // then
        Assertions.assertThat(findReply).isNotNull();
        Assertions.assertThat(findReply.get().getText()).isEqualTo(reply.getText());
    }

    @Test
    public void deleteReply(){

        // given - beforeEach

        // when
        replyService.deleteReply(saveReply.getId());

        // then
        Assertions.assertThat(replyRepository.count()).isZero();
    }
}
