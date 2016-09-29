/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.conexion.Equipo;
import com.conexion.Jugador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author byron
 */
@WebServlet(name = "jscliente", urlPatterns = {"/jscliente"})
public class jscliente extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet jscliente</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet jscliente at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        //processRequest(request, response);
        
        String flag = request.getParameter("flaa");
        System.out.println(flag);
        if(flag.equals("hrefplantillas"))
        {
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            System.out.println("LLEGO...href");
            String grp = request.getParameter("grupo");
            String code = request.getParameter("codequipo");
            Equipo e = new Equipo();
            e.buscarEquipo(grp,Integer.valueOf(code));
            System.out.println("PAIS:"+e.getPais());
            
            out.println("<h3>Equipo: ");
            out.println(e.getPais());
            out.println("</h3>");
            out.println("<h3>");
            out.println("Director tecnico: ");
            out.println(e.getDirector());
            out.println("</h3>");
            out.println("<table class='table table-striped'>\n");
            out.println("<thead>\n");
            out.println("<tr>\n");
            out.println("<th>\nCamiseta\n</th>\n");
            out.println("<th>\nNombre\n</th>\n");
            out.println("<th>\nPosicion\n</th>\n</thead>\n<tbody>");
            
            ArrayList<Jugador> jugadores;
            jugadores = e.getJugadores();
            Jugador j;
            for(int i =0; i<jugadores.size(); i++){
                j = jugadores.get(i);
                out.println("<tr>\n");
                out.println("<td>");
                out.println(j.getCamiseta());
                out.println("</td>\n");
                out.println("<td>");
                out.println(j.getNombre());
                out.println("</td>\n");
                out.println("<td>posicion</td>\n");
                out.println("</tr>\n");
                 System.out.println("EN EL FOR");
    }
            out.println("</tbody>\n</table");
            System.out.println("SALIO DEL FOR");

   
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
