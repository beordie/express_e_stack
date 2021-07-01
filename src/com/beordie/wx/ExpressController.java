package com.beordie.wx;

import com.beordie.model.Express;
import com.beordie.model.Message;
import com.beordie.model.StandardExpress;
import com.beordie.mvc.ResponseText;
import com.beordie.service.IExpressService;
import com.beordie.service.impl.ExpressService;
import com.beordie.utils.FormatDate;
import com.beordie.utils.JsonUtils;
import com.beordie.utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Classname ExpressController
 * @Description 微信端快递管理
 * @Date 2021/7/1 9:22
 * @Created 30500
 */
public class ExpressController {
    IExpressService expressService = new ExpressService();

    @ResponseText("/wx/getAllExpress.udo")
    public String getAllExpress(HttpServletRequest request, HttpServletResponse response) {
        String userPhone = UserUtils.getUserInfo(request.getSession()).getUserPhone();
        List<StandardExpress> list = expressService.getByUserPhone(userPhone);

        // 未取件
        Object[] objectI = list.stream().filter(e -> {
            if (e.getStatus() == 0)
                return true;
            else
                return false;
        }).sorted((e1, e2) -> {
            return (int) (FormatDate.parseString(e1.getInTime()) - FormatDate.parseString(e2.getInTime()));
        }).toArray();

        // 已取件
        Object[] objectO = list.stream().filter(e -> {
            if (e.getStatus() == 1)
                return true;
            else
                return false;
        }).sorted((e1, e2) -> {
            return (int) (FormatDate.parseString(e1.getOutTime()) - FormatDate.parseString(e2.getOutTime()));
        }).toArray();
        List<Object[]> result = new ArrayList<>();
        result.add(objectI);
        result.add(objectO);

        Message message = new Message();
        message.setStatus(0);
        message.setResult("查询结果");
        message.setData(result);
        return JsonUtils.parseObject(message);
    }
}
