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
                            if(partido.getCodPartido() == partId){
                    %>
                    <a 
                        href="/Apuestas/User/quinielaGrupo.jsp?grupo=<%=grupo%>&partido=<%=partido.getCodPartido()%>" 
                       class="list-group-item active">
                        <strong><%=partido.getRival1().getNombre()%> Vrs <%=partido.getRival2().getNombre()%></strong>
                    </a>
                    <%        
                            }else{
                    %>
                    <a 
                        href="/Apuestas/User/quinielaGrupo.jsp?grupo=<%=grupo%>&partido=<%=partido.getCodPartido()%>" 
                        class="list-group-item">
                        <strong><%=partido.getRival1().getNombre()%> Vrs <%=partido.getRival2().getNombre()%></strong>
                    </a>
                    <%
                            }
                      }%>
                </div>
                    <%
                        if(partId>0){
                    %>
                    <hr>
                    <h3>Ingrese su pronostico</h3>
                    <%=getFormulario(partId,grupo)%>
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

<%!
private String getFormulario(int partido, String grupo){
    Partido p = new Partido();
    p.buscarPartido(partido, grupo);
    if(p.crearMarcador())
        return this.getFormL(p);
    else
        return this.getFormD(p);
}

private String getFormL(Partido p){
    StringBuilder form = new StringBuilder();
    form.append("<form method='post' action='/Apuestas/LlenarMarcador'>\n")
        .append("<input name='eq1' hidden value='")
        .append(p.getRival1().getCodigo())
        .append("'>\n")
        .append("<input name='eq2' hidden value='")
        .append(p.getRival2().getCodigo())
        .append("' >\n")
        .append("<input name='part' hidden value='")
        .append(p.getCodPartido())
        .append("' > \n")
        .append("<input hidden name='crear' value='true'>\n")
        .append("<table>\n")
        .append("\t<tr>\n")
        .append("\t\t<td>")
        .append(p.getRival1().getNombre())
        .append("</td>\n\t\t<td>")
        .append("<input name='goles1' type='number' min=0 max=50 required></td>\n\t</tr>\n")
        .append("\t<tr>\n")
        .append("\t\t<td>")
        .append(p.getRival2().getNombre())
        .append("</td>\n\t\t<td>")
        .append("<input name='goles2' type='number' min=0 max=50 required></td>\n\t</tr>\n")
        .append("\t<tr>\n")
        .append("\t\t<td>\n\t\t</td>\n\t\t<td>\n")
        .append("\t\t\t<input type='submit' value='Aceptar'>\n")
        .append("\t\t\t<input type='reset' value='Cancelar'>\n\t\t<td>\n</tr>\n")
        .append("</table>\n")
        .append("</form>\t");
    return form.toString();
}

private String getFormD(Partido p){
    StringBuilder form = new StringBuilder();
    form.append("<form method='post' action='/Apuestas/User/LlenarMarcador'>\n")
        .append("<input name='eq1' hidden value='")
        .append(p.getRival1().getCodigo())
        .append("'>\n")
        .append("<input name='eq2' hidden value='")
        .append(p.getRival2().getCodigo())
        .append("' >\n")
        .append("<input name='part' hidden value='")
        .append(p.getCodPartido())
        .append("' > \n")
        .append("<input hidden name='crear' value='false'>\n")
        .append("<table>\n")
        .append("\t<tr>\n")
        .append("\t\t<td>")
        .append(p.getRival1().getNombre())
        .append("</td>\n\t\t<td>")
        .append("<input type='number' min=0 max=50 value=")
        .append(p.getRival1().getGoles())
        .append(" required></td></tr>\n")
        .append("\t<tr>")
        .append("\t\t<td>")
        .append(p.getRival2().getNombre())
        .append("</td>\n\t\t<td>")
        .append("<input type='number' min=0 max=50 value=")
        .append(p.getRival2().getGoles())
        .append(" required></td></tr>\n")
        .append("\t<tr>")
        .append("\t\t<td></td>\t\t<td>\n")
        .append("\t\t\t<input type='submit'value='Aceptar'>\n")
        .append("\t\t\t<input type='reset' value='Cancelar'>\n\t\t<td>\n</tr>\n")
        .append("</table>\n")
        .append("</form>");
    return form.toString();
}
%>
