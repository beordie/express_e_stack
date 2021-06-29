package com.beordie.controller;

import com.beordie.model.Express;
import com.beordie.model.Message;
import com.beordie.model.ResultData;
import com.beordie.model.StandardExpress;
import com.beordie.mvc.ResponseText;
import com.beordie.service.IExpressService;
import com.beordie.service.IUserService;
import com.beordie.service.impl.ExpressService;
import com.beordie.service.impl.UserServiceImpl;
import com.beordie.utils.AdminUtils;
import com.beordie.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Classname ExpressController
 * @Description TODO
 * @Date 2021/6/26 20:09
 * @Created 30500
 */
public class ExpressController {
    IExpressService service = new ExpressService();
    IUserService userService = new UserServiceImpl();
    /**
     * @description 用于获取控制台所需的快递数据
     * @author 30500
     * @date 2021/6/26 20:20
     * @type [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse] 
     * @return java.lang.String
     */
    @ResponseText("/express/console.udo")
    public String getConsoleData(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Integer>> result = service.getConsoleData();
        Map<String, Integer> userData = userService.getConsoleData();
        Message message = new Message();
        if (result != null && userData != null) {
            result.add(userData);
            message.setData(result);
            message.setResult("查询成功");
            message.setStatus(0);
        } else {
            message.setResult("查询失败");
            message.setStatus(-1);
        }
        String json = JsonUtils.parseObject(message);
        return json;
    }

    /**
     * @description 快件列表（分页）
     * @author 30500
     * @date 2021/6/26 20:21
     * @type [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse]
     * @return java.lang.String
     */
    @ResponseText("/express/findAll.udo")
    public String findAll(HttpServletRequest request, HttpServletResponse response) {
        //1.    获取查询数据的起始索引值
        String offsetS = request.getParameter("offset") == null ? "0" : request.getParameter("offset");
        int offset = Integer.parseInt(offsetS);

        //2.    获取当前页要查询的数据量
        String pageNumberS = request.getParameter("pageNumber") == null ? "5" : request.getParameter("pageNumber");
        int pageNumber = Integer.parseInt(pageNumberS);

        //3.    进行查询
        List<StandardExpress> list = service.getAllExpress(true, offset, pageNumber);
        List<Map<String, Integer>> result = service.getConsoleData();
        Integer total = result.get(0).get("data1_size");

        //4.    将集合封装为 bootstrap-table识别的格式
        ResultData<StandardExpress> data = new ResultData<>();
        data.setRows(list);
        data.setTotal(total);
        String json = JsonUtils.parseObject(data);
        return json;
    }

    /**
     * @description 根据单号查询快递信息
     * @author 30500
     * @date 2021/6/26 20:21
     * @type [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse] 
     * @return java.lang.String
     */
    @ResponseText("/express/findByNumber.udo")
    public String findByNumber(HttpServletRequest request, HttpServletResponse response) {
        // 拿到 number 信息
        String number = request.getParameter("number");
        Express express = service.getByNUmber(number);
        Message message = new Message();

        if (express == null) {
            message.setStatus(-1);
            message.setResult("查询失败");
        } else {
            message.setStatus(0);
            message.setResult("查询成功");
            message.setData(express);
        }
        String json = JsonUtils.parseObject(message);
        return json;
    }

    /**
     * @description 根据取件码查询快递信息
     * @author 30500
     * @date 2021/6/26 20:21
     * @type [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse] 
     * @return java.lang.String
     */
    @ResponseText("/express/findByCode.udo")
    public String findByCode(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
    
    /**
     * @description 根据用户的手机号，查询快递信息
     * @author 30500
     * @date 2021/6/26 20:21
     * @type [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse] 
     * @return java.lang.String
     */
    @ResponseText("/express/findByUserPhone.udo")
    public String findByUserPhone(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    /**
     * @description 根据录入人的手机号，查询快递信息（快递员/柜子的历史记录）
     * @author 30500
     * @date 2021/6/26 20:24
     * @type [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse]
     * @return java.lang.String
     */
    @ResponseText("/express/findBySysPhone.udo")
    public String findBySysPhone(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    /**
     * @description 快件录入
     * @author 30500
     * @date 2021/6/26 20:21
     * @type [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse] 
     * @return java.lang.String
     */
    @ResponseText("/express/insert.udo")
    public String insert(HttpServletRequest request, HttpServletResponse response) {
        // 获取参数
        String number = request.getParameter("number");
        String company = request.getParameter("company");
        String username = request.getParameter("username");
        String userPhone = request.getParameter("userPhone");

        int result = service.insertExpress(number, username, company, userPhone, AdminUtils.getAdminPhone(request.getSession()));
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
    
    /**
     * @description 修改快递信息
     * @author 30500
     * @date 2021/6/26 20:21
     * @type [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse] 
     * @return java.lang.String
     */
    @ResponseText("/express/update.udo")
    public String update(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String number = request.getParameter("number");
        String company = request.getParameter("company");
        String userName = request.getParameter("userName");
        int status = Integer.parseInt(request.getParameter("status"));
        String userPhone = request.getParameter("userPhone");
        int result = service.updateExpress(id, number, userName, company, userPhone, status);

        Message message= new Message();
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
    
    /**
     * @description 根据id删除快递信息
     * @author 30500
     * @date 2021/6/26 20:21
     * @type [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse] 
     * @return java.lang.String
     */
    @ResponseText("/express/delete.udo")
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int result = service.deleteExpress(id);

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
    
    /**
     * @description 确认取件
     * @author 30500
     * @date 2021/6/26 20:21
     * @type [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse] 
     * @return java.lang.String
     */
    @ResponseText("/express/updateStatus.udo")
    public String updateStatus(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

}
