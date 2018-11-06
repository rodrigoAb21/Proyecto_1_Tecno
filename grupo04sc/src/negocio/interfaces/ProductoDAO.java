package negocio.interfaces;

import datos.modelos.Producto;

import java.util.List;

public interface ProductoDAO {
    List<Producto> listarProductos();
    int registrarProducto(Producto producto);
    boolean actualizarProducto(Producto producto);
    boolean eliminarProducto(int producto_id);
    Producto getProducto(int producto_id);
}
