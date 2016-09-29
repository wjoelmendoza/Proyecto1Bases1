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
        <script>
            function jsPartido(grupoing,partidoing){
                var idrq = 'formq';
                var idnodo = 'id' + grupoing+partidoing;
                var nodos = document.getElementById('idlist').getElementsByTagName("a");
                for(var i=0; i<nodos.length; i++){
                    if(nodos[i].id === idnodo){
                        document.getElementById(nodos[i].id).className="list-group-item active";
                    }else{
                        document.getElementById(nodos[i].id).className="list-group-item";
                    }
                }
                $.post('/Apuestas/FormularioQ',{
                    idreq: idrq,
                    grupo: grupoing,
                    partido: partidoing
                },function(responseText){
                    $("#outdiv").html(responseText);
                });
            }
        </script>
    </head>
    <body role="document">
        <%@include file="../WEB-INF/jspf/menu_u.jspf" %>
        <div role="main" class="container theme-showcase">
            <br>
            <div class="jumbotron" id='idjumbo'>
                <h2>
                    Partidos Grupo 
                    <%
                        String grupo = request.getParameter("grupo");
                        String pid = request.getParameter("partido");
                        //int partId;
                        Partido partido;
                        ArrayList<Partido> partidos;
                        if(grupo != null && pid != null){
                            partido = new Partido();
                            partidos = partido.getPartidoGrupo(grupo);
                           // partId = Integer.parseInt(pid);
                    %>
                    <%=grupo%>
                </h2>
                <br>
                <strong>Seleccione un partido para pronosticar su resultado</strong>
                <br>
                <br>
                <div id="idlist" class="list-group">
                    <%
                        String id;
                        for(int i = 0; i<partidos.size(); i++){
                            partido = partidos.get(i);
                            id = ""+ grupo.charAt(0);
                            id += partido.getCodPartido();
                           
                    %>
                    <a 
                        id="id<%=id%>" name="id<%=id%>"
                        href="javascript:jsPartido('<%=grupo.charAt(0)%>','<%=partido.getCodPartido()%>');"
                        class="list-group-item">
                        <strong><%=partido.getRival1().getNombre()%> Vrs <%=partido.getRival2().getNombre()%></strong>
                    </a>
                    <%
                      }
                    %>
                </div>
                <hr>
                  
                    <%
                        
                        }else{
                    %>
                    </h2>
                    <br>
                    Opcion Desconocida
                    <%
                        }
                    %>
                    <div id="outdiv">
                    </div>
            </div>
        </div>
        <%@include file="../WEB-INF/jspf/scriptsu.jspf" %>
    </body>
</html>
