/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.conexion.Partido;
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
public class FormularioQ extends HttpServlet {

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
        String idreq = request.getParameter("idreq");
        Partido p;
        String grupo;
        String part;
        String salida;
        
        int codPart;
        int codUsuario =(int) request.getSession().getAttribute("id");
        if(idreq.equals("formq")){
            response.setContentType("text/html; charset=utf8");
            PrintWriter out = response.getWriter();
            grupo = request.getParameter("grupo");
            part = request.getParameter("partido");
            p = new Partido();
            codPart = Integer.parseInt(part);
            p.buscarPartido(codPart,codUsuario, grupo);
            if(p.crearMarcador()){
                salida = getFormL(p);
                out.print(salida);
            }else{
                salida = getFormD(p);
                out.print(salida);
            }
        }
    }
    
    private String getFormL(Partido p){
    StringBuilder form = new StringBuilder();
    form.append("<h3>Ingrese su Pronostico</h3>\n")
        .append("<form method='post' action='/Apuestas/LlenarMarcador'>\n")
        .append("<input name='eq1' hidden value='")
        .append(p.getRival1().getCodigo())
        .append("'>\n")
        .append("<input name='eq2' hidden value='")
        .append(p.getRival2().getCodigo())
        .append("' >\n")
        .append("<input name='part' hidden value='")
        .append(p.getCodPartido())
        .append("' > \n")
        .append("<input hidden name='crear' value='true'>\n")
        .append("<table>\n")
        .append("\t<tr>\n")
        .append("\t\t<td>")
        .append(p.getRival1().getNombre())
        .append("</td>\n\t\t<td>")
        .append("<input name='goles1' type='number' min=0 max=50 required></td>\n\t</tr>\n")
        .append("\t<tr>\n")
        .append("\t\t<td>")
        .append(p.getRival2().getNombre())
        .append("</td>\n\t\t<td>")
        .append("<input name='goles2' type='number' min=0 max=50 required></td>\n\t</tr>\n")
        .append("\t<tr>\n")
        .append("\t\t<td>\n\t\t</td>\n\t\t<td>\n")
        .append("\t\t\t<input type='submit' value='Aceptar'>\n")
        .append("\t\t\t<input type='reset' value='Cancelar'>\n\t\t<td>\n</tr>\n")
        .append("</table>\n")
        .append("</form>\t");
    return form.toString();
}

private String getFormD(Partido p){
    StringBuilder form = new StringBuilder();
    form.append("<h3>Modifique su pronostico</h3>")
        .append("<form method='post' action='/Apuestas/User/LlenarMarcador'>\n")
        .append("<input name='eq1' hidden value='")
        .append(p.getRival1().getCodigo())
        .append("'>\n")
        .append("<input name='eq2' hidden value='")
        .append(p.getRival2().getCodigo())
        .append("' >\n")
        .append("<input name='part' hidden value='")
        .append(p.getCodPartido())
        .append("' > \n")
        .append("<input hidden name='crear' value='false'>\n")
        .append("<table>\n")
        .append("\t<tr>\n")
        .append("\t\t<td>")
        .append(p.getRival1().getNombre())
        .append("</td>\n\t\t<td>")
        .append("<input type='number' min=0 max=50 value=")
        .append(p.getRival1().getGoles())
        .append(" required></td></tr>\n")
        .append("\t<tr>")
        .append("\t\t<td>")
        .append(p.getRival2().getNombre())
        .append("</td>\n\t\t<td>")
        .append("<input type='number' min=0 max=50 value=")
        .append(p.getRival2().getGoles())
        .append(" required></td></tr>\n")
        .append("\t<tr>")
        .append("\t\t<td></td>\t\t<td>\n")
        .append("\t\t\t<input type='submit'value='Aceptar'>\n")
        .append("\t\t\t<input type='reset' value='Cancelar'>\n\t\t<td>\n</tr>\n")
        .append("</table>\n")
        .append("</form>");
    return form.toString();
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
        if(oid!=null)
            processRequest(request, response);
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
