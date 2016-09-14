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
public class Equipo {
    private ResultSet rset;
    private Statement stm;
    private Conexion conexion;
    private String pais;
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
            StringBuilder query = new StringBuilder();
            query.append("SELECT E.cod_equipo, E.director ")
                    .append("FROM Equipo E, Pais P ")
                    .append("WHERE P.nombre = '")
                    .append(nombrePais)
                    .append("' AND E.cod_pais = p.cod_pais");
            
            stm = conexion.getConexion().createStatement();
            rset = stm.executeQuery(query.toString());
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
                if (stm != null)
                    stm.close();
                
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
            StringBuilder query = new StringBuilder();
            query.append("SELECT J.camiseta, J.fecha_nac, J.estatura, J.peso,J.nombre,J.equipo ")
                    .append("FROM jugador J WHERE J.COD_EQUIPO = ")
                    .append(this.codeq);
            
            conexion = new Conexion();
            stm = conexion.getConexion().createStatement();
            rset = stm.executeQuery(query.toString());
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
