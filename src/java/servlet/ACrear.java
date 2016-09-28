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
            PrintWriter out = response.getWriter();
            out.println(mess);
        }
        else if(flag.equals("getpartidos"))
        {
            
        }
        
        
        
        
        else
        {
            System.out.println("");
            //processRequest(request, response,flag+nomnom);
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
