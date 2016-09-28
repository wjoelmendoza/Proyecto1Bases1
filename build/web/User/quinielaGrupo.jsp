<%-- 
    Document   : quinielaGrupo
    Created on : 26/09/2016, 05:02:23 PM
    Author     : wxjoy
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.conexion.Partido"%>
<%@include file="../WEB-INF/jspf/val_u.jspf" %>
<%valUsuario(session,response);%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/jspf/headeru.jspf" %>
        <title>Quiniela Grupo</title>
    </head>
    <body role="document">
        <%@include file="../WEB-INF/jspf/menu_u.jspf" %>
        <div role="main" class="container theme-showcase">
            <br>
            <div class="jumbotron">
                <h2>
                    Partidos Grupo 
                    <%
                        String grupo = request.getParameter("grupo");
                        String pid = request.getParameter("partido");
                        int partId;
                        Partido partido;
                        ArrayList<Partido> partidos;
                        if(grupo != null && pid != null){
                            partido = new Partido();
                            partidos = partido.getPartidoGrupo(grupo);
                            partId = Integer.parseInt(pid);
                    %>
                    <%=grupo%>
                </h2>
                <br>
                <div class="list-group">
                    <%
                        for(int i = 0; i<partidos.size(); i++){
                            partido = partidos.get(i);
                    %>
                    <a href="#" class="list-group-item">
                        <strong><%=partido.getRival1().getNombre()%> VS <%=partido.getRival2().getNombre()%></strong>
                    </a>
                    <%
                        }
                        }else{
                    %>
                    </h2>
                    <br>
                    Opcion Desconocida
                    <%
                        }
                    %>
            </div>
        </div>
        <%@include file="../WEB-INF/jspf/scriptsu.jspf" %>
    </body>
</html>
