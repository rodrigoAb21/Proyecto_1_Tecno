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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import negocio.interfaces.UsuarioDAO;

/**
 *
 * @author KAKU
 */
public class UsuarioDAOImpl implements UsuarioDAO{

    private final ConexionBD db;
    private static final String TABLA = "usuario";

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
                    "FROM " + TABLA + " WHERE visible = true " +
                    "ORDER BY (id) asc";

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
    public List<Usuario> listarUsuariosEliminados() {
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
                    "FROM " + TABLA + " WHERE visible = false " +
                    "ORDER BY (id) asc";

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
    public int registrarUsuario(Usuario usuario) {
        try {
            db.conectar();

            String query = "INSERT INTO " + TABLA +"(nombre, apellido, ci, email, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = db.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getCi());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getCi());

            int i = ps.executeUpdate();
            db.desconectar();
            if (i > 0){
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public boolean editarUsuario(Usuario usuario) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET " +
                    "nombre = ?, " +
                    "apellido = ?, " +
                    "ci = ?, " +
                    "email = ?, " +
                    "password = ? " +
                    "WHERE usuario.id = ?";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getCi());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getCi());
            ps.setInt(6, usuario.getId());

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

            String query ="UPDATE " + TABLA + " SET visible = false WHERE id = " + usuario_id;

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
    public boolean recuperarUsuario(int usuario_id) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET visible = true WHERE id = " + usuario_id;

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
    public Usuario getUsuario(int usuario_id) {
        try {
            db.conectar();

            Usuario usuario = new Usuario();

            String query ="SELECT " +
                    "nombre, " +
                    "apellido, " +
                    "ci, " +
                    "email, " +
                    "visible " +
                    "FROM " + TABLA +
                    " WHERE id = " + usuario_id;

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

    @Override
    public Usuario getUsuario(String email) {
        try {
            db.conectar();

            Usuario usuario = new Usuario();

            String query ="SELECT " +
                    "id, " +
                    "nombre, " +
                    "apellido, " +
                    "ci, " +
                    "email, " +
                    "visible " +
                    "FROM " + TABLA +
                    " WHERE email = \'" + email + "\'";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            if (resultSet.next()){

                usuario.setId(resultSet.getInt("id"));
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

    @Override
    public boolean validarCorreo(String correo) {
        try {
            
            db.conectar();

            String query ="SELECT  * FROM " + TABLA + " WHERE email = \'" + correo + "\' AND visible = true";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            if (resultSet.next()){
                return true;
            }

        } catch (SQLException e) {
             System.out.println(e.getMessage());
        }
        return false;
    }




}
