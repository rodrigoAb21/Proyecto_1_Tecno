/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.interfaces;

import datos.modelos.Usuario;
import java.util.List;

/**
 *
 * @author KAKU
 */
public interface UsuarioDAO {
    public List<Usuario> listarUsuarios();
    public boolean registrarUsuario(Usuario usuario);
    public boolean editarUsuario(Usuario usuario);
    public boolean eliminarUsuario(int usuario_id);
    public Usuario verUsuario(int usuario_id);
}
