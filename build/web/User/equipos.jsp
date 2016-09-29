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
        <%@include file="../WEB-INF/jspf/headeru.jspf" %> href
        <title>Equipos</title>
        
        <script>
            function jsequipo(gr,codeq) {
                var fl = 'hrefplantillas';
                var idd  = 'href'+gr+codeq;
                
                var c = document.getElementById('divjumbo').getElementsByTagName("a");
                //var num = document.getElementById('divjumbo').childNodes.length;
                var mess=""
                for(var i=0; i<c.length; i++)
                {
                     if (c[i].id == idd)
                        {
                            document.getElementById(c[i].id).className = "list-group-item active";
                        }
                        else
                        {
                            document.getElementById(c[i].id).className = "list-group-item";
                        }
                    
                    
                   mess = mess+", "+c[i].id;
                }
                //alert(mess);
                
                $.post('/Apuestas/jscliente', {
				flaa : fl,
                                codequipo : codeq,
                                grupo : gr
				
			}, function(responseText) {
                            	$("#divplantilla").html(responseText);
			});
            }
        </script>
        
    </head>
    <body role="document">
        <%@include file="../WEB-INF/jspf/menu_u.jspf" %>
        <div role="main" class="container theme-showcase">
            <br/>
            <div  id="divjumbo" class="jumbotron">
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
                <a name="href<%=grupo.charAt(0)%><%=equipo.getCodEquipo()%>" id="href<%=grupo.charAt(0)%><%=equipo.getCodEquipo()%>" name href="javascript:jsequipo('<%=grupo.charAt(0)%>','<%=equipo.getCodEquipo()%>');"  class="list-group-item active">
                        <strong><%=equipo.getPais()%><br></strong>
                        <strong>Entrenador</strong> <%=equipo.getDirector()%><br>
                        <strong>Confederacion: </strong><%=equipo.getConfederacion()%>
                    </a>
                <%
                        }else{
                %>
                    <a name="href<%=grupo.charAt(0)%><%=equipo.getCodEquipo()%>" id="href<%=grupo.charAt(0)%><%=equipo.getCodEquipo()%>" href="javascript:jsequipo('<%=grupo.charAt(0)%>','<%=equipo.getCodEquipo()%>');" class="list-group-item">
                        <strong><%=equipo.getPais()%><br></strong>
                        <strong>Entrenador</strong> <%=equipo.getDirector()%><br>
                        <strong>Confederacion: </strong><%=equipo.getConfederacion()%>
                    </a>
                        <%}%>
                <%
                    }
                %>
                        </div> <!-- aqui va lo del if -->
                        <%     
                   
                    }else{
                %>
                </h2>
                <br>
                <%
                    }
                %>
                        <div id="divplantilla">
                            
                            dasdsadsadsadsadsadas
                        </div>
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