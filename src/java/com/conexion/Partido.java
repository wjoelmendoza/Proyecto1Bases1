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
    
    public Partido(){}
    
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
    
}
