/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;


/**
 *
 * @author wxjoy
 */
public class Equipo {
    private ResultSet rset;
    private Conexion conexion;
    private String pais;
    private CallableStatement clstm;
    private String director;
    private String confederacion;
    private int codeq;
    private ArrayList<Jugador> jugadores;
    private boolean equipo;
    private String grupo;
    
    public Equipo(){}
    public Equipo(int codeeq,String nombre){this.codeq=codeeq;this.pais=nombre;}
    public void M_jugador(int camiseta,String fecnac,float estatura,float peso,String nombre,String equipo,String posicion, int codeq)
    {
         try {
            
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call M_jugador(?,?,?,?,?,?,?,?)}");
            clstm.setInt(1,camiseta);
            clstm.setDate(2,java.sql.Date.valueOf(fecnac));
            clstm.setFloat(3,estatura);
            clstm.setFloat(4,peso);
            clstm.setString(5,nombre);
            clstm.setString(6,equipo);
            clstm.setString(7,posicion);
            clstm.setInt(8,codeq);
            
            
            clstm.execute();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(clstm!=null){
                try {
                    clstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rset!=null){
                try {
                    rset.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public void E_jugador(int camiseta,int codeq)
    {
        try {
            
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call E_jugador(?,?)}");
            clstm.setInt(1,camiseta);
            clstm.setInt(2,codeq);
            clstm.execute();
                 
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(clstm!=null){
                try {
                    clstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rset!=null){
                try {
                    rset.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public Jugador get_jugador(int camiseta, int codeq)
    {
        Jugador jugador = null;
        try {
            
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call get_jugador(?,?,?)}");
            clstm.setInt(1,camiseta);
            clstm.setInt(2,codeq);
            clstm.registerOutParameter(3,oracle.jdbc.OracleTypes.CURSOR);
            clstm.execute();
            rset = (ResultSet)clstm.getObject(3);
            rset.next();
            jugador = new Jugador(rset);
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(clstm!=null){
                try {
                    clstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rset!=null){
                try {
                    rset.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return jugador;
        
    }
    public String set_Jugador(int camiseta,String fecnac,float estatura,float peso,String nombre,String equipo,String posicion, int codeq)
    {
        System.out.println("ENTRO a set_Jugador "+codeq+","+camiseta);
        String mensaje="#";
        
        try {
            
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call set_jugador(?,?,?,?,?,?,?,?,?)}");
            clstm.setInt(1,camiseta);
            clstm.setDate(2,java.sql.Date.valueOf(fecnac));
            clstm.setFloat(3,estatura);
            clstm.setFloat(4,peso);
            clstm.setString(5,nombre);
            clstm.setString(6,equipo);
            clstm.setString(7,posicion);
            clstm.setInt(8,codeq);
            
            clstm.registerOutParameter(9,oracle.jdbc.OracleTypes.VARCHAR);
            clstm.execute();
            mensaje = clstm.getString(9);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(clstm!=null){
                try {
                    clstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rset!=null){
                try {
                    rset.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return mensaje;
    }
    
    public String M_Equipo(int codeq,String direc,String grrr)
    {
        System.out.println("ENTRO a M_Equipo "+codeq);
        String mensaje="#";
        
        try {
            System.out.println("GRUPO A CAMBIAR: "+grrr);
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call M_equipo(?,?,?,?)}");
            clstm.setInt(1, codeq);
            clstm.setString(2,direc);
            clstm.setString(3,grrr);
            clstm.registerOutParameter(4,oracle.jdbc.OracleTypes.VARCHAR);
            clstm.execute();
            mensaje = clstm.getString(4);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(clstm!=null){
                try {
                    clstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rset!=null){
                try {
                    rset.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return mensaje;
    }
    public void E_Equipo(int codeq)
    {
        System.out.println("ENTRO a E_Equipo "+codeq);
        try {
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call E_equipo(?)}");
            clstm.setInt(1, codeq);
            clstm.execute();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(clstm!=null){
                try {
                    clstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rset!=null){
                try {
                    rset.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public void get_infoEquipo(int codeq)
    {
        try {
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call get_equipo(?,?)}");
            clstm.registerOutParameter(2,oracle.jdbc.OracleTypes.CURSOR);
            clstm.setInt(1, codeq);
            clstm.execute();
            rset = (ResultSet)clstm.getObject(2);
            if(rset.next())
            {
                this.setCodeq(rset.getInt(1));
                this.pais=rset.getString(2);
                this.director=rset.getString(3);
                this.grupo=rset.getString(4);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(clstm!=null){
                try {
                    clstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rset!=null){
                try {
                    rset.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public ArrayList<Equipo> get_Selecciones()
    {
        ArrayList<Equipo> lista = new ArrayList<>();
        try {
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call listar_equipos(?)}");
            clstm.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            
            clstm.execute();
            rset = (ResultSet)clstm.getObject(1);
            while(rset.next())
            {
                lista.add(new Equipo(rset.getInt(1),rset.getString(2)));
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(clstm!=null){
                try {
                    clstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rset!=null){
                try {
                    rset.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return lista;
    }
    public String SetEquipo(int codpais,String grup,String director)
    {
        String mensaje="";
        try {
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call set_Equipo(?,?,?,?)}");
            clstm.registerOutParameter(4,oracle.jdbc.OracleTypes.VARCHAR);
            clstm.setString(1,director);
            clstm.setInt(2, codpais);
            clstm.setString(3,"A");
            clstm.execute();
            
            mensaje = clstm.getString(4);
            System.out.println("MENSAJE DE LA BASE DE DATOS: "+mensaje);          
            
        } catch (SQLException ex) {
            Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(clstm!=null){
                try {
                    clstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rset!=null){
                try {
                    rset.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return mensaje;
    }
    public Equipo(ResultSet rset){
        try {
            director = rset.getString(1);
            pais = rset.getString(2);
            confederacion = rset.getString(3);
            codeq = rset.getInt(4);
        } catch (SQLException ex) {
            Logger.getLogger(Equipo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Este metodo realiza una busqueda en la base de datos seung el 
     * nombre 
     * @param nombrePais mediante de este parametro se realiza la busques
     */
    public void buscarEquipo(String nombrePais){
        try {
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call buscar_equipo(?,?)}");
            clstm.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            clstm.setString(2, nombrePais);
            clstm.execute();
            rset = (ResultSet)clstm.getObject(1);
            if(rset.next()){
                equipo = true;
                setCodeq(rset.getInt(1));
                director = rset.getString(2);
                pais = nombrePais;
            }else{
                equipo = false;
            }
            
        } catch (SQLException ex) {
            equipo = false;
            Logger.getLogger(Equipo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (clstm != null)
                    clstm.close();
                
                if(rset!=null)
                    rset.close();
                
                if(conexion!= null)
                    conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Equipo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(hasEquipo())
            cargarJugadores();
        
    }
    
    public void buscarEquipo(String grupo, int codEq){
        System.out.println("EN buscarEquipo: equipo:"+codEq+" grupo:"+grupo);
        conexion = new Conexion();
        try {
            clstm = conexion.getConexion().prepareCall("{call buscar_equipo_cod(?,?,?)}");
            clstm.registerOutParameter(1, OracleTypes.CURSOR);
            clstm.setInt(2, codEq);
            clstm.setString(3, grupo);
            clstm.execute();
            rset = (ResultSet)clstm.getObject(1);
            if(rset.next()){
                equipo = true;
                setCodeq(rset.getInt(1));
                director = rset.getString(2);
                pais = rset.getString(3);
            }else{
                equipo = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Equipo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(hasEquipo())
            cargarJugadores();
    }
    
    public void cargarJugadores(){
        try {
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{ call listar_plantilla(?,?)}");
            clstm.setInt(1, getCodeq());
            clstm.registerOutParameter(2, OracleTypes.CURSOR);
            clstm.execute();
            rset = (ResultSet)clstm.getObject(2);
            jugadores = new ArrayList<>();
            //System.out.println("obteniendo equipo");
            while(rset.next()){
                jugadores.add(new Jugador(rset));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Equipo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getPais(){return pais;}
    
    public String getDirector(){return director;}
    
    public boolean hasEquipo(){
        return equipo;
    }
    
    public ArrayList<Jugador> getJugadores(){
        return jugadores;
    }
    
    public String getConfederacion(){
        return confederacion;
    }
    
    public int getCodEquipo(){
        return getCodeq();
    }
    public ArrayList<Equipo> getEquiposGrupo(char grupo){
        ArrayList<Equipo> equipos=null;
        String tmp;
        tmp = ""+ grupo;
        try {
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{ call listar_equipo_grupo(?,?)}");
            clstm.setString(1,tmp);
            
            clstm.registerOutParameter(2, OracleTypes.CURSOR);
            clstm.execute();
            rset = (ResultSet)clstm.getObject(2);
            
            equipos = new ArrayList<>();
            this.cargarEquipos(rset, equipos);
        } catch (SQLException ex) {
            Logger.getLogger(Equipo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         return equipos;
    }
    
    private void cargarEquipos(ResultSet rse, ArrayList<Equipo> equipos){
        try {
            while(rse.next()){
                equipos.add(new Equipo(rse));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Equipo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    /**
     * @return the codeq
     */
    public int getCodeq() {
        return codeq;
    }

    /**
     * @param codeq the codeq to set
     */
    public void setCodeq(int codeq) {
        this.codeq = codeq;
    }
    
}
