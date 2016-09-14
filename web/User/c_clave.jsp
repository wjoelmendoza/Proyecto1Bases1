<%-- 
    Document   : c_clave
    Created on : 12/09/2016, 07:03:15 PM
    Author     : wxjoy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../WEB-INF/jspf/val_u.jspf" %>
<%valUsuario(session,response);%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/jspf/headeru.jspf" %>
        <title>Cambiar Clave</title>
    </head>
    <body rol="document">
        <%@include file="../WEB-INF/jspf/menu_u.jspf" %>
        <div role="main" class="container theme-showcase">
            <br>
            <div class="jumbotron">
                <form method="post" action="/Apuestas/cambiarc">
                    <table>
                        <tr>
                            <td>Clave:</td>
                            <td><input  name="c_act" type="password" required></td>
                        </tr>
                        <tr>
                            <td>Nueva clave:</td>
                            <td><input name="nueva_c" type="password" required></td>
                        </tr>
                        <tr>
                            <td>Reingrese la<br>Nueva clave</td>
                            <td><input name="nueva_cr" type="password" required></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <input class="btn btn-primary" type="submit" value="Aceptar">
                                <input class="btn btn-danger" type="reset" value="Cancelar">
                            </td>
                        </tr>
                    </table>
                </form>
                <%
                    Object a = session.getAttribute("error");
                    String cad;
                    if(a!=null){
                        session.removeAttribute("error");
                        cad =(String) a;
                %>
                    <br>
                    <br>
                    <div class="alert alert-danger" role="alert">
                        <strong>Error:</strong> No se ha podido cambiar la clave<br>
                        <%=cad%>
                    </div>
                <%
                    }
                    a = session.getAttribute("hecho");
                    if(a!=null){
                        session.removeAttribute("hecho");
                        cad = (String) a;
                %>
                    <br>
                    <br>
                    <div class="alert alert-success" role="alert">
                        <strong>Hecho:</strong> Se cambio su clave
                    </div>
                <%
                    }
                %>
            </div>
        </div>
        <%@include file="../WEB-INF/jspf/scriptsu.jspf" %>
    </body>
</html>
