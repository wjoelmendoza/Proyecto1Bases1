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
public class ActDatosU extends HttpServlet {

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
        String mailA =(String) request.getSession().getAttribute("mail");
        String cambio;
        String msj="/Apuestas/User/cambiard.jsp";
        int id = (int) request.getSession().getAttribute("id");
        String nombre = request.getParameter("uNombre");
        String mail = request.getParameter("uCorreo");
        String pais = request.getParameter("pais");
        String fecha = request.getParameter("uFecha");
        User u;
        
        char formato='c';
        System.out.println(fecha);
        String fp[] = fecha.split("/");
        String fp2[] = fecha.split("-");
        
        if((fp!=null && fp.length != 3) && (fp2!=null && fp2.length != 3)){
            request.getSession().setAttribute("error", "El formato de la fecha es incorrecto");
            response.sendRedirect(msj);
            return;
        }
        
        if(fp!=null && fp.length==3 )
            formato = 'a';
        
        if(fp2!=null && fp2.length==3)
            formato = 'b';
        
        if(mailA.equals(mail))
            cambio = "no";
        else
            cambio ="si";
        
        if("si".equals(cambio)){
            u = new User();
            if(!u.isDisponible(mail)){
                request.getSession().setAttribute("error", "El correo ya esta registrado");
                response.sendRedirect(msj);
                return;
            }
        }
        
        System.out.println(pais);
        u = new User(id,nombre,fecha,mail,pais);
        
        response.sendRedirect(msj);
        if(u.cambiarDatos(cambio, formato)){
            request.getSession().setAttribute("hecho", "El correo ya esta registrado");
        }else{
            request.getSession().setAttribute("error", "sucedio un problema al guardar los datos");
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
        Object id = request.getSession().getAttribute("id");
        if(id!=null)
            this.processRequest(request, response);
        else
            response.sendRedirect("/Apuestas");
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
