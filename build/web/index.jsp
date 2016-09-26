<%-- 
    Document   : index
    Created on : 4/09/2016, 11:58:59 PM
    Author     : wxjoy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/jspf/val_l.jspf" %>
<%val_user(session,response);%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <title>Inicio</title>
    </head>
    <body role="document" >
        <%@include file="WEB-INF/jspf/menup.jspf" %>
        <br>
        <div class="container theme-showcase" role="main">
            <div class="jumbotron">
                <h1>Bienvenido!</h1>
                Hola gracias por visitarnos, Somos el mejor sitio de apuestas<br/>
                del mundo. Usted tiene la oportunidad de participar en nuestras<br/>
                quinielas para cada partido de la fase de grupos del mundial.
                <br/><br/>
                
                Apoya a tus equipos favoritos, si adivinas el marcador de los<br>
                partidos iras acumulando puntos que al final de la fase podras<br>
                hacer canjear por dinero.<br/><br/>
                
                No esperes mas y empieza a participar y si ya eres usuario<br>
                no dejes de darnos tu pronostico.<br>
            </div>
            <%
                Object msj =  session.getAttribute("msj");
                if(msj != null){
                    session.removeAttribute("msj");
            %>
            <br>
            <br>
            <div class="alert alert-success" role="alert">
                <strong>Hecho:</strong>
                <%=msj.toString()%>
            </div>
            <%}%>
        </div>
    </body>
    <%@include file="WEB-INF/jspf/scripts.jspf" %>
</html>
