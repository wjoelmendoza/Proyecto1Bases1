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

/**
 *
 * @author wxjoy
 */
public class Pais {
    private ArrayList<String> paises;
    private ArrayList<Pais> lpaises;
    private Conexion conexion;
    private Statement stm;
    private ResultSet rset;
    private CallableStatement clstm;
    private int codpais;
    private String nombre;
    
    
    public Pais(){}
    public Pais(int cod,String nom){this.codpais=cod;this.nombre=nom;}
    public ArrayList<Pais> getPaisesDisponibles(){
        
        try {
            conexion = new Conexion();
            
            lpaises = new ArrayList<>();
            
            clstm = conexion.getConexion().prepareCall("{call get_PaisesDisponibles(?)}");
           clstm.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
           
            clstm.execute();
            rset = (ResultSet)clstm.getObject(1);
            while(rset.next())
              lpaises.add(new Pais(rset.getInt("cod_pais"),rset.getString("nombre")));
            
        } catch (SQLException ex) {
            Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(stm!=null){
                try {
                    stm.close();
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
    
        
    return lpaises;
    
    }
    
    
    public ArrayList<Pais> getPaisesConCod()
    {
        try {
            conexion = new Conexion();
            lpaises = new ArrayList<>();
            clstm = conexion.getConexion().prepareCall("{call get_paises(?)}");
            clstm.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            clstm.execute();
            rset =(ResultSet)clstm.getObject(1);
            while(rset.next())
                lpaises.add(new Pais(rset.getInt("cod_pais"),rset.getString("nombre")));
            
        } catch (SQLException ex) {
            Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(stm!=null){
                try {
                    stm.close();
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
        
        return lpaises;
    }
    public ArrayList<String> getPaises(){
        try {
            conexion = new Conexion();
            paises = new ArrayList<>();
            clstm = conexion.getConexion().prepareCall("{call listado_paises(?)}");
            clstm.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            clstm.execute();
            rset =(ResultSet)clstm.getObject(1);
            while(rset.next())
                paises.add(rset.getString(1));
            
        } catch (SQLException ex) {
            Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(stm!=null){
                try {
                    stm.close();
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
        
        return paises;
    }
    
    /**
     * @return the codpais
     */
    public int getCodpais() {
        return codpais;
    }

    /**
     * @param codpais the codpais to set
     */
    public void setCodpais(int codpais) {
        this.codpais = codpais;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
