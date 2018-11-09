package negocio.controladores.usuarios;

import datos.daoimpl.UsuarioDAOImpl;
import datos.modelos.Usuario;
import negocio.interfaces.UsuarioDAO;

import java.util.List;

public class UsuarioController {
    UsuarioDAO dao;


    public UsuarioController(){
        dao = new UsuarioDAOImpl();
    }

    public int registrarUsuario(String nombre, String apellido, String carnet, String email){
        Usuario usuario =  new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCi(carnet);
        usuario.setEmail(email);

        return dao.registrarUsuario(usuario);
    }

    public boolean editarUsuario(int id, String nombre, String apellido, String carnet, String email){
        Usuario usuario = dao.getUsuario(id);
        if (usuario != null){
            if (!nombre.equals("_")) usuario.setNombre(nombre);
            if (!apellido.equals("_")) usuario.setApellido(apellido);
            if (!carnet.equals("_")) usuario.setCi(carnet);
            if (!email.equals("_")) usuario.setEmail(email);

            return dao.editarUsuario(usuario);
        }
        return false;
    }

    public boolean eliminarUsuario(int id){
        return dao.eliminarUsuario(id);
    }

    public Usuario getUsuario(String email){
        return dao.getUsuario(email);
    }

    public Usuario getUsuario(int id){
        return dao.getUsuario(id);
    }

    public List<Usuario> listarUsuarios(){
        return dao.listarUsuarios();
    }

    public List<Usuario> listarUsuariosEliminados(){
        return dao.listarUsuariosEliminados();
    }

    public boolean recuperarUsuario(int id){
        return dao.recuperarUsuario(id);
    }



}
