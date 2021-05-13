package edu.circle.moonplace.api.biz.reply.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import edu.circle.moonplace.api.biz.reply.service.ReplyService;

@WebMvcTest(ReplyController.class)
public class ReplyControllerTest {
    
    @MockBean
    ReplyService replyService;

    @MockBean
    ModelMapper modelMapper;

    @Test
    public void getReplyList() {
        assertTrue(true);
    }

    @Test
    public void getReply() {
        assertTrue(true);
    }

    @Test
    public void postReply() {
        assertTrue(true);
    }

    @Test
    public void putReply() {
        assertTrue(true);
    }

    @Test
    public void deleteReply() {
        assertTrue(true);
    }
}
