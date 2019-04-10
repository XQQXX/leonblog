package tech.ychen.blog.controller;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import tech.ychen.blog.dto.UserDto;
import tech.ychen.blog.entiy.User;
import tech.ychen.blog.entiy.JsonData;
import tech.ychen.blog.service.UserService;
import tech.ychen.blog.service.impl.MailService;
import tech.ychen.blog.utils.AuthCodeUtils;
import tech.ychen.blog.utils.IpUtils;
import tech.ychen.blog.utils.JwtUtils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author leon
 * @date 2019-04-04 17:39
 */
@SuppressWarnings("unused")
@RestController
@RequestMapping("api/v1")
@Api(tags = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserDto userDto;

    /**
     * 用户注册接口
     *
     * @param user
     * @return
     */
    @ApiOperation("用户注册接口")
    @PostMapping("/users")
    public JsonData save(@RequestBody User user, HttpServletRequest request) {

        if (userService.findByUsername(user.getUserName())) {
            return JsonData.buildError(0, user, "用户名已经存在");
        } else {

            user.setEffective(0);

            user.setGmtCreate(new Date());

            int code = AuthCodeUtils.makeAuthCode();

            user.setCode(code);

            user.setUserIp(IpUtils.getIpAddr(request));

            user.setGmtModified(new Date());

            userService.sava(user);

            return JsonData.buildSuccess(1, user, "注册成功");
        }
    }


    /**
     * 用户登录接口
     *
     * @param user
     * @param response
     * @return
     */
    @ApiOperation("用户登录接口")
    @PostMapping("/users/login")
    public JsonData login(@RequestBody User user, HttpServletResponse response) {

        if (userService.findByUsernameAndPassword(user.getUserName(), user.getUserPwd())) {


            String token = JwtUtils.geneJsonWebToken(user);

            //response.addHeader("token",token);

            Cookie cookie = new Cookie("token", token);

            cookie.setDomain("localhost");
            cookie.setPath("/");

            response.addCookie(cookie);

            //response.sendRedirect("/api/v1/userinfos?token="+token);

            return JsonData.buildSuccess(1, null, "登录成功");


        }

        return JsonData.buildError(0, null, "登录失败");
    }


    /*获取用户信息*//*
    @GetMapping("/users/info")
    public JsonData getCooKie(HttpServletRequest request) {

        Map<String, String> data = new HashMap<>();

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {

                String token = c.getValue();
                Claims claims = JwtUtils.checkJWT(token);
                if (claims != null) {
                    String name = (String) claims.get("name");
                    String img = (String) claims.get("img");
                    String id = (String) claims.get("id");
                    data.put("name", name);
                    data.put("img", img);
                    data.put("id", id);
                    return JsonData.buildSuccess(1, data, "token解析成功");

                } else {
                    return JsonData.buildError(0, null, "非法token");
                }

            }
        }


        return JsonData.buildError(0, null, "未登录");
    }*/


    /**
     * 用户账号激活
     *
     * @param request
     * @return
     */
    @ApiOperation("用户账号激活接口")
    @PutMapping("/users/effective")
    public JsonData checkMail(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {

                String token = c.getValue();
                Claims claims = JwtUtils.checkJWT(token);
                if (claims != null) {
                    String name = (String) claims.get("name");

                    User user1 = userService.findByname(name);


                    if (user1.getEffective() == 0) {

                        if (!user1.getUserEmail().matches("^\\w+@(\\w+\\.)+\\w+$")) {
                            return JsonData.buildSuccess(0, null, "邮箱不合法");
                        }

                        mailService.sendEffectiveMail(user1.getUserEmail(), user1.getCode());

                        return JsonData.buildSuccess(1, null, "邮件已发送");

                    } else {

                        return JsonData.buildSuccess(1, null, "账号已经激活");
                    }


                } else {
                    return JsonData.buildError(0, null, "非法token");
                }

            }
        }

        return JsonData.buildSuccess(0, null, "未登录");
    }


    /**
     * 账号激活回调
     *
     * @param code
     * @return
     */
    @ApiOperation("用户注册确认接口")
    @GetMapping("/users/effective")
    public JsonData mailBack(@Param("code") int code, HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {

                String token = c.getValue();
                Claims claims = JwtUtils.checkJWT(token);
                if (claims != null) {
                    String name = (String) claims.get("name");
                    User user = userService.findByname(name);

                    if (user.getCode() == code) {
                        user.setEffective(1);
                        user.setGmtModified(new Date());
                        userService.updateEffective(user);
                        return JsonData.buildSuccess(1, null, "账号激活成功");
                    }


                } else {
                    return JsonData.buildError(0, null, "非法token");
                }

            }
        }

        return JsonData.buildSuccess(0, null, "未登录");
    }

    /**
     * 修改密码
     * @param userDto
     * @param request
     * @return
     */
    @ApiOperation("用户修改密码接口")
   /* @ApiImplicitParams({
            @ApiImplicitParam(name="oldPassword",value = "旧密码",required = true,paramType = "form"),
            @ApiImplicitParam(name="newPassword",value = "新密码",required = true,paramType = "form")
    }
    )*/
    @PostMapping("/users/password")
    public JsonData changePassword(@RequestBody UserDto userDto, HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {

                String token = c.getValue();
                Claims claims = JwtUtils.checkJWT(token);
                if (claims != null) {
                    String name = (String) claims.get("name");
                    User user = userService.findByname(name);
                    String o = userDto.getOldPassword();
                    String n = userDto.getNewPassword();
                    if (!userDto.getOldPassword().equals(user.getUserPwd())) {
                        return JsonData.buildError(0, null, "输入的原密码错误");
                    }
                    user.setUserPwd(userDto.getNewPassword());
                    user.setGmtModified(new Date());
                    userService.updatePassword(user);

                    return JsonData.buildSuccess(1, user, "修改密码成功");


                } else {
                    return JsonData.buildError(0, null, "非法token");
                }

            }
        }

        return JsonData.buildError(0,null,"未登录");
    }


    /**
     * 修改邮箱
     * @param newEmail
     * @param request
     * @return
     */
    @ApiOperation("用户修改密码接口")
    /*@ApiImplicitParam(name="newEmail",value = "新邮箱地址",required = true,paramType = "query")*/
    @PutMapping("/users/email")
    public JsonData changeEmail(@Param("newEmail") String newEmail, HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {

                String token = c.getValue();
                Claims claims = JwtUtils.checkJWT(token);
                if (claims != null) {
                    String name = (String) claims.get("name");
                    User user = userService.findByname(name);

                    user.setUserEmail(newEmail);
                    user.setGmtModified(new Date());
                    userService.updateUserEmail(user);

                    return JsonData.buildSuccess(1, user, "修改邮箱成功");


                } else {
                    return JsonData.buildError(0, null, "非法token");
                }

            }
        }

        return JsonData.buildError(0,null,"未登录");
    }








}
