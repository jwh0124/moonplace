package edu.circle.moonplace.api.biz.reply.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import edu.circle.moonplace.api.biz.reply.dto.ReplyDto;
import edu.circle.moonplace.api.biz.reply.service.ReplyService;

@ActiveProfiles("test")
@WebMvcTest(ReplyController.class)
public class ReplyControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReplyService replyService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void getReplyList() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/replies").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getReply() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/replies/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postReply() throws Exception {
        ReplyDto reply = ReplyDto.builder().text("test").build();

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/replies").content(objectMapper.writeValueAsString(reply))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void putReply() throws Exception {
        ReplyDto reply = ReplyDto.builder().text("test").build();

        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/replies/{id}", 1L).content(objectMapper.writeValueAsString(reply))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteReply() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/replies/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}
