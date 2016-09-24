<%-- 
    Document   : login
    Created on : 5/09/2016, 01:32:47 AM
    Author     : wxjoy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/jspf/val_l.jspf" %>
<%val_user(session,response);%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <title>Iniciar Sesion</title>
    </head>
    <body role="document">
        <%@include file="WEB-INF/jspf/menup.jspf" %>
        <div role="main" class="container theme-showcase">
            <div class="jumbotron">
                <h2>Iniciar Sesion</h2>
                Para poder realizar su Quiniela debe de identificarse<br>
                ingrese los datos solicitados
                <form method="post" action="/Apuestas/LoginUser" >
                    <table>
                        <tr>
                            <td>Correo</td>
                            <td><input name="correo" type="email" required placeholder="micorreo@dominio.com"></td>
                        </tr>
                        <tr>
                            <td>Clave</td>
                            <td><input name="clave" type="password" required placeholder="123456"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <input value="Aceptar" class="btn btn-success" type="submit">
                                <input value="Cancelar" type="reset" class="btn btn-danger">
                            </td>
                        </tr>
                    </table>
                </form>
                 <%
                     String error =(String) session.getAttribute("error");
                     if(error!=null){
                        session.removeAttribute("error");
                    %>
                        <br>
                        <br>
                        <div class="alert alert-danger" role="alert">
                            <strong>Error:</strong> <%=error%>
                        </div>
                <%
                    }
                 %>
            </div>
        </div>
        <%@include file="WEB-INF/jspf/scripts.jspf" %>
    </body>
</html>
