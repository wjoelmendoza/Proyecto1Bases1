/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.conexion.Arbitro;
import com.conexion.Ciudad;
import com.conexion.Confederacion;
import com.conexion.Equipo;
import com.conexion.Jugador;
import com.conexion.Pais;
import com.conexion.Partido;
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
        else if(flag.equals("lselecciones"))
        {
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            Equipo eq = new Equipo();
            ArrayList<Equipo> lista = eq.get_Selecciones();
            Iterator<Equipo> it = lista.iterator();
            while(it.hasNext())
            {
                Equipo eqq = it.next();
                out.println("<option value=\""+eqq.getCodEquipo()+"\">"+eqq.getPais()+"</option>");
                
            }
        }
        else if(flag.equals("infoseleccion"))
        {
            int codeq = Integer.valueOf(request.getParameter("codseleccion"));
            System.out.println("CODIGO DE equipo: "+codeq);
            Equipo eq = new Equipo();
            eq.get_infoEquipo(codeq);
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            out.println("<form id=\"formseleccion\">");
            out.println("<table cellpadding=\"5\" >");
            
            out.println("       <tr>");
            out.println("           <td>Nombre:</td>");
            out.println("           <td><input name=\"nombre\" type=\"text\" value=\""+eq.getPais()+"\" id=\"txtnombreseleccion\"></td>");
            out.println("        </tr>");
            
            out.println("       <tr>");
            out.println("           <td>Director</td>");
            out.println("           <td><input name=\"acronimo\" type=\"text\" value=\""+eq.getDirector()+"\" id=\"txtdirector\"></td>");
            out.println("        </tr>");
            
            out.println("       <tr>");
            out.println("           <td>Grupo</td>");
            out.println("           <td>");
            out.println("<select id=selectgrrrequipo>");
                if(eq.getGrupo().charAt(0)=='A')
                    out.println("<option value='A' selected>A</option>");
                else if(eq.getGrupo().charAt(0)=='B')
                    out.println("<option value='B' selected>B</option>");
                else if(eq.getGrupo().charAt(0)=='C')
                    out.println("<option value='C' selected>C</option>");
                if(eq.getGrupo().charAt(0)=='D')
                    out.println("<option value='D' selected>D</option>");
                if(eq.getGrupo().charAt(0)=='E')
                    out.println("<option value='E' selected>E</option>");
                if(eq.getGrupo().charAt(0)=='F')
                    out.println("<option value='F' selected>F</option>");
                if(eq.getGrupo().charAt(0)=='G')
                    out.println("<option value='G' selected>G</option>");
                if(eq.getGrupo().charAt(0)=='H')
                    out.println("<option value='H' selected>H</option>");
                /*
                if(eq.getGrupo().charAt(0)=='A')
                    out.println("<option value='A' selected>A</option>");
                else
                    out.println("<option value='A'>A</option>");
                
                if(eq.getGrupo().charAt(0)=='B')
                    out.println("<option value='B' selected>B</option>");
                else
                    out.println("<option value='B'>B</option>");
                
                if(eq.getGrupo().charAt(0)=='C')
                    out.println("<option value='C' selected>C</option>");
                else
                    out.println("<option value='C'>C</option>");
                
                if(eq.getGrupo().charAt(0)=='D')
                    out.println("<option value='D' selected>D</option>");
                else
                    out.println("<option value='D'>D</option>");
                
                if(eq.getGrupo().charAt(0)=='E')
                    out.println("<option value='E' selected>E</option>");
                else
                    out.println("<option value='E'>E</option>");
                if(eq.getGrupo().charAt(0)=='F')
                    out.println("<option value='F' selected>F</option>");
                else
                    out.println("<option value='F'>F</option>");
                
                if(eq.getGrupo().charAt(0)=='G')
                    out.println("<option value='G' selected>G</option>");
                else
                    out.println("<option value='G'>G</option>");
                
                if(eq.getGrupo().charAt(0)=='H')
                    out.println("<option value='H' selected>H</option>");
                else
                    out.println("<option value='H'>H</option>");
            */
            out.println("</select>");
            out.println("           </td>");
            out.println("        </tr>");
            
            out.println("       <tr>");
            out.println("           <td></td>");
            out.println("           <td>");
            out.println("               <input value=\"Modificar\" class=\"btn btn-success\" type=\"button\" id=\"btnmodseleccion\"  onclick=\"javascript:jsMEseleccion('M');\" >");
            out.println("               <input value=\"Eliminar\" class=\"btn btn-danger\" type=\"button\"  id=\"btnelimseleccion\" onclick=\"javascript:jsMEseleccion('E');\">");
            out.println("           </td>");
            out.println("       </tr>");
            out.println("</table>");
            out.println("</form>");
        }
        else if(flag.equals("Eseleccion"))
        {
            System.out.println("ENTRO A Eseleccion");
            int codeq = Integer.valueOf(request.getParameter("codeq"));
            Equipo eq = new Equipo();
            eq.E_Equipo(codeq);
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            out.println("Seleccion eliminada correctamente");
            
        }
        else if(flag.equals("Mseleccion"))
        {
            System.out.println("ENTRO A Mseleccion");
            int codeq = Integer.valueOf(request.getParameter("codeq"));
            String director = request.getParameter("director");
            String grupo = request.getParameter("grupo");
            Equipo eq = new Equipo();
            String mess = eq.M_Equipo(codeq, director, grupo);
            System.out.println("mensaje en Mseleccion: "+mess);
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
            int code1 = Integer.valueOf(request.getParameter("codeq1"));
            int code2 = Integer.valueOf(request.getParameter("codeq2"));
            
            
            Equipo equipo1 = new Equipo();
            equipo1.buscarEquipo(group,Integer.valueOf(code1));
            Equipo equipo2 = new Equipo();
            equipo2.buscarEquipo(group,Integer.valueOf(code2));
            
            //SE VA A TRAER LA INFORMACION DEL POSIBLE PARTIDO DE LOS EQUIPO..
            
            Partido partido = new Partido();
            partido.get_InfoPartido(code1,code2);
            //System.out.println(""+partido.getFecha());
            if(partido.mensaje.equals("SI"))
            {
                //SI TIENE UN PARTIDO YA CREADO
                Ciudad ciudad = new Ciudad();
                ciudad.get_ciudad_partido(partido.getCodciudad());
                partido.get_marcador_partido(partido.getCodPartido());
                
                out.println("<table align=\"center\" id=\"tablainfopartido\"> ");
                    out.println("<tr>");
                    out.println("<th>");
                    out.println("<input value=\""+code1+"\" type=\"hidden\" id=\"txtcodq1\">");
                    out.println("</th>");
                    out.println("<th></th>");
                    out.println("<th>");
                    out.println("<input value=\""+code2+"\" type=\"hidden\" id=\"txtcodq2\">");
                    out.println("</th>");
                    out.println(" </tr>");
                    out.println("<tr>");
                    out.println("<th><h3>"+equipo1.getPais()+"</h3></th>");
                    out.println("<th></th>");
                    out.println("<th><h3>"+equipo2.getPais()+"</h3></th>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td rowspan=\"2\">");
                    if(partido.marcador_mensaje.equals("SI"))
                    {
                        if(equipo1.getCodEquipo()==partido.getRival1().getCodigo())
                        {
                            out.println("<input value=\""+partido.getRival1().getGoles()+"\" size=\"2\" id=\"txtgolq1\">");
                        }
                        else
                        {
                            out.println("<input value=\""+partido.getRival2().getGoles()+"\" size=\"2\" id=\"txtgolq1\">");
                        }
                                
                    }
                    else
                    {
                         out.println("<input value=\"0\" size=\"2\" id=\"txtgolq1\">");
                    }
                    
                    
                    out.println("</td>");
                    out.println(" <td></td>");
                    out.println("<td rowspan=\"2\">");
                    if(partido.marcador_mensaje.equals("SI"))
                    {
                        if(equipo2.getCodEquipo()==partido.getRival1().getCodigo())
                        {
                            out.println("<input value=\""+partido.getRival1().getGoles()+"\" size=\"2\" id=\"txtgolq2\">");
                        }
                        else
                        {
                            out.println("<input value=\""+partido.getRival2().getGoles()+"\" size=\"2\" id=\"txtgolq2\">");
                        }

                    }
                    else
                    {
                         out.println("<input value=\"0\" size=\"2\" id=\"txtgolq2\">");
                    }
                    out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td></td>"); 
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    String infofh[] = partido.getFecha().split(" ");
                    System.out.println(infofh[0]);
                    System.out.println(infofh[1]);
                    String info2[] = infofh[1].split(":");
                    System.out.println(info2.length);
                    String hour="";
                    for(int i=0;i<info2.length-1;i++)
                    {
                        hour = hour +info2[i]+ ":";
                    }
                    hour = hour+"00";
                    System.out.println(hour);
   
                    out.println("Fecha: <input type=\"date\"  value=\""+infofh[0]+"\"id=\"txtfecpartido\">");
                    out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    //String info2[] = infofh[1].split(".");
                    out.println("Hora: <input type=\"time\" value=\""+hour+"\" id=\"txthorapartido\">");
                    out.println("</td>");
                    out.println("</tr>");
                    
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    out.println("ciudad: <select id=\"selectciudadpartido\">");
                    
                    ArrayList<Ciudad> lista = ciudad.get_ciudades();
                    Iterator<Ciudad> it = lista.iterator();
                    while(it.hasNext())
                    {
                        Ciudad ciu2 = it.next();
                        if(ciudad.getCodigo()==ciu2.getCodigo())
                            out.println("<option value=\""+ciu2.getCodigo()+"\" selected>"+ciu2.getNombre()+"</option>");
                        else
                            out.println("<option value=\""+ciu2.getCodigo()+"\">"+ciu2.getNombre()+"</option>");
                                      
                                    
                     }
                     
                    
                    
                    
                    out.println("</select>");
                    out.println("</td>");
                    out.println("</tr>");
                    
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    out.println("--------");
                    out.println("</td>");
                    out.println("</tr>");
                    //LO DE LOS ARBITROS CENTRAL
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    
                    out.println("Central: <select id=\"selectarbitro1\">");
                    Arbitro arbi = new Arbitro();
                    ArrayList<Arbitro> larbitros = arbi.get_arbitros();
                    partido.get_arbitros_partido(partido.getCodPartido());
                    System.out.println("mensaje_arbitro: "+partido.mensaje_arbitro);
                    if(partido.mensaje_arbitro=="SI")
                    {
                        Iterator<Arbitro> itt = larbitros.iterator();
                        while(itt.hasNext())
                        {
                            Arbitro arbb = itt.next();
                            if(partido.central!=null)
                            {
                                if(arbb.getCod()==partido.central.getCod())
                                {
                                    out.println("<option value=\""+arbb.getCod()+"\" selected>"+arbb.getNombre()+"<option>");
                                }
                                
                            }
                            
                            else
                            {
                                out.println("<option value=\""+arbb.getCod()+"\">"+arbb.getNombre()+"<option>");
                            }
                            
                        }
                        
                    }
                    else
                    {
                        Iterator<Arbitro> itt = larbitros.iterator();
                        while(itt.hasNext())
                        {
                            Arbitro arbb = itt.next();
                            out.println("<option value=\""+arbb.getCod()+"\">"+arbb.getNombre()+"<option>");
                            
                            
                        }
                    }
                    out.println("</select>");
                    out.println("</td>");
                    out.println("</tr>");
                    
                    
                    //LO DE LOS ARBITROS ASISTENTE1
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    
                    out.println("Asistente1: <select id=\"selectarbitro2\">");
                    
                    if(partido.mensaje_arbitro=="SI")
                    {
                        Iterator<Arbitro> itt = larbitros.iterator();
                        while(itt.hasNext())
                        {
                            Arbitro arbb = itt.next();
                            if(partido.asistente1!=null)
                            {
                                if(arbb.getCod()==partido.asistente1.getCod())
                                {
                                    out.println("<option value=\""+arbb.getCod()+"\" selected>"+arbb.getNombre()+"<option>");
                                }
                                    
                                
                            }
                                                        
                            else
                            {
                                out.println("<option value=\""+arbb.getCod()+"\">"+arbb.getNombre()+"<option>");
                            }
                            
                        }
                        
                    }
                    else
                    {
                        Iterator<Arbitro> itt = larbitros.iterator();
                        while(itt.hasNext())
                        {
                            Arbitro arbb = itt.next();
                            out.println("<option value=\""+arbb.getCod()+"\">"+arbb.getNombre()+"<option>");
                            
                            
                        }
                    }
                    out.println("</select>");
                    out.println("</td>");
                    out.println("</tr>");

                    
                    //LO DE LOS ARBITROS ASISTENTE2
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    
                    out.println("Asistente2: <select id=\"selectarbitro3\">");
                    if(partido.mensaje_arbitro=="SI")
                    {
                        Iterator<Arbitro> itt = larbitros.iterator();
                        while(itt.hasNext())
                        {
                            Arbitro arbb = itt.next();
                            if(partido.asistente2!=null)
                            {
                                if(arbb.getCod()==partido.asistente2.getCod())
                                {
                                    out.println("<option value=\""+arbb.getCod()+"\" selected>"+arbb.getNombre()+"<option>");
                                }
                                
                            }
                            
                            else
                            {
                                out.println("<option value=\""+arbb.getCod()+"\">"+arbb.getNombre()+"<option>");
                            }
                            
                        }
                        
                    }
                    else
                    {
                        Iterator<Arbitro> itt = larbitros.iterator();
                        while(itt.hasNext())
                        {
                            Arbitro arbb = itt.next();
                            out.println("<option value=\""+arbb.getCod()+"\">"+arbb.getNombre()+"<option>");
                            
                            
                        }
                    }
                    out.println("</select>");
                    out.println("</td>");
                    out.println("</tr>");

                    
                    
                    
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    out.println("<input class=\"btn btn-success\" id=\"btngrabarpartido\" type=\"button\" value=\"Guardar\" style=\"font-size : 11px; width:100%; height:30px;>\" onclick=\"javascript:jsGrabarPartido('G');\"");
                    
                    out.println("</td>");
                    out.println("</tr>");
                     out.println("<td colspan=\"3\">");
                    out.println("<input class=\"btn btn-danger\" id=\"btneliminarpartido\" type=\"button\" value=\"Eliminar\" style=\"font-size : 11px; width:100%; height:30px;>\" onclick=\"javascript:jsGrabarPartido('E');\"");                  
                    
                    out.println("</td>");
                    out.println("</tr>");
                    
                    
                    out.println("</table>");
                    
            }
            else
            {
                out.println("<table align=\"center\" id=\"tablainfopartido\"> ");
                    out.println("<tr>");
                    out.println("<th>");
                    out.println("<input value=\""+equipo1.getCodEquipo()+"\" type=\"hidden\" id=\"txtcodq1\">");
                    out.println("</th>");
                    out.println("<th></th>");
                    out.println("<th>");
                    out.println("<input value=\""+equipo2.getCodEquipo()+"\" type=\"hidden\" id=\"txtcodq2\">");
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
                    out.println("<input value=\"0\" size=\"2\" id=\"txtgolq2\">");
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
                    out.println("ciudad: <select id=\"selectciudadpartido\">");
                    
                    Ciudad ciu1 = new Ciudad();
                        ArrayList<Ciudad> lista = ciu1.get_ciudades();
                        System.out.println("NUMERO DE CIUDADES: "+lista.size());
                        Iterator<Ciudad> it = lista.iterator();
                        while(it.hasNext())
                        {
                            Ciudad ciu2 = it.next();
                            out.println("<option value=\""+ciu2.getCodigo()+"\">"+ciu2.getNombre()+"</option>");
                                    
                        }
                    
                    
                    
                    
                    
                    out.println("</select>");
                    out.println("</td>");
                    out.println("</tr>");
                    
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    out.println("--------");
                    out.println("</td>");
                    out.println("</tr>");
                    
                    //LO DE LOS ARBITROS CENTRAL
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    
                    out.println("Central: <select id=\"selectarbitro1\">");
                    Arbitro arbi = new Arbitro();
                    ArrayList<Arbitro> larbitros = arbi.get_arbitros();
                   
                        Iterator<Arbitro> itt = larbitros.iterator();
                        while(itt.hasNext())
                        {
                            Arbitro arbb = itt.next();
                            out.println("<option value=\""+arbb.getCod()+"\">"+arbb.getNombre()+"<option>");
                            
                            
                        }
                    
                    out.println("</select>");
                    out.println("</td>");
                    out.println("</tr>");
                    
                    
                    //LO DE LOS ARBITROS ASISTENTE1
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    
                    out.println("Asistente1: <select id=\"selectarbitro2\">");
                    Iterator<Arbitro> ittt = larbitros.iterator();
                        while(ittt.hasNext())
                        {
                            Arbitro arbb = ittt.next();
                            out.println("<option value=\""+arbb.getCod()+"\">"+arbb.getNombre()+"<option>");
                            
                            
                        }
                    
                    out.println("</select>");
                    out.println("</td>");
                    out.println("</tr>");

                    
                    //LO DE LOS ARBITROS ASISTENTE2
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    
                    out.println("Asistente2: <select id=\"selectarbitro3\">");
                   
                    
                        Iterator<Arbitro> itttt = larbitros.iterator();
                        while(itttt.hasNext())
                        {
                            Arbitro arbb = itttt.next();
                            out.println("<option value=\""+arbb.getCod()+"\">"+arbb.getNombre()+"<option>");
                            
                            
                        }
                    
                    out.println("</select>");
                    out.println("</td>");
                    out.println("</tr>");

                    
                    out.println("<tr>");
                    out.println("<td colspan=\"3\">");
                    out.println("<input class=\"btn btn-success\" id=\"btngrabarpartido\" type=\"button\" value=\"Guardar\" style=\"font-size : 11px; width:100%; height:30px;>\" onclick=\"javascript:jsGrabarPartido('G');\"");
                    
                    out.println("</td>");
                    out.println("</tr>");
                     out.println("<td colspan=\"3\">");
                    out.println("<input class=\"btn btn-danger\" id=\"btneliminarpartido\" type=\"button\" value=\"Eliminar\" style=\"font-size : 11px; width:100%; height:30px;>\" onclick=\"javascript:jsGrabarPartido('E');\"");                  
                    
                    out.println("</td>");
                    out.println("</tr>");
                    
                    
                    out.println("</table>");
                    
            }
            
                    
            
            
            
            
        }
        else if(flag.equals("grabarpartido"))
        {
            System.out.println("ENTRO A grabarpartido");
            int cod1 = Integer.valueOf(request.getParameter("codeq1"));
            int cod2 = Integer.valueOf(request.getParameter("codeq2"));
            int goleq1 = Integer.valueOf(request.getParameter("golq1"));
            int goleq2 = Integer.valueOf(request.getParameter("golq2"));
            String fecha = request.getParameter("fecha");
            String hora = request.getParameter("hora");
            char grr = request.getParameter("grr").charAt(0);
            char flagw = request.getParameter("flagw").charAt(0);
            int codciudad = Integer.valueOf(request.getParameter("codciudad"));
            int oid=(int) request.getSession().getAttribute("id");
            int codarb1 = Integer.valueOf(request.getParameter("codarb1"));
            int codarb2 = Integer.valueOf(request.getParameter("codarb2"));
            int codarb3 = Integer.valueOf(request.getParameter("codarb3"));
            System.out.println("cod1:"+cod1);
            System.out.println("cod2:"+cod2);
            System.out.println("goleq1:"+goleq1);
            System.out.println("goleq2:"+goleq2);
            System.out.println("fecha:"+fecha);
            System.out.println("hora:"+hora);
            System.out.println("grr:"+grr);
            System.out.println("flagw:"+flagw);
            System.out.println("codciudad:"+codciudad);
            System.out.println("oid:"+oid);
            Partido partido = new Partido();
            partido.set_Partido(cod1,cod2,goleq1,goleq2,grr,hora,fecha,codciudad,flagw,oid,codarb1,codarb2,codarb3);
            
            
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
            Arbitro arbitro = new Arbitro();
            arbitro.set_Arbitro(nombre, codigop);
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
            System.out.println("coda: "+coda);
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
            out.println("           <td>Pais:</td>");
            out.println("<td>");
            out.println(" <select id=\"selectarbpaiact\">");
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
        else if(flag.equals("submitpais"))
        {
            String nombre = request.getParameter("nombre");
            int codconf = Integer.valueOf(request.getParameter("codconferencia"));
            Pais pa = new Pais();
           String mensaje= pa.set_Pais(nombre, codconf);
           response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            out.println(mensaje);
        }
       else if(flag.equals("infopais"))
        {
            System.out.println("ENTRO A infopais");
            //Se obtiene la info del arbitro
            int codp = Integer.valueOf(request.getParameter("codigopais"));
            System.out.println("codp: "+codp);
            Pais pa = new Pais();
            pa.get_infopais(codp);
                        
            //SE EMPIEZA A ARMAR LA TABLA EN DONDE SE VAN A PRESENTAR ESTOS DATOS
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            
            out.println("<form id=\"formpais\">");
            out.println("<table cellpadding=\"5\" >");
            out.println("       <tr>");
            out.println("           <td>Nombre:</td>");
            out.println("           <td><input name=\"nombre\" type=\"text\" value=\""+pa.getNombre()+"\" id=\"txtnombrepais\"></td>");
            out.println("        </tr>");
            
            out.println("       <tr>");
            out.println("           <td><input name=\"codarbitro\" type=\"hidden\" value=\""+pa.getCodpais()+"\" id=\"txtcodpais\"></td>");
            out.println("        </tr>");
            out.println("       <tr>");
            out.println("<td>");
            out.println("Confederacion:");
            out.println("</td>");
            out.println("<td>");
            out.println("<select id=\"selectconfepaisact\">");
            //se obtiene la lista de paises Y se deja como seleccionado el pais que tiene actualmente
            Confederacion confe = new Confederacion();
            ArrayList<Confederacion> lista = confe.getConfederaciones();
            Iterator<Confederacion> it = lista.iterator();
            while(it.hasNext())
            {
                Confederacion conf = it.next();
                if(conf.getCod()==pa.getCodconfederacion())
                    out.println("<option value=\""+conf.getCod()+"\" selected>"+conf.getNombre()+"</option>");
                else
                    out.println("<option value=\""+conf.getCod()+"\" >"+conf.getNombre()+"</option>");
            }
            out.println("</select>");
            out.println("</td>");        
            out.println("        </tr>");
            out.println("       <tr>");
            out.println("           <td></td>");
            out.println("           <td>");
            out.println("               <input value=\"Modificar\" class=\"btn btn-success\" type=\"button\" id=\"btnmodpais\"  onclick=\"javascript:jsMEpais('M');\" >");
            out.println("               <input value=\"Eliminar\" class=\"btn btn-danger\" type=\"button\"  id=\"btnelimpais\" onclick=\"javascript:jsMEpais('E');\">");
            out.println("           </td>");
            out.println("       </tr>");
            out.println("        </table>");
            out.println("        </form>");
        }
       
        else if(flag.equals("Epais"))
        {
            System.out.println("ENTRO A Epais");
            int codp = Integer.valueOf(request.getParameter("codpais"));
            Pais pa = new Pais();
            pa.Epais(codp);
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            out.println("Pais eliminado");
            
        }
        else if(flag.equals("Mpais"))
        {
            System.out.println("ENTRO A Mpais");
            int codp = Integer.valueOf(request.getParameter("codpais"));
            int codconfe = Integer.valueOf(request.getParameter("codconfe"));
            String nombre = request.getParameter("nombre");
            Pais pa = new Pais();
            pa.Mpais(codp, nombre, codconfe);
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            out.println("Pais modificado");
            
        }
        else if(flag.equals("submitjugador"))
        {
            //METODO set_Jugador(int camiseta,String fecnac,float estatura,float peso,String nombre,String equipo,String posicion, int codeq)
            int camiseta = Integer.valueOf(request.getParameter("camiseta"));
            String fecnac = request.getParameter("fecnac");
            float estatura = Float.valueOf(request.getParameter("estatura"));
            float peso = Float.valueOf(request.getParameter("peso"));
            String nombre= request.getParameter("nombre");
            String equipo= request.getParameter("equipo");
            String posicion = request.getParameter("posicion");
            int codeq = Integer.valueOf(request.getParameter("codeq"));
            Equipo eq = new Equipo();
            String mensaje = eq.set_Jugador(camiseta, fecnac, estatura, peso, nombre, equipo, posicion, codeq);
            PrintWriter out = response.getWriter();
            out.println(mensaje);
        }
        else if(flag.equals("ljugadores"))
        {
            int codeq = Integer.valueOf(request.getParameter("codeq"));
            Equipo equipo = new Equipo();
            equipo.setCodeq(codeq);
            equipo.cargarJugadores();
            ArrayList<Jugador> lista = equipo.getJugadores();
            Iterator<Jugador> it = lista.iterator();
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            while(it.hasNext())
            {
                Jugador jug = it.next();
                out.println("<option value=\""+jug.getCamiseta()+"\">"+jug.getCamiseta()+","+jug.getNombre()+"</option>");
            }
        }
        
        else if(flag.equals("infojugador"))
        {
            //equipo.get_jugador(int camiseta, int codeq)
             int camiseta = Integer.valueOf(request.getParameter("camiseta"));
             int codeq = Integer.valueOf(request.getParameter("codeq"));
             Equipo equipo = new Equipo();
             Jugador jug = equipo.get_jugador(camiseta, codeq);
             
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            out.println("<form id=\"formjugadoresinfo\">");
            out.println("<table cellpadding=\"5\" >");
            out.println("<tr>");
            out.println("<td>Fecha Nacimiento:</td>");
            out.println("<td><input id=\"txtfechajugador\" type=\"date\" maxlength=\"50\" value=\""+jug.getFechaNac().toString()+"\"></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Estatura:</td>");
            out.println("<td><input id=\"txtestaturajugador\"  type=\"number\" step=\"0.01\" value=\""+jug.getEstatura()+"\"></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Peso:</td>");
            out.println("<td><input id=\"txtpesojugador\" type=\"number\" step=\"0.01\" value=\""+jug.getPeso()+"\"></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Nombre:</td>");
            out.println("<td><input id=\"txtnombrejugador\" type=\"text\" maxlength=\"50\" value=\""+jug.getNombre()+"\"></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Equipo:</td>");
            out.println("<td><input id=\"txtequipojugador\" type=\"text\" maxlength=\"50\" value=\""+jug.getEquipo()+"\"></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Posicion:</td>");
            out.println("<td><input id=\"txtposicionjugador\" type=\"text\" maxlength=\"50\" value=\""+jug.getPosicion()+"\"></td>");
            out.println("</tr>");
            out.println("       <tr>");
            out.println("           <td></td>");
            out.println("           <td>");
            out.println("               <input value=\"Modificar\" class=\"btn btn-success\" type=\"button\" id=\"btnmodjugador\"  onclick=\"javascript:jsMEjugador('M');\" >");
            out.println("               <input value=\"Eliminar\" class=\"btn btn-danger\" type=\"button\"  id=\"btnelimjugador\" onclick=\"javascript:jsMEjugador('E');\">");
            out.println("           </td>");
            
            out.println("</table>");
            out.println("</form>");
             
             
        }
        else if(flag.equals("Ejugador"))
        {
            int camiseta = Integer.valueOf(request.getParameter("camiseta"));
            int codeq = Integer.valueOf(request.getParameter("codeq"));
            Equipo eq = new Equipo();
            eq.E_jugador(camiseta, codeq);
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            out.write("jugador eliminado correctamente");
        }
        else if(flag.equals("Mjugador"))
        {
            //equipo.M_jugador(int camiseta,String fecnac,float estatura,float peso,String nombre,String equipo,String posicion, int codeq)
            int camiseta = Integer.valueOf(request.getParameter("camiseta"));
            String fecnac = request.getParameter("fecnac");
            float estatura = Float.valueOf(request.getParameter("estatura"));
            float peso = Float.valueOf(request.getParameter("peso"));
            String nombre= request.getParameter("nombre");
            String equipo= request.getParameter("equipo");
            String posicion = request.getParameter("posicion");
            int codeq = Integer.valueOf(request.getParameter("codeq"));
            Equipo eq = new Equipo();
            eq.M_jugador(camiseta, fecnac, estatura, peso, nombre, equipo, posicion, codeq);
            response.setContentType( "text/html; charset=iso-8859-1" );
            PrintWriter out = response.getWriter();
            out.write("jugador modificado correctamente");
        }
        
       
        else
        {
            System.out.println("");
            processRequest(request, response,"sdsd");
            ;
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
