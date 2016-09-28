/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author byron
 */
public class Confederacion {
    
    private ArrayList<Confederacion> confederas;
    private Conexion conexion;
    private Statement stm;
    private ResultSet rset;
    CallableStatement cstmt;
    
    private int cod;
    private String nombre;
    private String acronimo;
    
    
    public Confederacion(){}
    public Confederacion(int co,String nom,String acro){this.cod = co;this.nombre=nom;this.acronimo=acro;}
    public Confederacion getconfederacion(int cod)
    {
        Confederacion confe = null;
        try {
            conexion = new Conexion();
            cstmt = conexion.getConexion().prepareCall("{call get_confederacion2(?,?)}");
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2, cod);
            cstmt.execute();
            
            rset = (ResultSet)cstmt.getObject(1);
            System.err.println("NUMERO DE FILA: "+rset.getRow());
            rset.next();
            System.err.println("NUMERO DE FILA: "+rset.getRow());
            confe = new Confederacion(rset.getInt("cod"),rset.getString("nombre"),rset.getString("acronimo"));
            
            
           
            
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
        return confe;
    }
    
    public ArrayList<Confederacion> getConfederaciones(){
        try {
            conexion = new Conexion();
            
            confederas = new ArrayList<>();
            
            cstmt = conexion.getConexion().prepareCall("{call get_confederacion(?)}");
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
           
            cstmt.execute();
            rset = (ResultSet)cstmt.getObject(1);
            //rset = cstmt.getResultSet();
            //System.out.println(rset.getCursorName());
            while(rset.next())
            {
                
              confederas.add(new Confederacion(rset.getInt("cod"),rset.getString("nombre"),rset.getString("acronimo")));
            }
            
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
        
        return confederas;
    }

    /**
     * @return the cod
     */
    public int getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(int cod) {
        this.cod = cod;
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

    /**
     * @return the acronimo
     */
    public String getAcronimo() {
        return acronimo;
    }

    /**
     * @param acronimo the acronimo to set
     */
    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }
    
}
