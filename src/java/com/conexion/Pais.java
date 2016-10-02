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
    private int codconfederacion;
    
    
    public Pais(){}
    public Pais(int cod,String nom){this.codpais=cod;this.nombre=nom;}
    
    public void Mpais(int codp,String nom,int codconfe)
    {
        //M_pais(codp IN integer,nom IN varchar,codconfe IN integer)
        try {
            conexion = new Conexion();
            
            lpaises = new ArrayList<>();
            
            clstm = conexion.getConexion().prepareCall("{call M_pais(?,?,?)}");
           
            clstm.setInt(1, codp);
            clstm.setString(2,nom);
            clstm.setInt(3, codconfe);
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
    public void Epais(int codp)
    {
        //E_pais(codp IN integer)
        try {
            conexion = new Conexion();
            
            lpaises = new ArrayList<>();
            
            clstm = conexion.getConexion().prepareCall("{call E_pais(?)}");
           
            clstm.setInt(1, codp);
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
    public void get_infopais(int codp)
    {
          try {
            conexion = new Conexion();
            
            lpaises = new ArrayList<>();
            //get_infopais(codp IN integer,cursor1 OUT SYS_REFCURSOR)
            clstm = conexion.getConexion().prepareCall("{call get_infopais(?,?)}");
           clstm.registerOutParameter(2,oracle.jdbc.OracleTypes.CURSOR);
            clstm.setInt(1, codp);
            clstm.execute();
            rset = (ResultSet)clstm.getObject(2);
            rset.next();
            this.setCodpais(rset.getInt("cod_pais"));
            this.setNombre(rset.getString("nombre"));
            this.setCodconfederacion(rset.getInt("cod_conf"));
            
            
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
   
    public String set_Pais(String nombre,int codconfe)
    {
        System.out.println("nombre:"+nombre);
        System.out.println("codconfe:"+codconfe);
        String mensaje="";
        //set_pais(nom IN varchar2,codconf IN integer,mess OUT varchar2)
        try {
            conexion = new Conexion();
            
            lpaises = new ArrayList<>();
            
            clstm = conexion.getConexion().prepareCall("{call set_pais(?,?,?)}");
           clstm.registerOutParameter(3,oracle.jdbc.OracleTypes.VARCHAR);
           clstm.setString(1, nombre);
           clstm.setInt(2, codconfe);
            clstm.execute();
            mensaje = clstm.getString(3);
            
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
        return mensaje;
    }
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

    /**
     * @return the codconfederacion
     */
    public int getCodconfederacion() {
        return codconfederacion;
    }

    /**
     * @param codconfederacion the codconfederacion to set
     */
    public void setCodconfederacion(int codconfederacion) {
        this.codconfederacion = codconfederacion;
    }
}
