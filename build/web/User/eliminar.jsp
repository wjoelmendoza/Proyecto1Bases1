<%-- 
    Document   : eliminar
    Created on : 25/09/2016, 09:36:22 PM
    Author     : wxjoy
--%>

<%@include file="../WEB-INF/jspf/val_u.jspf" %>
<%valUsuario(session,response);%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/jspf/headeru.jspf" %>
        <title>Eliminar Usuario</title>
    </head>
    <body role="document">
        <%@include file="../WEB-INF/jspf/menu_u.jspf" %>
        <div role="main" class="container theme-showcase">
            <br>
            <div class="jumbotron">
                <h2>
                    Eliminar Usuario
                </h2>
                <br>
                Para poder eliminar su cuenta es necesario que ingrese su clave...
                <form method="post" action="/Apuestas/EliminarUser">
                    <table>
                        <tr>
                            <td>Ingrese su clave:</td>
                            <td>
                                <input name="clave" type="password" required placeholder="123456">
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <input type="submit" value="Aceptar" class="btn btn-success">
                                <input type="reset" value="Cancelar" class="btn btn-danger">
                            </td>
                        </tr>
                    </table>
                </form>
                <%
                    Object erro = request.getSession().getAttribute("error"); 
                    if(erro != null){
                        request.getSession().removeAttribute("error");
                %>
                    <br>
                    <br>
                    <div class="alert alert-danger" role="alert">
                        <strong>Error</strong> No se ha podido eliminar su usuario<br>
                        <%=erro.toString()%>
                    </div>
                <%
                    }
                %>
            </div>
        </div>
        <%@include file="../WEB-INF/jspf/scriptsu.jspf" %>
    </body>
</html>
