/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.conexion.Confederacion;
import com.conexion.Equipo;
import com.conexion.Pais;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author byron
 */
@WebServlet(name = "ACrear", urlPatterns = {"/ACrear"})
public class ACrear extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     * 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response,String var)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ACrear "+var+" </title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ACrear at " + request.getContextPath() +"........"+var+ "</h1>");
            out.println("</body>");
            out.println("</html>");
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
       // processRequest(request, response,"GET");
        
            //String pagina = "/Admin/Creacion_Admin.jsp";
            //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
            //dispatcher.forward(request, response);
            //response.sendRedirect("/Apuestas/Admin/Creacion_Admin.jsp");
            response.sendRedirect("/Apuestas/Admin/EM.jsp");
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
        System.out.println("LLEGO AL POST DESDE AJAX");
        String flag = request.getParameter("flaa");
        System.out.println(flag);
        if(flag.equals("confederacion"))
        {
            System.out.println("ENTROO");
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            out.println("<h3>RESPUESTA DESDE aCrear,u did it</h3>");
            


        }
        else if(flag.equals("lconfederas"))
        {
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            //out.println(" <select id=\"selectconfederas\" name=\"selectconfederas\" multiple=\"multiple\">");
            
            Confederacion confederacion = new Confederacion();
            ArrayList<Confederacion> lista =  confederacion.getConfederaciones();
            Iterator<Confederacion> it =  lista.iterator();
            
            while(it.hasNext())
            {
                Confederacion conf = it.next();
                out.println("<option value="+conf.getCod()+">"+conf.getNombre()+"</option>");
            }
            //out.println(" </select>");
        }
        else if(flag.equals("confederainfo"))
        {
            
            int cod = Integer.valueOf(request.getParameter("codconfe"));
            System.out.println("CODIGO DE CONFEDERACION: "+cod);
            Confederacion confee = new Confederacion();
            Confederacion confe = confee.getconfederacion(cod);
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            out.println("<form id=\"formconfedera\">");
            out.println("<table cellpadding=\"5\" >");
            out.println("       <tr>");
            out.println("           <td>Nombre</td>");
            out.println("           <td><input name=\"nombre\" type=\"text\" value=\""+confe.getNombre()+"\" id=\"txtnomconfe\"></td>");
            out.println("        </tr>");
            
            out.println("       <tr>");
            out.println("           <td>Acronimo</td>");
            out.println("           <td><input name=\"acronimo\" type=\"text\" value=\""+confe.getAcronimo()+"\" id=\"txtacroconfe\"></td>");
            out.println("        </tr>");
            
            
            out.println("       <tr>");
            out.println("           <td></td>");
            out.println("           <td>");
            out.println("               <input value=\"Modificar\" class=\"btn btn-success\" type=\"button\" id=\"btnmodconfe\">");
            out.println("               <input value=\"Eliminar\" class=\"btn btn-danger\" type=\"button\"  id=\"btnelimconfe\">");
            out.println("           </td>");
            out.println("       </tr>");
            out.println("</table>");
            out.println("</form>");
            
        }
        else if(flag.equals("confederaelim"))
        {
            
        }
        else if(flag.equals("confederamodi"))
        {
            
        }
        
        
        
        
        else if(flag.equals("lpaisesdispo"))
        {
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            //out.println(" <select id=\"selectconfederas\" name=\"selectconfederas\" multiple=\"multiple\">");
            
            Pais pais = new Pais();
            ArrayList<Pais> lista =  pais.getPaisesDisponibles();
            Iterator<Pais> it =  lista.iterator();
            
            while(it.hasNext())
            {
                Pais conf = it.next();
                out.println("<option value="+conf.getCodpais()+">"+conf.getNombre()+"</option>");
            }
        }
        else if(flag.equals("submitseles"))
        {
            System.out.println("ENTRO A submitseles");
            String codp = request.getParameter("codp");
            String group = request.getParameter("grup");
            String dir = request.getParameter("dir");
            System.out.println(codp+","+group+","+dir);
            Equipo eq = new Equipo();
            String mess = eq.SetEquipo(Integer.valueOf(codp),group, dir);
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            out.println(mess);
        }
        else if(flag.equals("creapartidos"))
        {
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            System.out.println("ENTRO A creapartidos");
            String group = request.getParameter("grupo");
            System.out.println("GRUPO SELECCIONADO: "+group.charAt(0)+".");
            Equipo equipo = new Equipo();
            ArrayList<Equipo> lista = equipo.getEquiposGrupo(group.charAt(0));//devuelve una lista de equipos
            StringBuilder builder = new StringBuilder();
            System.out.println("tam de equipos: "+lista.size());
            //out.write("HOLA DESDE OUT");
            //builder.append("HOLA QUE RARO ESTO");
           
           
            
                    /*
                    out.println("<table align=\"center\">");
                    out.println("<tr>");
                    out.println("<th>");
                    out.println("<input value=\""+lista.get(0).getCodEquipo()+"\" type=\"hidden\" >");
                    out.println("</th>");
                    out.println("<th></th>");
                    out.println("<th>");
                    out.println("<input value=\""+lista.get(1).getCodEquipo()+"\" type=\"hidden\">");
                    out.println("</th>");
                    out.println(" </tr>");
                    out.println("<tr>");
                    out.println("<th><h3>"+lista.get(0).getPais()+"</h3></th>");
                    out.println("<th></th>");
                    out.println("<th><h3>"+lista.get(1).getPais()+"</h3></th>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td rowspan=\"2\">");
                    out.println("<input value=\"0\" size=\"2\">");
                    out.println("</td>");
                    out.println(" <td></td>");
                    out.println("<td rowspan=\"2\">");
                    out.println("<input value=\"0\" size=\"2\">");
                    out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td></td>"); 
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    out.println("Fecha: <input type=\"date\">");
                    out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    out.println("Hora: <input type=\"time\">");
                    out.println("</td>");
                    out.println("</tr>");
                    out.println("</table>");
                    out.println("</div>");
                    out.println("</li>");
            */
            out.println("<table align=\"left\">");
            out.println("<tr>");
            
            
            
            int x=0;
            
            for(int z=0;z<lista.size()-1;z++)//ciclos de pasada con el mismo indice
            {
                int y=x;
                for(;x<lista.size()-1;x++)//ciclos de pasada con el mismo indice
                {
                    System.out.println(z+","+x);
                    out.println("<th>");
                    out.println("<input class=\"btn btn-success\" id=\"btnz"+lista.get(z).getCodEquipo()+"z"+lista.get(x+1).getCodEquipo()+"z\" type=\"button\" value=\""+lista.get(z).getPais()+"vrs"+lista.get(x+1).getPais()+"\" style=\"font-size : 11px; width:100%; height:30px;>\" onclick=\"javascript:jsbtnpartido('"+lista.get(z).getCodEquipo()+"','"+lista.get(x+1).getCodEquipo()+"','"+group.charAt(0)+"');\"");
                    out.println("</th>");
                /*
                    out.println("<li>");
                    out.println("<div>");
                    
                    out.println("<table align=\"center\">");
                    out.println("<tr>");
                    out.println("<th>");
                    out.println("<input value=\""+lista.get(z).getCodEquipo()+"\" type=\"hidden\" >");
                    out.println("</th>");
                    out.println("<th></th>");
                    out.println("<th>");
                    out.println("<input value=\""+lista.get(x+1).getCodEquipo()+"\" type=\"hidden\">");
                    out.println("</th>");
                    out.println(" </tr>");
                    out.println("<tr>");
                    out.println("<th><h3>"+lista.get(z).getPais()+"</h3></th>");
                    out.println("<th></th>");
                    out.println("<th><h3>"+lista.get(x+1).getPais()+"</h3></th>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td rowspan=\"2\">");
                    out.println("<input value=\"0\" size=\"2\">");
                    out.println("</td>");
                    out.println(" <td></td>");
                    out.println("<td rowspan=\"2\">");
                    out.println("<input value=\"0\" size=\"2\">");
                    out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td></td>"); 
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    out.println("Fecha: <input type=\"date\">");
                    out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    out.println("Hora: <input type=\"time\">");
                    out.println("</td>");
                    out.println("</tr>");
                    out.println("</table>");
                    out.println("</div>");
                    out.println("</li>");
                */
                }
                
                x=y;
                x++;
                
            }
            
            //System.out.println(builder.toString());
            //out.println(builder.toString());
            out.println("<tr>");
            out.println("</table>");
            
        
        }
        else if(flag.equals("creapartidos2"))
        {
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            System.out.println("ENTRO A creapartidos2");
            String group = request.getParameter("grupo");
            String code1 = request.getParameter("codeq1");
            String code2 = request.getParameter("codeq2");
            
            Equipo equipo1 = new Equipo();
            equipo1.buscarEquipo(group,Integer.valueOf(code1));
            Equipo equipo2 = new Equipo();
            equipo2.buscarEquipo(group,Integer.valueOf(code2));
            
            
            
                    out.println("<table align=\"center\" id=\"tablainfopartido\"> ");
                    out.println("<tr>");
                    out.println("<th>");
                    out.println("<input value=\""+equipo1.getCodEquipo()+"\" type=\"hidden\" id=\"txtcodq1\">");
                    out.println("</th>");
                    out.println("<th></th>");
                    out.println("<th>");
                    out.println("<input value=\""+equipo2.getCodEquipo()+"\" type=\"hidden\" id=\"txtcodq1\">");
                    out.println("</th>");
                    out.println(" </tr>");
                    out.println("<tr>");
                    out.println("<th><h3>"+equipo1.getPais()+"</h3></th>");
                    out.println("<th></th>");
                    out.println("<th><h3>"+equipo2.getPais()+"</h3></th>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td rowspan=\"2\">");
                    out.println("<input value=\"0\" size=\"2\" id=\"txtgolq1\">");
                    out.println("</td>");
                    out.println(" <td></td>");
                    out.println("<td rowspan=\"2\">");
                    out.println("<input value=\"0\" size=\"2\" id=\"txtgolq1\">");
                    out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td></td>"); 
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    out.println("Fecha: <input type=\"date\" id=\"txtfecpartido\">");
                    out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    out.println("Hora: <input type=\"time\" id=\"txthorapartido\">");
                    out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    out.println("--------");
                    out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    out.println("<input class=\"btn btn-success\" id=\"btngrabarpartido\" type=\"button\" value=\"Guardar\" style=\"font-size : 11px; width:100%; height:30px;>\" onclick=\"javascript:jsGrabarPartido();\"");
                    out.println("</td>");
                    out.println("</tr>");
                                      
                    
                    out.println("</table>");
                    
            
            
            
            
        }
        
        
        
        else
        {
            System.out.println("");
            processRequest(request, response,"sdsd");
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

}
