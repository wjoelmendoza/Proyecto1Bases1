<%-- 
    Document   : cambiard
    Created on : 7/10/2016, 10:04:10 PM
    Author     : wxjoy
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.conexion.Pais"%>
<%@page import="com.conexion.User"%>
<%@include file="../WEB-INF/jspf/val_u.jspf" %>
<%valUsuario(session, response);%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/jspf/headeru.jspf" %>
        <title>Cambiar Datos</title>
    </head>
    <body role="document">
        <%@include file="../WEB-INF/jspf/menu_u.jspf" %>
        <div role="main"  class="container theme-showcase">
            <br>
            <div class="jumbotron">
                <h1>Cambiar Datos</h1>
                <%
                    int codU =(int) session.getAttribute("id");
                    User u = new User();
                    u.cargarDatos(codU);
                    Pais p = new Pais();
                    p.getPaises();
                    session.setAttribute("mail", u.getCorreo());
                %>
                <form method="post" action="/Apuestas/ActDatosU" >
                    <table>
                        <tr>
                            <td>Nombre</td>
                            <td>
                                <input name="uNombre" type="text" value="<%=u.getNombre()%>" required>
                            </td>
                        </tr>
                        <tr>
                            <td>Fecha</td>
                            <td>
                                <input name="uFecha" type="date" value="<%=u.getFecha()%>" required pattern="\d{2}/\d{2}/\d{4}">
                            </td>
                        </tr>
                        <tr>
                            <td>Correo</td>
                            <td>
                                <input name="uCorreo" type="email" value="<%=u.getCorreo()%>" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Pais
                            </td>
                            <td>
                                <%=getPaises(u,p)%>
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
            </div>
            <br>
            <br>
            <%
                Object a = session.getAttribute("error");
                String cad;
                if(a!=null){
                    session.removeAttribute("error");
                    cad = (String) a;
            %>
            <div class="alert alert-danger" role="alert">
                <strong>Error:</strong> No se actualizaron sus datos
                <br>
                <%=cad%>
            </div>
            <%
                }
                a=session.getAttribute("hecho");
                if(a!=null){
                    session.removeAttribute("hecho");
                    cad = (String)a;
            %>
            <div class="alert alert-success" role="alert">
                <strong>Hecho:</strong> Se Actualizaron los datos
                <br>
            </div>     
            <%
                }
            %>
        </div>
        <%@include file="../WEB-INF/jspf/scriptsu.jspf" %>
    </body>
</html>

<%!
private String getPaises(User u, Pais p){
    ArrayList<String> paises = p.getPaises();
    StringBuilder sb = new StringBuilder();
    String pais;
    sb.append("<select name=\"pais\">");
    if(p!=null){
        for(int i = 0; i< paises.size();i++){
            pais = paises.get(i);
            if(u.getPais().equals(pais)){
                sb.append("\t<option selected >")
                    .append(paises.get(i))
                    .append("</option>\n");
            }else{
                sb.append("\t<option>")
                    .append(paises.get(i))
                    .append("</option>\n");
            }
            
        }
    }else{
        sb.append("\t<option>")
            .append("En construccion")
            .append("</option>\n");
    }

    sb.append("</select>");
    return sb.toString();
}
%>