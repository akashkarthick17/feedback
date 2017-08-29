<%--
  Created by IntelliJ IDEA.
  User: Akash
  Date: 14-08-2017
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Feedback</title>
</head>
<body>
<%
    if(request.getParameter("register")!=null) {
        String user = request.getParameter("register");
        if (user.equals("111514104005")) {

            response.sendRedirect("student_login/dashboard.jsp");
        }
        else{
            response.sendRedirect("index.jsp");
        }
    }
    else if(request.getParameter("pass")!=null){
        String pass = request.getParameter("pass");
        if (pass.equals("123")) {

            response.sendRedirect("admin_login/dashboard.jsp");
        }
    }
    else {


        response.sendRedirect("index.jsp");
    }
%>
</body>
</html>
