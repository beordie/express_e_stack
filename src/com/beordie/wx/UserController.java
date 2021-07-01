package com.beordie.wx;

import com.beordie.model.Message;
import com.beordie.model.Users;
import com.beordie.mvc.ResponseText;
import com.beordie.mvc.ResponseView;
import com.beordie.service.IUserService;
import com.beordie.service.impl.UserServiceImpl;
import com.beordie.utils.GenerationCode;
import com.beordie.utils.JsonUtils;
import com.beordie.utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Classname UserController
 * @Description 用户
 * @Date 2021/6/30 22:27
 * @Created 30500
 */
public class UserController {
    IUserService service = new UserServiceImpl();

    /**
     * @description 获取验证码
     * @author 30500
     * @date 2021/7/1 7:46
     * @type [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse]
     * @return java.lang.String
     */
    @ResponseText("/wx/cellCode.udo")
    public String getCode(HttpServletRequest request, HttpServletResponse response) {
        // 获取临时手机号的登陆验证码
        String userPhone = request.getParameter("userPhone");
        String cellCode = GenerationCode.generationCode();

        UserUtils.setTempInfo(request.getSession(), userPhone, cellCode);
        // 前端信息
        Message message = new Message();
        message.setResult("验证码: " + cellCode);
        message.setStatus(0);
        String json = JsonUtils.parseObject(message);
        return json;
    }

    /**
     * @description 登陆 / 注册
     * @author 30500
     * @date 2021/7/1 7:47
     * @type [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse]
     * @return java.lang.String
     */
    @ResponseText("/wx/login.udo")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String phone = request.getParameter("phone");
        String code = request.getParameter("code");
        String cellCode = UserUtils.getTempInfo(request.getSession(), phone);

        Message message = new Message();
        Users users = null;
        // 检查验证码是否正确
        if (cellCode != null) { // 该手机号获取验证码
            if (!Objects.equals(code, cellCode)) { // 验证码错误
                message.setStatus(-1);
                message.setResult("验证码错误");
            } else { // 验证码正确
                users = service.getByPhone(phone, 0);
                if (users == null) { // 不是管理员
                    users = service.getByPhone(phone, 1);
                    if (users == null) { // 不是用户 , 默认注册用户
                        service.insert("佚名" + phone, "123456", phone, "522424199912129999", 1);
                        users = service.getByPhone(phone, 1);
                        message.setStatus(1);
                        message.setResult("用户注册成功");
                    } else { // 用户手机号
                        message.setStatus(1);
                        message.setResult("用户登陆成功");
                    }
                    UserUtils.setAuthority(request.getSession(), false);
                } else { // 管理员手机号
                    message.setStatus(0);
                    message.setResult("管理员登陆成功");
                    UserUtils.setAuthority(request.getSession(), true);
                }
            }
        } else { // 该手机未获取验证码
            message.setStatus(-1);
            message.setResult("该手机未获取验证码");
        }
        // 到这一步 users 肯定有数据
        UserUtils.setUserInfo(request.getSession(), users);
        // 返回结果
        message.setData(users);
        String json = JsonUtils.parseObject(message);
        return json;
    }

    /**
     * @description 退出
     * @author 30500
     * @date 2021/7/1 8:30
     * @type [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse]
     * @return java.lang.String
     */
    @ResponseText("/wx/logout.udo")
    public String quit(HttpServletRequest request, HttpServletResponse response) {
        UserUtils.logout(request.getSession());
        Message message = new Message(0, "退出登陆");
        return JsonUtils.parseObject(message);
    }

    @ResponseText("/wx/userInfo.udo")
    public String userInfo(HttpServletRequest request, HttpServletResponse response) {
        Users users = UserUtils.getUserInfo(request.getSession());
        Message message = new Message();

        if (users == null) {
            message.setStatus(-1);
        } else {
            if (UserUtils.getAuthority(request.getSession())) { // 管理
                message.setStatus(0);
            } else { // 用户
                message.setStatus(1);
            }
        }
        return JsonUtils.parseObject(message);
    }
}
