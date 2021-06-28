package com.beordie.mvc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Classname FrameServlet
 * @Description 统一消息分发
 * @Date 2021/6/24 12:25
 * @Created 30500
 */
public class FrameServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        HandlerMapping.Mapping mapping = HandlerMapping.getData().get(url);
        if (mapping == null){
            resp.sendError(404, "地址映射不存在" + url);
            return;
        }
        System.out.println(url);
        // 响应地址的对象
        Object obj = mapping.getObject();
        // 对应的方法
        Method method = mapping.getMethod();
        // 接收方法的返回
        Object result = null;
        try {
            result = method.invoke(obj, req, resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 按照格式返还给前端页面数据
        switch (mapping.getType()) {
            case TEXT:
                resp.getWriter().write((String) result);
                break;
            case VIEW:
                resp.sendRedirect((String) result);
                break;
        }

    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 获取配置文件输入流
        String path = config.getInitParameter("contentConfig");
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(path);
        HandlerMapping.load(stream);
    }
}
