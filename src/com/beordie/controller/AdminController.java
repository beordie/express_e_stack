package com.beordie.controller;

import com.beordie.model.Message;
import com.beordie.model.Users;
import com.beordie.mvc.ResponseText;
import com.beordie.mvc.ResponseView;
import com.beordie.service.IUserService;
import com.beordie.service.impl.UserServiceImpl;
import com.beordie.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Classname AdminController
 * @Description 管理员请求响应
 * @Date 2021/6/25 16:40
 * @Created 30500
 */
public class AdminController {
    private IUserService userService = new UserServiceImpl();

    @ResponseText("/admin/login.udo")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("username");
        String password = request.getParameter("password");

        // 消息结构
        Message msg = null;
        Users user = userService.getByNamePass(name, password);
        // 判断密码和用户名是否正确
        if (user != null) {
            String ip = request.getRemoteAddr();
            Date time = new Date();
            userService.updateIpLoginTime(user.getUserId(), ip, time);
            msg = new Message(0, "登陆成功");
        } else {
            msg = new Message(-1, "登陆失败");
        }
        String result = JsonUtils.parseObject(msg);
        return result;
    }
}
