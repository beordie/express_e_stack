package com.beordie.controller;

import com.beordie.model.Message;
import com.beordie.model.ResultData;
import com.beordie.model.StandardUsers;
import com.beordie.model.Users;
import com.beordie.mvc.ResponseText;
import com.beordie.service.IUserService;
import com.beordie.service.impl.UserServiceImpl;
import com.beordie.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Classname UserController
 * @Description 用户管理
 * @Date 2021/6/29 10:40
 * @Created 30500
 */
public class UserController {
    private IUserService userService = new UserServiceImpl();

    @ResponseText("/user/findAll.udo")
    public String getAll(HttpServletRequest request, HttpServletResponse response) {
        //1.    获取查询数据的起始索引值
        String offsetS = request.getParameter("offset") == null ? "0" : request.getParameter("offset");
        int offset = Integer.parseInt(offsetS);

        //2.    获取当前页要查询的数据量
        String pageNumberS = request.getParameter("pageNumber") == null ? "5" : request.getParameter("pageNumber");
        int pageNumber = Integer.parseInt(pageNumberS);

        //3.    获取身份标识
        String identityS = request.getParameter("courer");
        int courer = Integer.parseInt(identityS);

        List<StandardUsers> usersList = userService.getAllUsers(true, offset, pageNumber, courer);
        List<Map<String, Integer>> resultAll = userService.getConsoleData();
        Map<String, Integer> result = resultAll.get(0);
        Integer total = result.get("data3_size");

        ResultData<StandardUsers> data = new ResultData<>();
        data.setRows(usersList);
        data.setTotal(total);
        String json = JsonUtils.parseObject(data);
        return json;
    }

    @ResponseText("/user/insert.udo")
    public String insert(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String number = request.getParameter("number");
        String password = request.getParameter("password");
        int identity = Integer.parseInt(request.getParameter("identity"));

        int result = userService.insert(name, password, phone, number, identity);
        Message message = new Message();
        if (result > 0) {
            message.setStatus(0);
            message.setResult("新增成功");
        } else {
            message.setStatus(-1);
            message.setResult("新增失败");
        }
        String json = JsonUtils.parseObject(message);
        return json;
    }

    @ResponseText("/user/findByPhone.udo")
    public String findByPhone(HttpServletRequest request, HttpServletResponse response) {
        // 获取参数
        String phone = request.getParameter("phone");
        int identity = Integer.parseInt(request.getParameter("identity"));

        Users result = userService.getByPhone(phone, identity);
        Message message = new Message();

        if (result != null) {
            message.setStatus(0);
            message.setData(result);
            message.setResult("查询成功");
        } else {
            message.setStatus(-1);
            message.setResult("查询失败");
        }

        String json = JsonUtils.parseObject(message);
        return json;
    }

    @ResponseText("/user/update.udo")
    public String updateUser(HttpServletRequest request, HttpServletResponse response) {
        // 获得参数
        String userName = request.getParameter("userName");
        String userPhone = request.getParameter("userPhone");
        String number = request.getParameter("number");
        String password = request.getParameter("password");
        int userId = Integer.parseInt(request.getParameter("userId"));

        int result = userService.update(userName, password, userPhone, number, userId);
        Message message = new Message();
        if (result > 0) {
            message.setStatus(0);
            message.setResult("更新成功");
        } else {
            message.setStatus(-1);
            message.setResult("更新失败");
        }
        String json = JsonUtils.parseObject(message);
        return json;
    }

    @ResponseText("/user/delete.udo")
    public String deleteUser(HttpServletRequest request, HttpServletResponse response) {
        int userId = Integer.parseInt(request.getParameter("id"));

        int result = userService.delete(userId);

        Message message = new Message();
        if (result > 0) {
            message.setStatus(0);
            message.setResult("删除成功");
        } else {
            message.setStatus(-1);
            message.setResult("删除失败");
        }
        String json = JsonUtils.parseObject(message);
        return json;
    }
}
