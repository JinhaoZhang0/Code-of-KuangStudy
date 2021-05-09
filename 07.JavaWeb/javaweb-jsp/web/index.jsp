
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%-- JSP表达式
  作用：用来将程序的输出，输出到客户端
  <%= 变量或表达式%>
  --%>
  <%= new Date()%>
  <hr>
  <%--JSP脚本片段--%>
  <%
    int sum = 0;
    for (int i = 1; i <= 100; i++) {
      sum += i;
    }
    out.println("<h1>sum="+sum+"</h>");
  %>

  <%
    int x = 10;
    out.println(x);
  %>

  <p>这是一个JSP文档</p>
  <%
    int y = 20;
    out.println(y);
  %>
  <hr>
<%--  在代码中嵌入HTML元素--%>
  <% for (int i = 0; i < 5; i++) { %>
  <h1>Hello, world! <%=i%></h1>
  <% } %>

  <hr>
  <%!
    static {
      System.out.println("Loading Servlet!");
    }
    private int globalVar = 0;

    public void kuang(){
      System.out.println("进入了方法kuang!");
    }
  %>


  </body>
</html>
