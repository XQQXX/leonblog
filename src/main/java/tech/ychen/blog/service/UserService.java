package tech.ychen.blog.service;

import tech.ychen.blog.entiy.User;

public interface UserService {



    boolean findByUsername(String username);

    User findByname(String username);

    User findById(Integer id);

    boolean findByUsernameAndPassword(String username,String password);

    int sava(User user);

    int updateEffective(User user);

    int updatePassword(User user);

    int updateUserEmail(User user);


}
