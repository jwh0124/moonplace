package edu.circle.moonplace.api.biz.tag.controller;

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

import edu.circle.moonplace.api.biz.tag.dto.TagDto;
import edu.circle.moonplace.api.biz.tag.service.TagService;

@ActiveProfiles("test")
@WebMvcTest(TagController.class)
public class TagControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private TagService tagService;

        @MockBean
        private ModelMapper modelMapper;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        public void getTagList() throws Exception {
                this.mockMvc.perform(MockMvcRequestBuilders.get("/tags").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        public void getTag() throws Exception {
                this.mockMvc.perform(MockMvcRequestBuilders.get("/tags/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        public void postTag() throws Exception {
                TagDto tag = TagDto.builder().name("camp").build();

                this.mockMvc.perform(MockMvcRequestBuilders.post("/tags").content(objectMapper.writeValueAsString(tag))
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
        }

        @Test
        public void putTag() throws Exception {
                TagDto tag = TagDto.builder().name("camp").build();

                this.mockMvc.perform(MockMvcRequestBuilders.put("/tags/{id}", 1L)
                                .content(objectMapper.writeValueAsString(tag)).contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        public void deleteTag() throws Exception {
                this.mockMvc.perform(MockMvcRequestBuilders.delete("/tags/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
        }
}
