package com.jin.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//统计网站在线人数：统计session
public class OnlineCountListener implements HttpSessionListener {
    //创建session的监听：看你的一举一动
    //一旦创建session就会触发一次这个事件
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();
        Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");

        if (onlineCount==null) {
            onlineCount = 1;
        } else {
            onlineCount++;
        }

        ctx.setAttribute("OnlineCount",onlineCount);
    }
    //销毁session的监听
    //一旦销毁session就会触发一次这个事件
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();
//        se.getSession().invalidate();
        Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");

        if (onlineCount==null) {
            onlineCount = 0;
        } else {
            onlineCount--;
        }

        ctx.setAttribute("OnlineCount",onlineCount);
    }
    /*
    Session销毁
    1. 手动销毁 se.getSession().invalidate();
    2. 自动销毁
     */
}
