/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.daoimpl;

import datos.ConexionBD;
import datos.modelos.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


        try {
            db.conectar();

            String query ="SELECT " +
                    "id, " +
                    "nombre, " +
                    "apellido, " +
                    "ci, " +
                    "email, " +
                    "visible " +
                    "FROM usuario";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getInt("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setApellido(resultSet.getString("apellido"));
                usuario.setCi(resultSet.getString("ci"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setVisible(resultSet.getBoolean("visible"));

                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

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
    public boolean editarUsuario(Usuario usuario) {
        try {
            db.conectar();

            String query ="UPDATE usuario SET " +
                    "nombre = ?, " +
                    "apellido = ?, " +
                    "ci = ?, " +
                    "email = ? " +
                    "WHERE usuario.id = ?";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getCi());
            ps.setString(4, usuario.getEmail());
            ps.setInt(5, usuario.getId());

            int i = ps.executeUpdate();

            db.desconectar();

            return (i > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarUsuario(int usuario_id) {
        try {
            db.conectar();

            String query ="UPDATE usuario SET visible = false WHERE usuario.id = " + usuario_id;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            int i = ps.executeUpdate();

            db.desconectar();

            return (i > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Usuario verUsuario(int usuario_id) {
        try {
            db.conectar();

            Usuario usuario = new Usuario();

            String query ="SELECT " +
                    "nombre, " +
                    "apellido, " +
                    "ci, " +
                    "email, " +
                    "visble " +
                    "FROM usuario "+
                    "WHERE id = " + usuario_id;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            if (resultSet.next()){

                usuario.setId(usuario_id);
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setApellido(resultSet.getString("apellido"));
                usuario.setCi(resultSet.getString("ci"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setVisible(resultSet.getBoolean("visible"));

                return usuario;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    

    public static void main(String[] args) {
        UsuarioDAO ud = new UsuarioDAOImpl();
        System.out.println("" + ud.listarUsuarios().size());

        
        
        
        
    }

}
