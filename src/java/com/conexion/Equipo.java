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
import oracle.jdbc.OracleTypes;


/**
 *
 * @author wxjoy
 */
public class Equipo {
    private ResultSet rset;
    private Conexion conexion;
    private String pais;
    private CallableStatement clstm;
    private String director;
    private int codeq;
    private ArrayList<Jugador> jugadores;
    private boolean equipo;
    /**
     * Este metodo realiza una busqueda en la base de datos seung el 
     * nombre 
     * @param nombrePais mediante de este parametro se realiza la busques
     */
    public void buscarEquipo(String nombrePais){
        try {
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call buscar_equipo(?,?)}");
            clstm.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            clstm.setString(2, nombrePais);
            clstm.execute();
            rset = (ResultSet)clstm.getObject(1);
            if(rset.next()){
                equipo = true;
                codeq = rset.getInt(1);
                director = rset.getString(2);
                pais = nombrePais;
            }else{
                equipo = false;
            }
            
        } catch (SQLException ex) {
            equipo = false;
            Logger.getLogger(Equipo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (clstm != null)
                    clstm.close();
                
                if(rset!=null)
                    rset.close();
                
                if(conexion!= null)
                    conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Equipo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(hasEquipo())
            cargarJugadores();
        
    }
    
    private void cargarJugadores(){
        try {
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{ call listar_plantilla(?,?)}");
            clstm.setInt(1, codeq);
            clstm.registerOutParameter(2, OracleTypes.CURSOR);
            clstm.execute();
            rset = (ResultSet)clstm.getObject(2);
            jugadores = new ArrayList<>();
            while(rset.next()){
                jugadores.add(new Jugador(rset));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Equipo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getPais(){return pais;}
    
    public String getDirector(){return director;}
    
    public boolean hasEquipo(){
        return equipo;
    }
    
    public ArrayList<Jugador> getJugadores(){
        return jugadores;
    }
}
