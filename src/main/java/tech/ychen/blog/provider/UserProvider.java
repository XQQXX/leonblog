package tech.ychen.blog.provider;

import org.apache.ibatis.jdbc.SQL;
import tech.ychen.blog.entiy.User;

/**
 * @author leon
 * @date 2019-04-07 16:56
 * user 构建动态sql
 */
public class UserProvider {

    public String updateUser(final User user){

        return new SQL(){{

            UPDATE("user");

            if(user.getUserName() != null){

                SET("user_name=#{userName}");
            }
            if(user.getUserPwd() != null){

                SET("user_pwd=#{userPwd}");
            }
            if(user.getUserEmail() != null){

                SET("user_email=#{userEmail}");
            }
            if(user.getUserHeadimg()!=null){

                SET("user_headimg=#{userHeadimg}");
            }
            if(user.getUserIp() != null){

                SET("user_ip=#{userIp}");
            }
            if((Integer)user.getEffective() != null){
                SET("is_effective=#{effective}");
            }
            if(user.getGmtCreate() != null){
                SET("gmt_create=#{gmtCreate}");
            }
            if(user.getGmtModified() != null){
                SET("gmt_modified=#{gmtModified}");
            }
            if((Integer)user.getCode() !=null){
                SET("code=#{code}");
            }


            WHERE("id=#{id}");


        }
        }.toString();



    }


}
