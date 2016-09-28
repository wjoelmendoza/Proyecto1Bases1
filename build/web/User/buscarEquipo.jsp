<%-- 
    Document   : buscarEquipo
    Created on : 13/09/2016, 02:15:38 PM
    Author     : wxjoy
--%>

<%@page import="com.conexion.Jugador"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=ISO-8859-1"%>
<%@page import="com.conexion.Equipo" %>
<%@include file="../WEB-INF/jspf/val_u.jspf" %>
<%valUsuario(session,response);%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/jspf/headeru.jspf" %>
        <title>Buscar Plantilla</title>
    </head>
    <body role="document">
        <%@include file="../WEB-INF/jspf/menu_u.jspf"%>
        <div role="main" class="container theme-showcase">
            <br>
            <div class="jumbotron">
                <form method="post" action="/Apuestas/User/buscarEquipo.jsp">
                    <table>
                        <tr>
                            <td>Ingrese el nombre<br>del equipo:</td>
                            <td><input name="pais" maxlength="50" type="text" required></td>
                            <td><input class="btn btn-primary" type="submit" value="Buscar"></td>
                        </tr>
                    </table>
                </form>
                <hr>
                <%
                    String a;
                    
                    if(request.getMethod()=="POST"){
                        a = request.getParameter("pais");
                        Equipo e = new Equipo();
                        e.buscarEquipo(a);
                        if(e.hasEquipo()){%>
                        <%=escribirPlantilla(e)%>
                        <%}else{
                %>
                <div rol="alert" class="alert alert-warning">
                    El Pais <%=a%> no cuenta con equipo que lo
                    represente en el mundial
                </div>
                <%
                    }
                }
                %>                
            </div>
        </div>
                <%@include file="../WEB-INF/jspf/scriptsu.jspf" %>
    </body>
</html>
<%!
private String escribirPlantilla(Equipo e){
/**falta colocar la posicion*/
    StringBuilder salida = new StringBuilder();
    ArrayList<Jugador> jugadores;
    salida.append("<h3>Equipo: ")
            .append(e.getPais())
            .append("</h3>")
            .append("<h3>")
            .append("Director tecnico: ")
            .append(e.getDirector())
            .append("</h3>")
            .append("<table class='table table-striped'>\n")
            .append("<thead>\n")
            .append("<tr>\n")
            .append("<th>\nCamiseta\n</th>\n")
            .append("<th>\nNombre\n</th>\n")
            .append("<th>\nPosicion\n</th>\n</thead>\n<tbody>");
    jugadores = e.getJugadores();
    Jugador j;
    for(int i =0; i<jugadores.size(); i++){
        j = jugadores.get(i);
        salida.append("<tr>\n")
                .append("<td>")
                .append(j.getCamiseta())
                .append("</td>\n")
                .append("<td>")
                .append(j.getNombre())
                .append("</td>\n")
                .append("<td>posicion</td>\n")
                .append("</tr>\n");
    }

    salida.append("</tbody>\n</table");
    return salida.toString();
}
%>