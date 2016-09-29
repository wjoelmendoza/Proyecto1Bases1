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
    private boolean valido;
    private boolean crear;
    private int codU;
    
    public Partido(){}
    
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
    
    public void buscarPartido(int codPartido, String grupo){
        try {
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call get_marcador_partido(?,?,?,?)}");
            clstm.setString(1, grupo);
            clstm.setInt(2, codPartido);
            clstm.registerOutParameter(3, OracleTypes.CURSOR);
            clstm.registerOutParameter(4,OracleTypes.CURSOR);
            clstm.execute();
            rset =(ResultSet) clstm.getObject(4);
            if(rset.next()){
                crear = false;
                valido = true;
                cargarMarcador(rset);
            }else{
                crear = true;
                rset = (ResultSet) clstm.getObject(3);
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
        this.codPartido = rset.getInt(1);
        rival1 = new Rival(rset.getInt(2),rset.getString(3));
        rset.next();
        rival2 = new Rival(rset.getInt(2),rset.getString(3));
    }
    private void cargarMarcador(ResultSet rset) throws SQLException{
        rival1 = new Rival(rset.getInt(1),rset.getString(2));
        rival1.setGoles(rset.getInt(3));
        this.codPartido = rset.getInt(4);
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
            clstm.setInt(2, this.codPartido);
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
}
