/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import config.VariablesConf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author KAKU
 */
public class ConexionBD {
    private Connection conexion = null;
    private String db_name;
    private String host;
    private String user;
    private String password;

    public ConexionBD() {
        db_name = VariablesConf.DB_NOMBRE;
        host = VariablesConf.DB_HOST;
        user = VariablesConf.DB_USUARIO;
        password = VariablesConf.DB_CONTRASENA;
        
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo registrar el Driver \"postgresql\".");
        }
    }
    
    public void conectar(){
        try {
            String url = "jdbc:postgresql://" + host + ":5432/"+ db_name;
            conexion = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("No se pudo conectar con " + host + ".");
        }
    }
    
    public void desconectar(){
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public Connection getConexion() {
        return conexion;
    }
    
    
    
    
}
