package tech.ychen.blog.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import tech.ychen.blog.entiy.User;
import tech.ychen.blog.provider.UserProvider;

@Mapper
@Component
public interface UserDao {



    /**
     * 保存用户
     * @param user
     * @return
     */
    @Insert("INSERT INTO `user`( `user_name`, `user_pwd`, `user_email`, `user_headimg`, `user_ip`, `is_effective`, `gmt_create`, `gmt_modified`,`code`) " +
            "VALUES " +
            "(#{userName}, #{userPwd},#{userEmail},#{userHeadimg},#{userIp},#{effective},#{gmtCreate},#{gmtModified},#{code});")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(User user);


    /**
     * 根据username查找用户
     * @param username
     * @return
     */
    @Select({"SELECT * FROM `user` WHERE user_name = #{username}"})
    @Results({
            @Result(property="userName",column ="user_name"),
            @Result(property="userPwd",column ="user_pwd"),
            @Result(property="userEmail",column ="user_email"),
            @Result(property ="userHeadimg",column ="user_headimg"),
            @Result(property ="userIp",column ="user_ip"),
            @Result(property ="effective",column ="is_effective"),
            @Result(property ="gmtCreate",column ="gmt_create"),
            @Result(property ="gmtModified",column ="gmt_modified"),
            @Result(property ="code",column ="code")
    })
    User findByUsername(@Param("username") String username);


    /**
     * 根据用户名和密码查找用户
     * @param username
     * @param password
     * @return
     */
    @Select("SELECT * FROM `user` WHERE `user_name` = #{username} AND `user_pwd` = #{password}")
    @Results({
            @Result(property="userName",column ="user_name"),
            @Result(property="userPwd",column ="user_pwd"),
            @Result(property="userEmail",column ="user_email"),
            @Result(property ="userHeadimg",column ="user_headimg"),
            @Result(property ="userIp",column ="user_ip"),
            @Result(property ="effective",column ="is_effective"),
            @Result(property ="gmtCreate",column ="gmt_create"),
            @Result(property ="gmtModified",column ="gmt_modified"),
            @Result(property ="code",column ="code")
    })
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("SELECT * FROM `user` WHERE `id` = #{id}")
    @Results({
            @Result(property="userName",column ="user_name"),
            @Result(property="userPwd",column ="user_pwd"),
            @Result(property="userEmail",column ="user_email"),
            @Result(property ="userHeadimg",column ="user_headimg"),
            @Result(property ="userIp",column ="user_ip"),
            @Result(property ="effective",column ="is_effective"),
            @Result(property ="gmtCreate",column ="gmt_create"),
            @Result(property ="gmtModified",column ="gmt_modified"),
            @Result(property ="code",column ="code")
    })@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    User findById(@Param("id") Integer id);

    @UpdateProvider(type = UserProvider.class,method = "updateUser")
    int updateEffetive(User user);

    @UpdateProvider(type = UserProvider.class,method = "updateUser")
    int updatePassword(User user);

    @UpdateProvider(type = UserProvider.class,method = "updateUser")
    int updateEmail(User user);

}
