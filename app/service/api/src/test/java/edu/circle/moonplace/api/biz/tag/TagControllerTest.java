package edu.circle.moonplace.api.biz.tag;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import edu.circle.moonplace.api.biz.tag.controller.TagController;
import edu.circle.moonplace.api.biz.tag.dto.TagDto;
import edu.circle.moonplace.api.biz.tag.service.TagService;

@WebMvcTest(TagController.class)
public class TagControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TagService tagService;

    @MockBean
    ModelMapper modelMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getTagList() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/tags")).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getTag() throws Exception {
        final TagDto tag = TagDto.builder().name("test").build();

        this.mockMvc.perform(MockMvcRequestBuilders.get("/tags", tag.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postTag() throws Exception {
        final String jsonTag = objectMapper.writeValueAsString(TagDto.builder().name("camp").build());

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/tags").content(jsonTag).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void putTag() throws Exception {
        TagDto tag = TagDto.builder().name("camp").build();
        String jsonTag = objectMapper.writeValueAsString(tag);

        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/tags", tag.getId()).content(jsonTag)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteTag() throws Exception {
        TagDto tag = TagDto.builder().name("camp").build();

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/tags", tag.getId()).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}
