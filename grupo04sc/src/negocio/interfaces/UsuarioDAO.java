package negocio.interfaces;

import datos.modelos.Usuario;

import java.util.List;

public interface UsuarioDAO {
    List<Usuario> listarUsuarios();
    boolean registrarUsuario(Usuario usuario);
    boolean editarUsuario(Usuario usuario);
    boolean eliminarUsuario(int usuario_id);
    Usuario getUsuario(int usuario_id);
}
