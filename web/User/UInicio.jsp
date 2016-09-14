<%-- 
    Document   : UInicio
    Created on : 8/09/2016, 12:02:19 AM
    Author     : wxjoy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../WEB-INF/jspf/val_u.jspf" %>
<%valUsuario(session,response);%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/jspf/headeru.jspf" %>
        <title>Inicio-Usuario</title>
    </head>
    <body rol="document">
        <%@include file="../WEB-INF/jspf/menu_u.jspf" %>
        <div role="main" class="container theme-showcase">
            <br>
            <div class="jumbotron">
                <h1>Hello World!</h1>
                habria que validar que no entre cualquiera
            </div>
        </div>
        <%@include file="../WEB-INF/jspf/scriptsu.jspf" %>
    </body>
</html>
