/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wxjoy
 */
public class Jugador {
    private int camiseta;
    private Date fecha_nac;
    private float estatura;
    private float peso;
    private String nombre;
    private String equipo;
    private String posicion;
    
    public Jugador(ResultSet result){
        if(result !=null){
            try {
                camiseta = result.getInt(1);
                fecha_nac = result.getDate(2);
                estatura = result.getFloat(3);
                peso = result.getFloat(4);
                nombre = result.getString(5);
                equipo = result.getString(6);
                posicion = result.getString(7); 
            } catch (SQLException ex) {
                Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int getCamiseta(){return camiseta;}
    
    public Date getFechaNac(){return fecha_nac;}
    
    public float getEstatura(){return estatura;}
    
    public float getPeso(){return peso;}
    
    public String getNombre(){return nombre;}
    
    public String getEquipo(){ return equipo;}
    
    public String getPosicion(){ return posicion;}
    
}
