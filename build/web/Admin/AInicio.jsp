<%-- 
    Document   : AInicio
    Created on : 8/09/2016, 03:00:20 AM
    Author     : wxjoy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../WEB-INF/jspf/val_a.jspf" %>
<% valAdmin(session,response);%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/jspf/headera.jspf" %>
        <title>Inicio-Admin</title>
    </head>
    <body rol="document">
        <%@include file="../WEB-INF/jspf/menu_a.jspf"%>
        <div role="main" class="container theme-showcase">
            <br>
            <div class="jumbotron">
                <h1>Hola administrador!</h1>
            </div>
        </div>
        <%@include file="../WEB-INF/jspf/scriptsu.jspf" %>
    </body>
</html>
