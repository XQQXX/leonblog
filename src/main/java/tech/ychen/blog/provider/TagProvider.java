package tech.ychen.blog.provider;

import org.apache.ibatis.jdbc.SQL;
import tech.ychen.blog.entiy.Tag;

/**
 * @author leon
 * @date 2019-04-09 09:55
 */
public class TagProvider {

    public String updateTag(final Tag tag){

        return new SQL(){{

            UPDATE("tag");

            if(tag.getTagName() != null){

                SET("tag_name=#{tagName}");
            }
            if(tag.getNumber() != null){

                SET("number=#{number}");
            }
            if(tag.getEffective() != null){

                SET("is_effective=#{effective}");
            }

            if(tag.getGmtCreate() != null){
                SET("gmt_create=#{gmtCreate}");
            }
            if(tag.getGmtModified() != null){
                SET("gmt_modified=#{gmtModified}");
            }

            WHERE("id=#{id}");


        }
        }.toString();



    }












}
