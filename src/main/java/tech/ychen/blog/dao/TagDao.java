package tech.ychen.blog.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import tech.ychen.blog.entiy.Tag;
import tech.ychen.blog.provider.TagProvider;
import tech.ychen.blog.provider.UserProvider;

import java.util.List;

/**
 * @author leon
 * @date 2019-04-09 09:45
 */
@Mapper
@Component
public interface TagDao {

    @Insert("INSERT INTO `tag`(`tag_name`, `number`, `is_effective`, `gmt_create`, `gmt_modified`) " +
            "VALUES (#{tagName}, #{number}, #{effective}, #{gmtCreate}, #{gmtModified});")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertTag(Tag tag);


    @UpdateProvider(type = TagProvider.class,method = "updateTag")
    int updateTag(Tag tag);

    @Select("SELECT * FROM `tag`")
    @Results({
            @Result(property = "tagName", column = "tag_name"),
            @Result(property = "number", column = "number"),
            @Result(property = "effective", column = "is_effective"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified")
    })
    List<Tag> listTag();

    @Select("SELECT * FROM `tag` WHERE `tag_name` = #{tagName} ")
    @Results({
            @Result(property = "tagName", column = "tag_name"),
            @Result(property = "number", column = "number"),
            @Result(property = "effective", column = "is_effective"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified")
    })
    Tag selectTagByName(String tagName);


    @Select("SELECT * FROM `tag` WHERE `id` = #{tagId} ")
    @Results({
            @Result(property = "tagName", column = "tag_name"),
            @Result(property = "number", column = "number"),
            @Result(property = "effective", column = "is_effective"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified")
    })
    Tag selectTagById(Integer tagId);

}
