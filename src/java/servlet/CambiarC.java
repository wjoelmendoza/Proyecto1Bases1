/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.conexion.User;
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
public class CambiarC extends HttpServlet {

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
        User u ;
        String cAct = request.getParameter("c_act");
        String nClave = request.getParameter("nueva_c");
        String nrClave = request.getParameter("nueva_cr");
        response.sendRedirect("/Apuestas/User/c_clave.jsp");
        if(cAct==null){
            request.getSession().setAttribute("error", "No ingreso la clave actual");
            return;
        }
        
        if(nClave==null){
            request.getSession().setAttribute("error", "No ingreso la nueva clave");
            return;
        }
        
        if(nrClave == null){
            request.getSession().setAttribute("error", "No confirmo la nueva clave");
            return;
        }
        
        if(!nrClave.equals(nClave)){
            request.getSession().setAttribute("error", "La nueva clave no coincide");
            return;
        }
        int codu =(int) request.getSession().getAttribute("id");
        u = new User();
        int tmp = u.cambiarClave(codu, cAct, nClave);
        
        if (tmp==1){
            request.getSession().setAttribute("hecho", "Se cambio la clave");
        }else{
            request.getSession().setAttribute("error", "La clave actual es incorrecta");
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
        char tipo;
        Object obj;
        
        obj = request.getSession().getAttribute("rol");
        if(obj!=null){
            tipo = (char) obj;
            if(tipo == 'A')
                response.sendRedirect("/Apuestas/Admin/AInicio.jsp");
            else if(tipo == 'C')
                response.sendRedirect("/Apuestas/User/UInicio.jsp");
        }else{
            response.sendRedirect("/Apuestas");
        }
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
        processRequest(request, response);
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
