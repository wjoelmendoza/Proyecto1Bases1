/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.conexion.Arbitro;
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
            String nombreconf = request.getParameter("nombre");
            String acroconf = request.getParameter("acronimo");
            Confederacion confe = new Confederacion();
            confe.set_confederacion(nombreconf, acroconf);
            
            out.println("Confederacion creada satisfactoriamente");
            


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
            out.println("           <td><input name=\"nombre\" type=\"text\" value=\""+confe.getNombre()+"\" id=\"txtnombreconf\"></td>");
            out.println("        </tr>");
            
            out.println("       <tr>");
            out.println("           <td>Acronimo</td>");
            out.println("           <td><input name=\"acronimo\" type=\"text\" value=\""+confe.getAcronimo()+"\" id=\"txtacroconfe\"></td>");
            out.println("        </tr>");
            
            
            out.println("       <tr>");
            out.println("           <td></td>");
            out.println("           <td>");
            out.println("               <input value=\"Modificar\" class=\"btn btn-success\" type=\"button\" id=\"btnmodconfe\"  onclick=\"javascript:jsMEconfe('M');\" >");
            out.println("               <input value=\"Eliminar\" class=\"btn btn-danger\" type=\"button\"  id=\"btnelimconfe\" onclick=\"javascript:jsMEconfe('E');\">");
            out.println("           </td>");
            out.println("       </tr>");
            out.println("</table>");
            out.println("</form>");
            
        }
        else if(flag.equals("Econfe"))
        {
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            //out.println(" <select id=\"selectconfederas\" name=\"selectconfederas\" multiple=\"multiple\">");
            int cod = Integer.valueOf(request.getParameter("codeconfe"));
            Confederacion confederacion = new Confederacion();
            confederacion.Elim_confederacion(cod);
            out.println("Confederacion Eliminada");
            
            
        }
        else if(flag.equals("Mconfe"))
        {
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            //out.println(" <select id=\"selectconfederas\" name=\"selectconfederas\" multiple=\"multiple\">");
            int cod = Integer.valueOf(request.getParameter("codeconfe"));
            String nomconfe = request.getParameter("nomconfe");
            String acroconfe = request.getParameter("acroconfe");
            System.out.println(""+nomconfe+","+acroconfe+","+cod);
            Confederacion confederacion = new Confederacion();
            confederacion.Mod_confederacion(cod,nomconfe,acroconfe);
            out.println("Confederacion Eliminada");
            
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
        
        else if(flag.equals("lpaises"))
        {
            System.out.println("ENTRO A lpaises");
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            Pais pais = new Pais();
            ArrayList<Pais> lista = pais.getPaisesConCod();
            Iterator<Pais> it = lista.iterator();
            while(it.hasNext())
            {
                Pais p = it.next();
                out.println("<option value=\""+p.getCodpais()+"\">"+p.getNombre()+"</option>");
            }
        }
        else if(flag.equals("submitarbitro"))
        {
            System.out.println("ENTRO A submitarbitro");
            String nombre = request.getParameter("nombre");
            int codigop = Integer.valueOf(request.getParameter("codigop"));
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            out.println("Arbitro creado");
            
        }
        else if(flag.equals("Earbitro"))
        {
            System.out.println("ENTRO A Earbitro");
            int coda = Integer.valueOf(request.getParameter("codarbitro"));
            Arbitro arbi = new Arbitro();
            arbi.EArbitro(coda);
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            out.println("Arbitro eliminado");
            
        }
        else if(flag.equals("Marbitro"))
        {
            System.out.println("ENTRO A Marbitro");
            int coda = Integer.valueOf(request.getParameter("codarbitro"));
            String nom = request.getParameter("nombre");
            int cop = Integer.valueOf(request.getParameter("codpais"));
            Arbitro arbi = new Arbitro();
            arbi.MArbitro(coda, nom, cop);
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            out.println("Arbitro Modificado");
        }
        else if(flag.equals("larbitros"))
        {
            System.out.println("ENTRO A larbitros");
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            Arbitro arbitro = new Arbitro();
            ArrayList<Arbitro> lista = arbitro.get_arbitros();
            Iterator<Arbitro> it = lista.iterator();
            while(it.hasNext())
            {
                Arbitro temp = it.next();
                out.println("<option value=\""+temp.getCod()+"\">"+temp.getNombre()+"</option>");
            }
        }
        else if(flag.equals("infoarbitro"))
        {
            System.out.println("ENTRO A infoarbitro");
            //Se obtiene la info del arbitro
            int coda = Integer.valueOf(request.getParameter("codigoarbitro"));
            Arbitro arbitro = new Arbitro();
            arbitro.get_arbitro(coda);
            
            //SE EMPIEZA A ARMAR LA TABLA EN DONDE SE VAN A PRESENTAR ESTOS DATOS
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            
            out.println("<form id=\"formarbitro\">");
            out.println("<table cellpadding=\"5\" >");
            out.println("       <tr>");
            out.println("           <td>Nombre:</td>");
            out.println("           <td><input name=\"nombre\" type=\"text\" value=\""+arbitro.getNombre()+"\" id=\"txtnombrearbitro\"></td>");
            out.println("        </tr>");
            
            out.println("       <tr>");
            out.println("           <td><input name=\"codarbitro\" type=\"hidden\" value=\""+arbitro.getCod()+"\" id=\"txtcodarbitro\"></td>");
            out.println("        </tr>");
            out.println("       <tr>");
            out.println("<td>");
            out.println("<select id=\"selectarbpaiact\">");
            //se obtiene la lista de paises Y se deja como seleccionado el pais que tiene actualmente
            Pais pais = new Pais();
            ArrayList<Pais> lista = pais.getPaisesConCod();
            Iterator<Pais> it = lista.iterator();
            while(it.hasNext())
            {
                Pais p = it.next();
                if(p.getCodpais()==arbitro.getCodpais())
                    out.println("<option value=\""+p.getCodpais()+"\" selected>"+p.getNombre()+"</option>");
                else
                    out.println("<option value=\""+p.getCodpais()+"\" >"+p.getNombre()+"</option>");
            }
            out.println("</select>");
            out.println("</td>");        
            out.println("        </tr>");
             out.println("       <tr>");
            out.println("           <td></td>");
            out.println("           <td>");
            out.println("               <input value=\"Modificar\" class=\"btn btn-success\" type=\"button\" id=\"btnmodarbitro\"  onclick=\"javascript:jsMEarbitro('M');\" >");
            out.println("               <input value=\"Eliminar\" class=\"btn btn-danger\" type=\"button\"  id=\"btnelimarbitro\" onclick=\"javascript:jsMEarbitro('E');\">");
            out.println("           </td>");
            out.println("       </tr>");
            out.println("        </table>");
            out.println("        </form>");
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
