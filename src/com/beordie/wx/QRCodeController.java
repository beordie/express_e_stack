package com.beordie.wx;

import com.beordie.model.Message;
import com.beordie.model.Users;
import com.beordie.mvc.ResponseText;
import com.beordie.mvc.ResponseView;
import com.beordie.utils.JsonUtils;
import com.beordie.utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;

/**
 * @Classname QRCodeController
 * @Description TODO
 * @Date 2021/7/1 11:44
 * @Created 30500
 */
public class QRCodeController {
    /**
     * @description 保存取件码
     * @author 30500
     * @date 2021/7/1 12:02
     * @type [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse]
     * @return java.lang.String
     */
    @ResponseView("/wx/createQRCode.udo")
    public String createQRCode(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        //express | user
        String type = request.getParameter("type");
        String userPhone = null;
        String qRCodeContent = null;
        if("express".equals(type)){
            //快递二维码:被扫后,展示单个快递的信息
            //code
            qRCodeContent = "express_"+code;
        }else{
            //用户二维码:被扫后,快递员(柜子)端展示用户所有快递
            //userPhone
            userPhone = UserUtils.getUserInfo(request.getSession()).getUserPhone();
            qRCodeContent = "userPhone_"+userPhone;
        }
        HttpSession session = request.getSession();
        session.setAttribute("qrcode",qRCodeContent);
        return "/personQRcode.html";
    }

    /**
     * @description 获取取件码
     * @author 30500
     * @date 2021/7/1 12:03
     * @type [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse]
     * @return java.lang.String
     */
    @ResponseText("/wx/qrcode.udo")
    public String qrCode(HttpServletRequest request, HttpServletResponse response) {
        String code = (String) request.getSession().getAttribute("qrcode");
        // 取出数据后进行销毁
        request.getSession().removeAttribute("qrcode");
        Message message = new Message();
        if(code == null){
            message.setStatus(-1);
            message.setResult("取件码获取出错,请用户重新操作");
        }else{
            message.setStatus(0);
            message.setResult(code);
        }
        return JsonUtils.parseObject(message);
    }
}
