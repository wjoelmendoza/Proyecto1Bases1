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

/**
 *
 * @author byron
 */
public class Ciudad {
    
    
    private int codigo;
    private String nombre;
    public String mensaje;
    public Ciudad(){}
    public Ciudad(int codigo,String nombre){this.setCodigo(codigo);this.setNombre(nombre);}
    private ResultSet rset;
    private Conexion conexion;
    private CallableStatement clstm;
    public void get_ciudad_partido(int codeq1,int codeq2)
    {
        try {
            //get_ciudad_partido(codeq1 IN INTEGER,codeq2 IN INTEGER,mess OUT varchar2,codciudad OUT INTEGER,nomciudad OUT varchar2 )
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call get_ciudad_partido(?,?,?,?,?)}");
            clstm.registerOutParameter(3,oracle.jdbc.OracleTypes.VARCHAR);
            clstm.registerOutParameter(4,oracle.jdbc.OracleTypes.INTEGER);
            clstm.registerOutParameter(5,oracle.jdbc.OracleTypes.VARCHAR);
            clstm.setInt(1,codeq1);
            clstm.setInt(2,codeq2);
            clstm.execute();
            
            mensaje = clstm.getString(3);
            this.setCodigo(clstm.getInt(4));
            this.setNombre(clstm.getString(5));
            
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
    }
    public ArrayList<Ciudad> get_ciudades(){
    
        ArrayList<Ciudad> lista = new ArrayList<>();
        try {
            //get_ciudades(cursor1 OUT SYS_REFCURSOR)
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call get_ciudades(?)}");
            clstm.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            
            clstm.execute();
            
            rset = (ResultSet)clstm.getObject(1);
            while(rset.next())
            {
                lista.add(new Ciudad(rset.getInt("cod_ciudad"),rset.getString("nombre")));
            }
            
           // System.out.println("MENSAJE DE LA BASE DE DATOS: "+mensaje);          
            
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
            
    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
