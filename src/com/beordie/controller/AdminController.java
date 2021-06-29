package com.beordie.controller;

import com.beordie.model.*;
import com.beordie.mvc.ResponseText;
import com.beordie.mvc.ResponseView;
import com.beordie.service.IAdminService;
import com.beordie.service.IUserService;
import com.beordie.service.impl.AdminService;
import com.beordie.service.impl.UserServiceImpl;
import com.beordie.utils.AdminUtils;
import com.beordie.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Classname AdminController
 * @Description 管理员请求响应
 * @Date 2021/6/25 16:40
 * @Created 30500
 */
public class AdminController {
    private IAdminService adminService = new AdminService();

    @ResponseText("/admin/login.udo")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("username");
        String password = request.getParameter("password");

        // 消息结构
        Message msg = null;
        Admin admin = adminService.getByNamePass(name, password);
        // 判断密码和用户名是否正确
        if (admin != null) {
            String ip = request.getRemoteAddr();
            Date time = new Date();
            adminService.updateIpLoginTime(admin.getId(), ip, time);
            msg = new Message(0, "登陆成功");
            request.getSession().setAttribute("userName", admin.getName());
            request.getSession().setAttribute("userPhone", admin.getPhone());
        } else {
            msg = new Message(-1, "登陆失败");
        }
        String result = JsonUtils.parseObject(msg);
        return result;
    }

    @ResponseText("/admin/quit.udo")
    public String quit(HttpServletRequest request, HttpServletResponse response) {
        AdminUtils.quit(request.getSession());
        Message message = new Message(0, "退出成功");
        return JsonUtils.parseObject(message);
    }

}
