<%-- 
    Document   : equipos
    Created on : 22/09/2016, 01:14:57 AM
    Author     : wxjoy
--%>
<%@page import="com.conexion.Jugador"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.conexion.Equipo"%>
<%@include file="../WEB-INF/jspf/val_u.jspf" %>
<%valUsuario(session,response);%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/jspf/headeru.jspf" %>
        <title>Equipos</title>
    </head>
    <body role="document">
        <%@include file="../WEB-INF/jspf/menu_u.jspf" %>
        <div role="main" class="container theme-showcase">
            <br/>
            <div class="jumbotron">
                <h2>
                    Equipos Grupo 
                <%
                    String grupo = request.getParameter("grupo");
                    int codeq = Integer.parseInt(request.getParameter("eq"));
                    Equipo equipo;
                    ArrayList<Equipo> equipos;
                    if(grupo!= null){
                        equipo = new Equipo();
                        equipos = equipo.getEquiposGrupo(grupo.charAt(0));
                        %>
                        <%=grupo%>
                        </h2>
                        <br>
                        <div class="list-group">
                 <%
                        for(int i = 0; i<equipos.size(); i++){
                            equipo = equipos.get(i);
                            if(equipo.getCodEquipo() == codeq){
                %>
                <a href="/Apuestas/User/equipos.jsp?grupo=<%=grupo.charAt(0)%>&eq=<%=equipo.getCodEquipo()%>" class="list-group-item active">
                        <strong><%=equipo.getPais()%><br></strong>
                        <strong>Entrenador</strong> <%=equipo.getDirector()%><br>
                        <strong>Confederacion: </strong><%=equipo.getConfederacion()%>
                    </a>
                <%
                        }else{
                %>
                    <a href="/Apuestas/User/equipos.jsp?grupo=<%=grupo.charAt(0)%>&eq=<%=equipo.getCodEquipo()%>" class="list-group-item">
                        <strong><%=equipo.getPais()%><br></strong>
                        <strong>Entrenador</strong> <%=equipo.getDirector()%><br>
                        <strong>Confederacion: </strong><%=equipo.getConfederacion()%>
                    </a>
                        <%}%>
                <%
                    }
                %>
                        </div>
                <%     
                    if(codeq!= 0){
                        Equipo e = new Equipo();
                        e.buscarEquipo(grupo, codeq);
                        
                        if(e.hasEquipo()){
                %>
                <hr>
                <%=escribirPlantilla(e)%>
                <%        }
                    }
                    }else{
                %>
                </h2>
                <br>
                <%
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