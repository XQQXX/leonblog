package tech.ychen.blog.dto;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.TagName;
import tech.ychen.blog.entiy.Tag;

import java.util.List;

/**
 * @author leon
 * @date 2019-04-09 10:08
 */
public class TagDto {
    private String TagName;


    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }


}
