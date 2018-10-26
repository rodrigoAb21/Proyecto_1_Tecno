/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.daoimpl;

import datos.ConexionBD;
import datos.modelos.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import negocio.interfaces.UsuarioDAO;

/**
 *
 * @author KAKU
 */
public class UsuarioDAOImpl implements UsuarioDAO{

    private ConexionBD db;

    public UsuarioDAOImpl() {
        db = new ConexionBD();
    }
    
    
    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        
        return usuarios;
    }

    @Override
    public boolean registrarUsuario(Usuario usuario) {
        try {
            db.conectar();
            
            String query = "INSERT INTO usuario(nombre, apellido, ci, email) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getCi());
            ps.setString(4, usuario.getEmail());
            
            int i = ps.executeUpdate();
            
            db.desconectar();
            
            return (i > 0);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean actualizarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarUsuario(int usuario_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario verUsuario(int usuario_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
