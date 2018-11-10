package negocio.interfaces;

import datos.modelos.Categoria;

import java.util.List;

public interface CategoriaDAO {
    List<Categoria> listarCategorias();
    int registrarCategoria(Categoria categoria);
    boolean actualizarCategoria(Categoria categoria);
    boolean eliminarCategoria(int categoria_id);
    Categoria getCategoria(int categoria_id);
}
