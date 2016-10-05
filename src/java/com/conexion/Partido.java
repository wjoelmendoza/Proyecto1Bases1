/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author wxjoy
 */
public class Partido {
    private Conexion conexion;
    private ResultSet rset;
    private CallableStatement clstm;
    private ArrayList<Partido> partidos;
    private Rival rival1;
    private Rival rival2;
    private int codPartido;
    private String fecha;
    private int codciudad;
    private boolean valido;
    private boolean crear;
    private int codU;
    
    public String mensaje;
    
    public Partido(){}
    public void MakePartidos(char grupo){
        //ESTE METODOS ES EL ENCARGADO DE CREAR LAS COMBINACIONES DE
        //TODOS LOS DIFERENTES PARTIDOS DE UN GRUPO DETERMINADO
        
    }
    public void get_marcador_partido(int codparti)
    {
        try {
            //get_info_partido(codeq1 IN INTEGER,codeq2 IN INTEGER,mess OUT varchar2,cursorr OUT SYS_REFCURSOR)
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call get_marcador_partido_a(?,?)");
            clstm.setInt(1,codparti);
            clstm.registerOutParameter(2,oracle.jdbc.OracleTypes.CURSOR);
                      
            clstm.execute();
            
            rset = (ResultSet)clstm.getObject(2);
            while(rset.next())
            {
                rival1 = new Rival(rset.getInt("cod_equipo"),rset.getInt("goles"));
                
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
    public void get_InfoPartido(int codeq1,int codeq2)
    {
        try {
            //get_info_partido(codeq1 IN INTEGER,codeq2 IN INTEGER,mess OUT varchar2,cursorr OUT SYS_REFCURSOR)
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call get_info_partido(?,?,?,?)");
            clstm.setInt(1,codeq1);
            clstm.setInt(2,codeq2);
            clstm.registerOutParameter(3,oracle.jdbc.OracleTypes.VARCHAR);
            clstm.registerOutParameter(4,oracle.jdbc.OracleTypes.CURSOR);
                      
            clstm.execute();
            mensaje = clstm.getString(3);
            
            System.out.println("MENSAJE DE LA BASE DE DATOS get_infoPartido: "+mensaje);          
            
            rset = (ResultSet)clstm.getObject(4);
            
            if(rset!=null)
            {
                rset.next();
                this.setCodPartido(rset.getInt("codigo"));
                this.setFecha(rset.getTimestamp("fecha").toString());
                this.setCodciudad(rset.getInt("cod_ciudad"));
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
    public String set_Partido(int codeq1,int codeq2,int goleq1,int goleq2,char grr,String hora,String fecc,int codciudad,char flag,int codu )
    {
        //set_partido(codu IN INTEGER, mess OUT VARCHAR2)
        try {
            //get_ciudades(cursor1 OUT SYS_REFCURSOR)
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call set_partido(?,?,?,?,?,?,?,?,?,?)}");
            clstm.setInt(1,codeq1);
            clstm.setInt(2,codeq2);
            clstm.setInt(3,goleq1);
            clstm.setInt(4,goleq2);
            clstm.setString(5,String.valueOf(grr));
            clstm.setTimestamp(6,java.sql.Timestamp.valueOf(fecc+" "+hora+":00"));
            clstm.setInt(7,codciudad);
            clstm.setString(8,String.valueOf(flag));
            clstm.setInt(9, codu);
            clstm.registerOutParameter(10,oracle.jdbc.OracleTypes.VARCHAR);
                      
            clstm.execute();
            mensaje = clstm.getString(10);
            /*rset = (ResultSet)clstm.getObject(1);
            while(rset.next())
            {
                lista.add(new Ciudad(rset.getInt("cod_ciudad"),rset.getString("nombre")));
            }
            */
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
    public Partido(Rival rival1, Rival rival2, int codPartido, int codU, boolean crear){
        this.rival1 = rival1;
        this.rival2 = rival2;
        this.codPartido = codPartido;
        this.crear = crear;
        this.codU = codU;
    }
    
    public Partido(ResultSet rsetc){
        try {
            codPartido = rsetc.getInt(1);
            rival1 = new Rival(rsetc.getInt(2),rsetc.getString(3));
            rsetc.next();
            rival2 = new Rival(rsetc.getInt(2),rsetc.getString(3));
        } catch (SQLException ex) {
            Logger.getLogger(Partido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Rival getRival1(){
        return rival1;
    }
    
    public Rival getRival2(){
        return rival2;
    }
    
    public boolean isValid(){
        return valido;
    }
    
    public ArrayList<Partido> getPartidoGrupo(String grupo){
        conexion = new Conexion();
        try {
            clstm = conexion.getConexion().prepareCall("{ call get_rivales_grupo(?,?)}");
            clstm.setString(1, grupo);
            clstm.registerOutParameter(2, OracleTypes.CURSOR);
            clstm.execute();
            rset = (ResultSet)clstm.getObject(2);
            cargarPartidos();
            this.valido = true;
        } catch (SQLException ex) {
            this.valido = false;
            Logger.getLogger(Partido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return partidos;
    }
    
    public int getCodPartido(){
        return codPartido;
    }
    
    private void cargarPartidos(){
        try {
            partidos = new ArrayList<>();
            while(rset.next()){
                partidos.add(new Partido(rset));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Partido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void buscarPartido(int codPartido, int codUsuario, String grupo){
        try {
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call get_marcador_partido(?,?,?,?,?)}");
            clstm.setString(1, grupo);
            clstm.setInt(2, codPartido);
            clstm.setInt(3, codUsuario);
            clstm.registerOutParameter(4, OracleTypes.CURSOR);
            clstm.registerOutParameter(5,OracleTypes.CURSOR);
            clstm.execute();
            rset =(ResultSet) clstm.getObject(5);
            if(rset.next()){
                crear = false;
                valido = true;
                cargarMarcador(rset);
            }else{
                crear = true;
                rset = (ResultSet) clstm.getObject(4);
                if(rset.next()){
                    valido = true;
                    cargarRivales(rset);
                }else{
                    valido = false;
                }
            }
        } catch (SQLException ex) {
            valido = false;
            Logger.getLogger(Partido.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    private void cargarRivales(ResultSet rset) throws SQLException{
        this.setCodPartido(rset.getInt(1));
        rival1 = new Rival(rset.getInt(2),rset.getString(3));
        rset.next();
        rival2 = new Rival(rset.getInt(2),rset.getString(3));
    }
    private void cargarMarcador(ResultSet rset) throws SQLException{
        rival1 = new Rival(rset.getInt(1),rset.getString(2));
        rival1.setGoles(rset.getInt(3));
        this.setCodPartido(rset.getInt(4));
        rset.next();
        rival2 = new Rival(rset.getInt(1),rset.getString(2));
        rival2.setGoles(rset.getInt(3));
    }
    
    public boolean crearMarcador(){
        return crear;
    }
    
    public void guardarPartido(String tipo){
        try {
            String sv;
            sv = crear?"SI":"no";
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call guardar_marcador(?,?,?,?,?,?,?,?)}");
            clstm.setInt(1, codU);
            clstm.setInt(2, this.getCodPartido());
            clstm.setInt(3, this.rival1.getCodigo());
            clstm.setInt(4, this.rival2.getCodigo());
            clstm.setInt(5, this.rival1.getGoles());
            clstm.setInt(6, this.rival2.getGoles());
            clstm.setString(7,tipo);
            clstm.setString(8, sv);
            clstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Partido.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    /**
     * @param codPartido the codPartido to set
     */
    public void setCodPartido(int codPartido) {
        this.codPartido = codPartido;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the codciudad
     */
    public int getCodciudad() {
        return codciudad;
    }

    /**
     * @param codciudad the codciudad to set
     */
    public void setCodciudad(int codciudad) {
        this.codciudad = codciudad;
    }
}
