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
public class Arbitro {
    
    private int cod;
    private String nombre;
    private int codpais;
    private Conexion conexion;
    private Statement stm;
    private ResultSet rset;
    private CallableStatement clstm;
    
    
    
    public Arbitro(){}
    public Arbitro(int co,String nom,int cop){this.cod=co;this.nombre=nom;this.codpais=cop;}
    
    public void get_arbitro(int coda)
    {
        Arbitro arbitro = null;
        try {
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call get_Arbitro(?,?)}");
            clstm.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            clstm.setInt(2,coda);
            clstm.execute();
            rset = (ResultSet)clstm.getObject(1);
            rset.next();
            this.setCod(rset.getInt("cod_arb"));
            this.setNombre(rset.getString("nombre"));
            this.setCodpais(rset.getInt("cod_pais"));
            
                
            
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
        
    }
    
    public ArrayList<Arbitro> get_arbitros()
    {
        ArrayList<Arbitro> lista =new ArrayList<>();
        try {
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call get_Arbitros(?)}");
            clstm.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            clstm.execute();
            rset = (ResultSet)clstm.getObject(1);
            while(rset.next())
                lista.add(new Arbitro(rset.getInt("cod_arb"),rset.getString("nombre"),rset.getInt("cod_pais")));
            
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
        return lista;
        
    }
    public void MArbitro(int coda,String nom,int codp)
    {
        try {
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call M_arbitro(?,?,?)}");
            clstm.setInt(1,coda);
            clstm.setString(2, nom);
            clstm.setInt(3,codp);
            clstm.execute();
            
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
    }
    public void EArbitro(int coda)
    {
        try {
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call E_arbitro(?)}");
            clstm.setInt(1,coda);
            clstm.execute();
            
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
    }
    public void set_Arbitro(String nom,int codp)
    {
        try {
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call set_arbitro(?,?)}");
            clstm.setString(1,nom);
            clstm.setInt(2, codp);
            clstm.execute();
            
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
    
}
