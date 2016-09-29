/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.conexion.Partido;
import com.conexion.Rival;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wxjoy
 */
public class LlenarMarcador extends HttpServlet {

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
       int oid=(int) request.getSession().getAttribute("id");
       String codEq1 = request.getParameter("eq1");
       String codEq2 = request.getParameter("eq2");
       String codPart = request.getParameter("part");
       String crear = request.getParameter("crear");
       String goles1 = request.getParameter("goles1");
       String goles2 = request.getParameter("goles2");
       
       /**
        * falta verificar que todos los datos esten llegando
       **/
       System.out.println(codPart);
       Rival r1 = new Rival(Integer.parseInt(codEq1),Integer.parseInt(goles1));
       Rival r2 = new Rival(Integer.parseInt(codEq2),Integer.parseInt(goles2));
       
       Partido p = new Partido(r1,r2,Integer.parseInt(codPart),oid,Boolean.parseBoolean(crear));
       char rol =(char) request.getSession().getAttribute("rol");
       if(rol == 'A')
            p.guardarPartido("R");
       else
           p.guardarPartido("P");
           
       
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
        response.sendRedirect("/Apuestas");
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
        if(oid!=null){
            processRequest(request, response);
        }else{
            response.sendRedirect("/Apuestas");
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
