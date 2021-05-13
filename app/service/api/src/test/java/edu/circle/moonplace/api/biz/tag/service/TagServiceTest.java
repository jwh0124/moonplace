package edu.circle.moonplace.api.biz.tag.service;

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

import edu.circle.moonplace.api.biz.tag.domain.Tag;
import edu.circle.moonplace.api.biz.tag.repository.TagRepository;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagRepository tagRepository;

    private Tag saveTag;

    @BeforeEach
    public void beforeEach() {
        saveTag = tagRepository.save(Tag.builder().name("camp").build());
    }

    @AfterEach
    public void afterEach() {
        tagRepository.deleteAll();
    }

    @Test
    public void retrieveTagList() {
        // given - beforeEach

        // when
        List<Tag> tagList = tagService.retrieveTagList();

        // then
        Assertions.assertThat(tagList.size()).isEqualTo(1);
    }

    @Test
    public void retireveArea() {
        // given - beforeEach

        // when
        Optional<Tag> tag = tagService.retrieveTag(saveTag.getId());

        // then
        Assertions.assertThat(tag).isNotNull();
    }

    @Test
    public void insertArea() {
        // given
        Tag tag = Tag.builder().name("camp").build();

        // when
        Long insertTagId = tagService.insertTag(tag);

        // then
        Optional<Tag> findTag = tagRepository.findById(insertTagId);
        Assertions.assertThat(findTag).isNotNull();
        Assertions.assertThat(tag.getName()).isEqualTo(findTag.get().getName());
    }

    @Test
    public void updateArea() {
        // given
        Tag tag = Tag.builder().name("eat").build();

        // when
        tagService.updateTag(saveTag.getId(), tag);
        Optional<Tag> updateTag = tagRepository.findById(saveTag.getId());

        // then
        Assertions.assertThat(updateTag).isNotNull();
        Assertions.assertThat(updateTag.get().getName()).isEqualTo(tag.getName());
    }

    @Test
    public void deleteArea() {
        // given - beforeEach

        // when
        tagService.deleteTag(saveTag.getId());

        // then
        Assertions.assertThat(tagRepository.count()).isZero();
    }
}
