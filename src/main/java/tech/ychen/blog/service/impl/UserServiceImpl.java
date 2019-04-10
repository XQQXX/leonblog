package tech.ychen.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ychen.blog.dao.UserDao;
import tech.ychen.blog.entiy.User;
import tech.ychen.blog.service.UserService;

/**
 * @author leon
 * @date 2019-04-05 15:43
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 保存user
     * @param user
     * @return
     */
    @Override
    public int sava(User user) {

        return userDao.insert(user);
    }

    /**
     * 查找该用户名是否已经存在
     * @param username
     * @return
     */
    @Override
    public boolean findByUsername(String username) {

        User user =  userDao.findByUsername(username);

        if(user == null){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 验证用户是否存在
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean findByUsernameAndPassword(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username,password);
        if(user == null) {
            return false;
        }else{

            return true;
        }
    }

    /**
     * 根据用户名返回用户
     * @param username
     * @return
     */
    @Override
    public User findByname(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * 更新激活状态
     * @param user
     * @return
     */
    @Override
    public int updateEffective(User user) {
        return userDao.updateEffetive(user);
    }

    /**
     * 更新密码
     * @param user
     * @return
     */
    @Override
    public int updatePassword(User user) {
        return userDao.updatePassword(user);
    }

    /**
     * 更新头像
     * @param user
     * @return
     */
    @Override
    public int updateUserEmail(User user) {
        return userDao.updateEmail(user);
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }
}
