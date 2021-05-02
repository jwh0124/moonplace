package edu.circle.moonplace.api.biz.tag.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.circle.moonplace.api.biz.tag.domain.Tag;

@SpringBootTest
@Transactional
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @AfterEach
    public void afterEach() {
        // to-do

    }

    @Test
    public void retrieveTagList() {
        // given
        
        // when

        // then
        assertTrue(true);
    }

    @Test
    public void retireveArea() {
        // given

        // when

        // then
        assertTrue(true);
    }

    @Test
    public void insertArea() {
        // given
        Tag tag = Tag.builder().name("camp").build();
        // when
        Long insertTagId = tagService.insertTag(tag);
        // then
        Tag findTag = tagService.retrieveTag(insertTagId).get();
        Assertions.assertThat(tag.getName()).isEqualTo(findTag.getName());
    }

    @Test
    public void updateArea() {
        // given

        // when

        // then
        assertTrue(true);
    }

    @Test
    public void deleteArea() {
        // given

        // when

        // then
        assertTrue(true);
    }
}
