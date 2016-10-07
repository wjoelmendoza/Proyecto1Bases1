/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.conexion.Reporte;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wxjoy
 */
public class Reportes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String  idreq = request.getParameter("idreq");
        String rep = request.getParameter("rep");
        Reporte repB = new Reporte();
        if(idreq.equals("formReq")){
            switch(rep){
                case "Reporte 1":
                    out.println("<h2>Confederacion con mas Equipos</h2>");
                    out.print(this.reporte1(repB));
                    break;
                case "Reporte 2":
                    out.println("<h2>Cantidad de Equipos por Confederacion</h2>");
                    out.print(this.reporte2(repB));
                    break;
                case "Reporte 3":
                    out.println("<h2>Cantidad de Arbitros por Confederacion</h2>");
                    out.print(this.reporte3(repB));
                    break;
                case "Reporte 4":
                    out.println("<h2>Top 5 de Arbitros Centrales</h2>");
                    out.print(this.reporte4(repB));
                    break;
                case "Reporte 5":
                    out.println("<h2>Top 5 Equipos con mas Partidos Ganados</h2>");
                    out.print(this.reporte5(repB));
                    break;
                case "Reporte 6":
                    out.println("<h2>Goles a Favor y En Contra por Equipo</h2>");
                    out.println(this.reporte6(repB));
                    break;
                case "Reporte 7":
                    out.println("<h2>Promedio de edad por Equipo</h2>");
                    out.println(this.reporte7(repB));
                    break;
                case "Reporte 8":
                    out.println("<h2>Seleccion con mayor cantidad de goles</h2>");
                    out.println(this.reporte8(repB));
                    break;
                case "Reporte 9":
                    out.println("<h2>Cantidad de Goles por Confederacion</h2>");
                    out.print(this.reporte9(repB));
                    break;
                case "Reporte 10":
                    out.println("<h2>Top 5 de Partidos con mas goles</h2>");
                    out.print(this.reporte10(repB));
                    break;
                case "Reporte 11":
                    out.println("<h2>Promedio de edad de los usuarios por pais</h2>");
                    out.print(this.reporte11(repB));
                    break;
                case "Reporte 12":
                    out.println("<h2>Top 10 de las personas con mas punteos</h2>");
                    out.print(this.reporte12(repB));
                    break;
                case "Reporte 13":
                    out.println("<h2>Ciudad con mayor cantidad de partidos Asignados</h2>");
                    out.print(this.reporte13(repB));
                    break;
                case "Reporte 14":
                    out.println("<h2>Total de puntos por edad</h2>");
                    out.print(this.reporte14(repB));
                    break;
                case "Reporte 15":
                    out.println("<h2>Confederacion con mas usuarios</h2>");
                    out.print(this.reporte15(repB));
                    break;
                case "Reporte 16":
                    out.println("<h2>Total de usuarios que pagaron por quinielas</h2>");
                    out.print(this.reporte16(repB));
                    break;
                case "Reporte 17":
                    out.println("<h2>LIstado de usuarios que no pagaron</h2>");
                    out.print(this.reporte17(repB));
                    break;
                case "Reporte 18":
                    out.println("<h2>Confedarion con mayor numero de usuarios que pagaron</h2>");
                    out.print(this.reporte18(repB));
                    break;
                case "Reporte 19":
                    out.println("<h2>Equipo con menos goles en contra</h2>");
                    out.print(this.reporte19(repB));
                    break;
                case "Reporte 20":
                    out.println("<h2>Equipo mas goleado</h2>");
                    out.print(this.reporte20(repB));
                    break;
                default:
                    out.println("<h2>Desconocido</h2>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>");
        out.println("Metodo de comunicacion no Soportado");
        out.println("</h1>");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Object oid = request.getSession().getAttribute("id");
        char rol  = (char)request.getSession().getAttribute("rol");
        if(oid!=null)
            if(rol == 'A')
                processRequest(request, response);
            else
                response.sendRedirect("/Apuestas");
        else{
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<h2>");
            out.println("Su sesion ha expirado");
            out.println("</h2>");
            out.println("vuelva a iniciar sesion para ver la informacion solicitada");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private String reporte1(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] resultado = rep.reporte1();
        if(rep.esValido()){
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Codigo</th>")
                    .append("\t\t<th>Confederacion</th>")
                    .append("\t\t<th>Selecciones</th>")
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>")
                    .append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(resultado[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(resultado[1])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(resultado[2])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>")
                    .append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    
    private String reporte2(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] result;
        ArrayList<String[]> resultado;
        resultado = rep.reporte2();
        if(rep.esValido()){
            
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Codigo</th>")
                    .append("\t\t<th>Confederacion</th>")
                    .append("\t\t<th>Selecciones</th>")
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>");
            for(int i=0; i<resultado.size(); i++){
                result = resultado.get(i);
                srep.append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(result[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[1])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[2])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>");
            }
            
            srep.append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    
    private String reporte3(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] result;
        ArrayList<String[]> resultado;
        resultado = rep.reporte3();
        if(rep.esValido()){
            
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Codigo</th>")
                    .append("\t\t<th>Confederacion</th>")
                    .append("\t\t<th>Arbitros</th>")
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>");
            for(int i=0; i<resultado.size(); i++){
                result = resultado.get(i);
                srep.append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(result[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[1])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[2])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>");
            }
            
            srep.append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    
    private String reporte4(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] result;
        ArrayList<String[]> resultado;
        resultado = rep.reporte4();
        if(rep.esValido()){
            
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Codigo</th>")
                    .append("\t\t<th>Nombre</th>")
                    .append("\t\t<th>Participaciones</th>")
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>");
            for(int i=0; i<resultado.size(); i++){
                result = resultado.get(i);
                srep.append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(result[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[1])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[2])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>");
            }
            
            srep.append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    
    private String reporte5(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] result;
        ArrayList<String[]> resultado;
        resultado = rep.reporte5();
        if(rep.esValido()){
            
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Codigo</th>")
                    .append("\t\t<th>Nombre</th>")
                    .append("\t\t<th>Ganados</th>")
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>");
            for(int i=0; i<resultado.size(); i++){
                result = resultado.get(i);
                srep.append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(result[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[1])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[2])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>");
            }
            
            srep.append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    
    private String reporte6(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] result;
        ArrayList<String[]> resultado;
        resultado = rep.reporte6();
        if(rep.esValido()){
            
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Codigo</th>")
                    .append("\t\t<th>Nombre</th>")
                    .append("\t\t<th>GF</th>")
                    .append("\t\t<th>GC</th>")                    
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>");
            for(int i=0; i<resultado.size(); i++){
                result = resultado.get(i);
                srep.append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(result[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[1])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[2])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[3])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>");
            }
            
            srep.append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    
    private String reporte7(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] result;
        ArrayList<String[]> resultado;
        resultado = rep.reporte7();
        if(rep.esValido()){
            
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Codigo</th>")
                    .append("\t\t<th>Nombre</th>")
                    .append("\t\t<th>Promedio</th>")
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>");
            for(int i=0; i<resultado.size(); i++){
                result = resultado.get(i);
                srep.append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(result[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[1])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[2])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>");
            }
            
            srep.append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    
    private String reporte8(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] resultado = rep.reporte8();
        if(rep.esValido()){
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Codigo</th>")
                    .append("\t\t<th>Nombre</th>")
                    .append("\t\t<th>Goles</th>")
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>")
                    .append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(resultado[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(resultado[1])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(resultado[2])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>")
                    .append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    
    private String reporte9(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] result;
        ArrayList<String[]> resultado;
        resultado = rep.reporte9();
        if(rep.esValido()){
            
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Codigo</th>")
                    .append("\t\t<th>Confederacion</th>")
                    .append("\t\t<th>Goles</th>")
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>");
            for(int i=0; i<resultado.size(); i++){
                result = resultado.get(i);
                srep.append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(result[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[1])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[2])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>");
            }
            
            srep.append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    
    private String reporte10(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] result;
        ArrayList<String[]> resultado;
        resultado = rep.reporte10();
        if(rep.esValido()){
            
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Codigo</th>")
                    .append("\t\t<th>Fecha/Hora</th>")
                    .append("\t\t<th>Ciudad</th>")
                    .append("\t\t<th>Goles</th>")                    
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>");
            for(int i=0; i<resultado.size(); i++){
                result = resultado.get(i);
                srep.append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(result[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[1])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[2])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[3])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>");
            }
            
            srep.append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    
    private String reporte11(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] result;
        ArrayList<String[]> resultado;
        resultado = rep.reporte11();
        if(rep.esValido()){
            
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Nombre</th>")
                    .append("\t\t<th>Edad</th>")
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>");
            for(int i=0; i<resultado.size(); i++){
                result = resultado.get(i);
                srep.append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(result[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[1])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>");
            }
            
            srep.append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    private String reporte12(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] result;
        ArrayList<String[]> resultado;
        resultado = rep.reporte12();
        if(rep.esValido()){
            
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Codigo</th>")
                    .append("\t\t<th>Nombre</th>")
                    .append("\t\t<th>Puntos</th>")
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>");
            for(int i=0; i<resultado.size(); i++){
                result = resultado.get(i);
                srep.append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(result[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[1])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[2])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>");
            }
            
            srep.append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    private String reporte13(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] result;
        ArrayList<String[]> resultado;
        resultado = rep.reporte13();
        if(rep.esValido()){
            
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Codigo</th>")
                    .append("\t\t<th>Nombre Ciudad</th>")
                    .append("\t\t<th>Cantidad Partidos</th>")
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>");
            for(int i=0; i<resultado.size(); i++){
                result = resultado.get(i);
                srep.append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(result[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[1])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[2])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>");
            }
            
            srep.append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    private String reporte14(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] result;
        ArrayList<String[]> resultado;
        resultado = rep.reporte14();
        if(rep.esValido()){
            
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Edad</th>")
                    .append("\t\t<th>Puntos</th>")
                    
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>");
            for(int i=0; i<resultado.size(); i++){
                result = resultado.get(i);
                srep.append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(result[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[1])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>");
            }
            
            srep.append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    
    private String reporte15(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] result;
        ArrayList<String[]> resultado;
        resultado = rep.reporte15();
        if(rep.esValido()){
            
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Codigo</th>")
                    .append("\t\t<th>Nombre</th>")
                    .append("\t\t<th>Cantidad usuarios</th>")
                    
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>");
            for(int i=0; i<resultado.size(); i++){
                result = resultado.get(i);
                srep.append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(result[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[1])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[2])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>");
            }
            
            srep.append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    
    private String reporte16(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] result;
        ArrayList<String[]> resultado;
        resultado = rep.reporte16();
        if(rep.esValido()){
            
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Cantidad de Usuarios</th>")
                    
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>");
            for(int i=0; i<resultado.size(); i++){
                result = resultado.get(i);
                srep.append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(result[0])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>");
            }
            
            srep.append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    private String reporte17(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] result;
        ArrayList<String[]> resultado;
        resultado = rep.reporte17();
        if(rep.esValido()){
            
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Codigo</th>")
                    .append("\t\t<th>Nombre</th>")
                    
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>");
            for(int i=0; i<resultado.size(); i++){
                result = resultado.get(i);
                srep.append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(result[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[1])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>");
            }
            
            srep.append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    
    private String reporte18(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] result;
        ArrayList<String[]> resultado;
        resultado = rep.reporte18();
        if(rep.esValido()){
            
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Codigo</th>")
                    .append("\t\t<th>Nombre Confederacion</th>")
                    .append("\t\t<th>Cantidad de Usuarios</th>")
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>");
            for(int i=0; i<resultado.size(); i++){
                result = resultado.get(i);
                srep.append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(result[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[1])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[2])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>");
            }
            
            srep.append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    private String reporte19(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] result;
        ArrayList<String[]> resultado;
        resultado = rep.reporte19();
        if(rep.esValido()){
            
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Codigo</th>")
                    .append("\t\t<th>Nombre</th>")
                    .append("\t\t<th>Goles en contra</th>")
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>");
            for(int i=0; i<resultado.size(); i++){
                result = resultado.get(i);
                srep.append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(result[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[1])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[2])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>");
            }
            
            srep.append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    private String reporte20(Reporte rep){
        StringBuilder srep = new StringBuilder();
        String[] result;
        ArrayList<String[]> resultado;
        resultado = rep.reporte20();
        if(rep.esValido()){
            
            srep.append("<table class='table table-striped'>")
                    .append("\t<thead>\n")
                    .append("<tr>")
                    .append("\t\t<th>Codigo</th>")
                    .append("\t\t<th>Nombre</th>")
                    .append("\t\t<th>Goles en contra</th>")
                    .append("\t</thead>")
                    .append("\t</tr>\n\t<tbody>");
            for(int i=0; i<resultado.size(); i++){
                result = resultado.get(i);
                srep.append("\t\t<tr>")
                    .append("\t\t<td>")
                    .append(result[0])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[1])
                    .append("\t\t</td/>")
                    .append("\t\t<td>")
                    .append(result[2])
                    .append("\t\t</td/>")
                    .append("\t\t</tr>");
            }
            
            srep.append("\t</tbody>")
                    .append("</table>");
        }else{
            srep.append("Ha Ocurrido un problema recuperando los datos.");
        }
        return srep.toString();
    }
    
    
}
