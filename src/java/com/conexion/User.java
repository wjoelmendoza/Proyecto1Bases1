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
public class User {
    
    private Conexion conexion;
    private Statement stm;
    private ResultSet rset;
    private String nombre ="";
    private char rol;
    
    
    private void nuevaConexion(){
        conexion = new Conexion();
    }
    
    public int nuevoUsuario(String nombre, String fecha, String correo, char pago,
                            String clave,String pais, int ffecha){
        nuevaConexion();
        StringBuilder query = new StringBuilder();
        query.append("BEGIN ")
                .append("REGISTRAR_CLIENTE('")
                .append(nombre)
                .append("',");
                
            if(ffecha == 1){
            query.append("TO_DATE('")
                .append(fecha)
                .append("','dd/mm/yyyy'),'");
            }else if(ffecha==2){
                query.append("TO_DATE('")
                        .append(fecha)
                        .append("','yyyy-mm-dd'),'");
            }
        query.append(correo)
            .append("','")
            .append(pago)
            .append("','")
            .append(clave)
            .append("','")
            .append(pais)
            .append("');")
            .append(" END;");
        
        try {
            stm = conexion.getConexion().createStatement();
            stm.execute(query.toString());
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.close();
            try {
                if(rset!=null)rset.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if(stm!=null)stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    
    public int login(String correo,String clave){
        this.nuevaConexion();
        int id=-1;
        String tipo;
        StringBuilder stme = new StringBuilder();
        stme.append("SELECT U.cod_usuario, U.nombre, U.tipo")
                .append(" FROM USUARIO U")
                .append(" WHERE U.correo = '")
                .append(correo)
                .append("' and U.clave = '")
                .append(clave)
                .append("'");
        try{
            stm = conexion.getConexion().createStatement();
            rset = stm.executeQuery(stme.toString());
            if(rset.next()){
                id = rset.getInt(1);
                nombre = rset.getString(2);
                tipo = rset.getString(3);
                rol = tipo.charAt(0);
            }else{
                id = -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public char getRol(){
        return rol;
    }
    
    /**
     * llamar para verificar si el correo no le pertenece a otro usuario
     * que ya esta registrado
     */
    public boolean isDisponible(String correo){
        this.nuevaConexion();
        StringBuilder query = new StringBuilder();
        boolean disp = true;
        query.append("SELECT U.cod_usuario ")
                .append("FROM USUARIO U ")
                .append("WHERE U.correo = '")
                .append(correo)
                .append("'");
        
        try{
            stm = conexion.getConexion().createStatement();
            rset = stm.executeQuery(query.toString());
            disp = !rset.next();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                if(stm!=null)
                    stm.close();
                
                if(rset != null)
                    rset.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return disp;
    }
}
