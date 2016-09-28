/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;


/**
 *
 * @author wxjoy
 */
public class Rival {
    private int codEq;
    private String nombre;
    
    public Rival(){
    }
    
    public Rival(int codEq, String nombre){
        this.codEq = codEq;
        this.nombre = nombre;
    }
    
    public int getCodigo(){
        return codEq;
    }
   
    public String getNombre(){
        return nombre;
    }

}
