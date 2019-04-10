package tech.ychen.blog.service;

import tech.ychen.blog.entiy.Tag;

import java.util.List;

public interface TagService {

    int insertTag(Tag tag);

    int updateTag(Tag tag);

    List<Tag> list();

    Tag selectTagByName(String tagName);

    Tag selectTagById(Integer tagId);

}
