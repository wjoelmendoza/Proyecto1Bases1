/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.conexion.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wxjoy
 */
public class LoginUser extends HttpServlet {

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
          /**
             * Aqui deberia de hacer una disticion
             * entre los administradores y los usuarios normales para
             * redireccionar a sus vistas correspondientes
             */
        char rol =(char) request.getSession().getAttribute("rol");
        switch (rol) {
            case 'A':
                response.sendRedirect("/Apuestas/Admin/AInicio.jsp");
                break;
            case 'C':
                response.sendRedirect("/Apuestas/User/UInicio.jsp");
                break;
            default:
                response.sendRedirect("/Apuestas");
                break;
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
        request.getSession().setAttribute("error", "No ingreso los datos solicitados");
        response.sendRedirect("/Apuestas/login.jsp");
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
        boolean valida;
        int id;
        String nombre="";
        String correo, clave;
        correo = request.getParameter("correo");
        clave = request.getParameter("clave");
        valida = correo!= null && clave != null;
        
        if(valida){
            User u = new User();
            id = u.login(correo, clave);
            if(id >0){
                nombre = u.getNombre();
                request.getSession().setAttribute("id", id);
                request.getSession().setAttribute("nombre", nombre);
                request.getSession().setAttribute("rol", u.getRol());
                this.processRequest(request, response);
            }else{
                request.getSession().setAttribute("error", "Credenciales invalidas");
                response.sendRedirect("/Apuestas/login.jsp");
            }
        }else{
            request.getSession().setAttribute("error", "Datos invalidos");
            response.sendRedirect("/Apuestas/login.jsp");
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
