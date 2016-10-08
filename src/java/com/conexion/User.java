/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author wxjoy
 */
public class User {
    
    private Conexion conexion;
    private Statement stm;
    private int cod;
    private ResultSet rset;
    private String nombre ="";
    private String fecha;
    private String correo;
    private String pais;
    private char rol;
    private CallableStatement clstm;
    
    public User(){}
    
    public User(int cod,String nombre,String fecha, String correo, String pais){
        this.cod=cod;
        this.nombre=nombre;
        this.fecha = fecha;
        this.correo = correo;
        this.pais= pais;
    }

    private void nuevaConexion(){
        conexion = new Conexion();
    }
    
    public void cargarDatos(int codUser){
        conexion = new Conexion();
        String tmpFecha;
        String[] divFecha;
        try {
            clstm = conexion.getConexion().prepareCall("{ call get_d_usuario(?,?) }");
            clstm.setInt(1, codUser);
            clstm.registerOutParameter(2, OracleTypes.CURSOR);
            clstm.execute();
            rset = (ResultSet) clstm.getObject(2);
            rset.next();
            nombre = rset.getString(1);
            correo = rset.getString(2);
            tmpFecha = rset.getDate(3).toString();
            divFecha = tmpFecha.split("-");
            fecha = divFecha[2]+"/"+divFecha[1]+"/"+divFecha[0];
            System.out.println(tmpFecha);
            pais = rset.getString(4);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean cambiarDatos(String cambio, char formato){
        nuevaConexion();
        Date fecha;
        String tmpf[];
        System.out.println(formato);
        if(formato=='a')
        {
            tmpf = this.fecha.split("/");
            fecha = Date.valueOf(tmpf[2] +"-"+tmpf[1] +"-"+tmpf[0]);
        }else{
            fecha = Date.valueOf(this.fecha);
        }
        System.out.println(fecha.toString());
        try {
            clstm = conexion.getConexion().prepareCall("{ call act_d_usuario(?,?,?,?,?,?) }");
            clstm.setInt(1,cod);
            clstm.setString(2, nombre);
            clstm.setString(3, correo);
            clstm.setDate(4, fecha);
            clstm.setString(5, pais);
            clstm.setString(6, cambio);
            return !clstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
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
        
        try{
            clstm = conexion.getConexion().prepareCall("{call login_usuarios(?,?,?)}");
            clstm.setString(1, correo);
            clstm.setString(2, clave);
            clstm.registerOutParameter(3, OracleTypes.CURSOR);
            clstm.execute();
            rset = (ResultSet) clstm.getObject(3);
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
    
    public String getFecha(){
        return fecha;
    }
    
    public String getCorreo(){
        return correo;
    }
    
    public String getPais(){
        return pais;
    }
    /**
     * llamar para verificar si el correo no le pertenece a otro usuario
     * que ya esta registrado
     * @param correo es el correo que se desea validar
     * @return true si y solo si no existe otro usuario con el correo en cuestion
     */
    public boolean isDisponible(String correo){
        this.nuevaConexion();
        StringBuilder query = new StringBuilder();
        boolean disp = true;
        
        try{
            clstm = conexion.getConexion().prepareCall("{call validar_correo(?,?)}");
            clstm.setString(1, correo);
            clstm.registerOutParameter(2, OracleTypes.CURSOR);
            clstm.execute();
            rset =(ResultSet) clstm.getObject(2);
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
    
    /**
     * Este metodo se utilizar para el cambio de contraseña
     * @param codigo de usuario
     * @param clave actual del usuario
     * @param nClave sustituira la clave anterior
     * @return 0 si ocurre algun error 1 si se logra realizar el cambio satisfactorio
     */
    public int cambiarClave(int codigo, String clave, String nClave){
        int resultado=0;
        try {
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call cambiar_clave(?,?,?,?)}");
            clstm.setInt(1, codigo);
            clstm.setString(2, clave);
            clstm.setString(3, nClave);
            clstm.registerOutParameter(4, OracleTypes.INTEGER);
            clstm.execute();
            resultado =(int) clstm.getObject(4);
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(rset!=null) try {
                rset.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(clstm!=null) try {
                clstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(conexion!=null) conexion.close();
        }
        
        return resultado;
    }
    
    public boolean eliminarUsuario(int id, String clave){
        boolean resultado = false;
        int tmp;    
        try {
            conexion = new Conexion();
            clstm = conexion.getConexion().prepareCall("{call eliminar_usuario(?,?,?)}");
            clstm.setInt(1, id);
            clstm.setString(2, clave);
            clstm.registerOutParameter(3, OracleTypes.INTEGER);
            clstm.execute();
            tmp = clstm.getInt(3);
            resultado = tmp == 1;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
}
