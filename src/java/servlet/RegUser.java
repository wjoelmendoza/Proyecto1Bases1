/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.conexion.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author wxjoy
 */
public class RegUser extends HttpServlet {

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
            out.println("<title>Servlet RegUser</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegUser at " + request.getContextPath() + "</h1>");
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
        request.getSession().setAttribute("error", "Debe de llenar los campos solicitados");
        String error = "/Apuestas/reg.jsp";
        response.sendRedirect(error);
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
        
        String msj = "/Apuestas/reg.jsp";
        String clave = request.getParameter("clave");
        System.out.println(clave);
        String reclave = request.getParameter("reclave");
        if(!clave.equals(reclave)){
            request.getSession().setAttribute("error","Las claves no coinciden");
            response.sendRedirect(msj);
            return;
        }
        
        String correo = request.getParameter("correo");
        
        if(correo == null){
            request.getSession().setAttribute("error","No ingreso el correo");
            response.sendRedirect(msj);
            return;
        }
        
        String nombre = request.getParameter("nombre");
        
        if(nombre==null){
            request.getSession().setAttribute(nombre,"No ingreso+nombre");
            response.sendRedirect(msj);
            return;
        }
        
        String fecha = request.getParameter("fdn");
        if(fecha == null){
            request.getSession().setAttribute("error", "No ingreso fecha de nacimiento");
            response.sendRedirect(msj);
            return;
        }
        String fp[] = fecha.split("/");
        String fp2[] = fecha.split("-");
        int ffecha =0;
        
        if((fp!=null && fp.length != 3) && (fp2!=null && fp2.length != 3)){
            request.getSession().setAttribute("error", "El formato de la fecha es incorrecto");
            response.sendRedirect(msj);
            return;
        }
        
        if( fp != null && fp.length == 3 )
            ffecha = 1;
        else if(fp2 != null && fp2.length == 3)
            ffecha = 2;
        
        String pais = request.getParameter("pais");
        
        if(pais == null || pais.equals("En construccion")){
            request.getSession().setAttribute("error", "No se reconoce el pais");
            response.sendRedirect(msj);
            return;
        }
        String p = request.getParameter("pago");
        char pago;
        
        System.out.println(p);
        
        if(p==null || !p.equals("Si") && !p.equals("No")){
            request.getSession().setAttribute("error", "Se desconoce el pago");
            response.sendRedirect(msj);
            return;
        }else{
            pago = p.charAt(0);
        }
        
        
        User u = new User();
        if(u.isDisponible(correo)){
            u.nuevoUsuario(nombre, fecha, correo, pago, clave, pais,ffecha);
            request.getSession().setAttribute("msj", "Se ha creado su usuario");
            response.sendRedirect("/Apuestas");
        }else{
            request.getSession().setAttribute("error", "El correo: " + correo+" ya se encuentra registrado");
            response.sendRedirect(msj);
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
