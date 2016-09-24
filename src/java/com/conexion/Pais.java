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
    private Conexion conexion;
    private Statement stm;
    private ResultSet rset;
    private CallableStatement clstm;
    
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
}
