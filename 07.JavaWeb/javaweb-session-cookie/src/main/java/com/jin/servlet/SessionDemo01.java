package com.jin.servlet;

import com.jin.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码问题
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");
        //得到session
        HttpSession session = req.getSession();
        //给session中存东西
        session.setAttribute("name",new Person("津",1));
        //获取session的id
        String sessionId = session.getId();
        //判断session是不是新创建的
        if (session.isNew()) {
            resp.getWriter().write("session创建成功，ID: "+sessionId);
        } else {
            resp.getWriter().write("session已经在服务器中存在了，ID: " + sessionId);
        }
//        //session创建的时候做了什么事情
//        Cookie cookie = new Cookie("JSESSONID", sessionId);
//        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
