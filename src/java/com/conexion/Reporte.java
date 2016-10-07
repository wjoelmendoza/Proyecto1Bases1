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
public class Reporte {
    private Conexion conexion;
    private ResultSet rset;
    private CallableStatement clstm;
    private ArrayList<String[]>  resultado;
    private boolean valido;
    
    public Reporte(){}
    
    public boolean esValido(){
        return valido;
    }
    
    public String[] reporte1(){
        conexion = new Conexion();
        String[] res = new String[3];
        valido=true;
        try {
            clstm = conexion.getConexion().prepareCall("{ call reporte_1(?)}");
            clstm.registerOutParameter(1, OracleTypes.CURSOR);
            clstm.execute();
            rset =(ResultSet) clstm.getObject(1);
            if(rset.next()){
                res[0] = rset.getString(1);
                res[1] = rset.getString(2);
                res[2] = rset.getString(3);
            }else{
                valido = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            valido = false;
        }
        return res;
    }
    
    public ArrayList<String[]> reporte2(){
        conexion = new Conexion();
        resultado = new ArrayList<>();
        String[] res = new String[3];
        valido=true;
        try {
            clstm = conexion.getConexion().prepareCall("{ call reporte_2(?)}");
            clstm.registerOutParameter(1, OracleTypes.CURSOR);
            clstm.execute();
            rset =(ResultSet) clstm.getObject(1);
            while(rset.next()){
                res = new String[3];
                res[0] = rset.getString(1);
                res[1] = rset.getString(2);
                res[2] = rset.getString(3);
                resultado.add(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            valido = false;
        }
        return resultado;
    }
    
    public ArrayList<String[]> reporte3(){
        conexion = new Conexion();
        resultado = new ArrayList<>();
        String[] res;
        valido=true;
        try {
            clstm = conexion.getConexion().prepareCall("{ call reporte_3(?)}");
            clstm.registerOutParameter(1, OracleTypes.CURSOR);
            clstm.execute();
            rset =(ResultSet) clstm.getObject(1);
            while(rset.next()){
                res = new String[3];
                res[0] = rset.getString(1);
                res[1] = rset.getString(2);
                res[2] = rset.getString(3);
                resultado.add(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            valido = false;
        }
        return resultado;
    }
    
    public ArrayList<String[]> reporte4(){
        conexion = new Conexion();
        resultado = new ArrayList<>();
        String[] res;
        valido=true;
        try {
            clstm = conexion.getConexion().prepareCall("{ call reporte_4(?)}");
            clstm.registerOutParameter(1, OracleTypes.CURSOR);
            clstm.execute();
            rset =(ResultSet) clstm.getObject(1);
            while(rset.next()){
                res = new String[3];
                res[0] = rset.getString(1);
                res[1] = rset.getString(2);
                res[2] = rset.getString(3);
                resultado.add(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            valido = false;
        }
        return resultado;
    }
    
    public ArrayList<String[]> reporte5(){
        conexion = new Conexion();
        resultado = new ArrayList<>();
        String[] res;
        valido=true;
        try {
            clstm = conexion.getConexion().prepareCall("{ call reporte_5(?)}");
            clstm.registerOutParameter(1, OracleTypes.CURSOR);
            clstm.execute();
            rset =(ResultSet) clstm.getObject(1);
            while(rset.next()){
                res = new String[3];
                res[0] = rset.getString(1);
                res[1] = rset.getString(2);
                res[2] = rset.getString(3);
                resultado.add(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            valido = false;
        }
        return resultado;
    }
    
    public ArrayList<String[]> reporte6(){
        conexion = new Conexion();
        resultado = new ArrayList<>();
        String[] res;
        valido=true;
        try {
            clstm = conexion.getConexion().prepareCall("{ call reporte_6(?)}");
            clstm.registerOutParameter(1, OracleTypes.CURSOR);
            clstm.execute();
            rset =(ResultSet) clstm.getObject(1);
            while(rset.next()){
                res = new String[4];
                res[0] = rset.getString(1);
                res[1] = rset.getString(2);
                res[2] = rset.getString(3);
                res[3] = rset.getString(4);
                resultado.add(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            valido = false;
        }
        return resultado;
    }
    
    public ArrayList<String[]> reporte7(){
        conexion = new Conexion();
        resultado = new ArrayList<>();
        String[] res;
        valido=true;
        try {
            clstm = conexion.getConexion().prepareCall("{ call reporte_7(?)}");
            clstm.registerOutParameter(1, OracleTypes.CURSOR);
            clstm.execute();
            rset =(ResultSet) clstm.getObject(1);
            while(rset.next()){
                res = new String[3];
                res[0] = rset.getString(1);
                res[1] = rset.getString(2);
                res[2] = rset.getString(3);
                resultado.add(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            valido = false;
        }
        return resultado;
    }
    
    public String[] reporte8(){
        conexion = new Conexion();
        String[] res = new String[3];
        valido=true;
        try {
            clstm = conexion.getConexion().prepareCall("{ call reporte_8(?)}");
            clstm.registerOutParameter(1, OracleTypes.CURSOR);
            clstm.execute();
            rset =(ResultSet) clstm.getObject(1);
            if(rset.next()){
                res[0] = rset.getString(1);
                res[1] = rset.getString(2);
                res[2] = rset.getString(3);
            }else{
                valido = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            valido = false;
        }
        return res;
    }
    
    public ArrayList<String[]> reporte9(){
        conexion = new Conexion();
        resultado = new ArrayList<>();
        String[] res;
        valido=true;
        try {
            clstm = conexion.getConexion().prepareCall("{ call reporte_9(?)}");
            clstm.registerOutParameter(1, OracleTypes.CURSOR);
            clstm.execute();
            rset =(ResultSet) clstm.getObject(1);
            while(rset.next()){
                res = new String[3];
                res[0] = rset.getString(1);
                res[1] = rset.getString(2);
                res[2] = rset.getString(3);
                resultado.add(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            valido = false;
        }
        return resultado;
    }
    
    public ArrayList<String[]> reporte10(){
        conexion = new Conexion();
        resultado = new ArrayList<>();
        String[] res;
        valido=true;
        try {
            clstm = conexion.getConexion().prepareCall("{ call reporte_10(?)}");
            clstm.registerOutParameter(1, OracleTypes.CURSOR);
            clstm.execute();
            rset =(ResultSet) clstm.getObject(1);
            while(rset.next()){
                res = new String[4];
                res[0] = rset.getString(1);
                res[1] = rset.getString(2);
                res[2] = rset.getString(3);
                res[3] = rset.getString(4);
                resultado.add(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            valido = false;
        }
        return resultado;
    }
}
