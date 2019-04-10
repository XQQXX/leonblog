package tech.ychen.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ychen.blog.dao.TagDao;
import tech.ychen.blog.entiy.Tag;
import tech.ychen.blog.service.TagService;

import java.util.List;

/**
 * @author leon
 * @date 2019-04-09 10:00
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;


    /**
     * 增加tag
     * @param tag
     * @return
     */
    @Override
    public int insertTag(Tag tag) {
        return tagDao.insertTag(tag);
    }

    /**
     * 更新tag
     * @param tag
     * @return
     */
    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    /**
     * 列出所有Tag
     * @return
     */
    @Override
    public List<Tag> list() {
        return tagDao.listTag();
    }


    /**
     * 根据tag名找tag
     * @return
     */
    @Override
    public Tag selectTagByName(String tagName) {
        return tagDao.selectTagByName(tagName);
    }

    @Override
    public Tag selectTagById(Integer tagId) {
        return tagDao.selectTagById(tagId);
    }
}
