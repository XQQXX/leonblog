package tech.ychen.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.ychen.blog.dto.AdminUserDto;
import tech.ychen.blog.entiy.JsonData;
import tech.ychen.blog.entiy.User;
import tech.ychen.blog.intercepter.AdminLoginIntercepter;
import tech.ychen.blog.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author leon
 * @date 2019-04-08 12:22
 */
@SuppressWarnings("unused")
@Api(tags = "管理员模块")
@RestController
@RequestMapping("admin/api/v1")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminUserDto adminUserDto;


    /**
     * 后台管理员登录
     * @param adminUserDto
     * @return
     */
    @ApiOperation("后台管理员登录")
    @PostMapping("/login")
    public JsonData adminLogin(@RequestBody AdminUserDto adminUserDto,HttpServletResponse response){

        if(adminUserDto.getName().equals("leonadmin") && adminUserDto.getPassword().equals("nb")){
            Cookie cookie = new Cookie(AdminLoginIntercepter.SESSION_KEY,adminUserDto.toString());
            response.addCookie(cookie);
            return JsonData.buildSuccess(1,adminUserDto,"管理员登录成功");
        }

        return JsonData.buildError(0,null,"管理员登录失败");

    }


    /**
     * 取消用户权限
     */
    @ApiOperation("取消用户权限")
    @PutMapping("/users")
    public JsonData cancelEffective(@Param("id") Integer id){


        User user = userService.findById(id);


        user.setEffective(0);

        userService.updateEffective(user);

        String msg = "用户"+user.getId()+":"+user.getUserName()+"已取消权限";

        return JsonData.buildSuccess(1,user,msg);


    }












}
