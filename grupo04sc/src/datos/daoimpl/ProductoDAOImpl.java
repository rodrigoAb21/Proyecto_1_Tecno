package datos.daoimpl;

import datos.ConexionBD;
import datos.modelos.Producto;
import negocio.interfaces.ProductoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {
    private final ConexionBD db;
    private static final String TABLA = "producto";

    public ProductoDAOImpl() {
        db = new ConexionBD();
    }

    @Override
    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();

        try {
            db.conectar();

            String query ="SELECT " +
                    "id, " +
                    "nombre, " +
                    "tipo, " +
                    "codigo, " +
                    "cantidad, " +
                    "visible " +
                    "FROM " + TABLA;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                Producto producto = new Producto();
                producto.setId(resultSet.getInt("id"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setTipo(resultSet.getString("tipo"));
                producto.setCodigo(resultSet.getString("codigo"));
                producto.setCantidad(resultSet.getInt("cantidad"));
                producto.setVisible(resultSet.getBoolean("visible"));

                productos.add(producto);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return productos;
    }

    @Override
    public boolean registrarProducto(Producto producto) {
        try {
            db.conectar();

            String query = "INSERT INTO " + TABLA +"(nombre, tipo, codigo, cantidad) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getTipo());
            ps.setString(3, producto.getCodigo());
            ps.setInt(4, producto.getCantidad());

            int i = ps.executeUpdate();

            db.desconectar();

            return (i > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean actualizarProducto(Producto producto) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET " +
                    "nombre = ?, " +
                    "tipo= ?, " +
                    "codigo = ?, " +
                    "cantidad = ? " +
                    "WHERE id = ?";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getTipo());
            ps.setString(3, producto.getCodigo());
            ps.setInt(4, producto.getCantidad());
            ps.setInt(5, producto.getId());

            int i = ps.executeUpdate();

            db.desconectar();

            return (i > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarProducto(int producto_id) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET visible = false WHERE id = " + producto_id;

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
    public Producto getProducto(int producto_id) {
        try {
            db.conectar();

            String query ="SELECT " +
                    "id, " +
                    "nombre, " +
                    "tipo, " +
                    "codigo, " +
                    "cantidad, " +
                    "visible " +
                    "FROM " + TABLA + " WHERE id = " + producto_id;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                Producto producto = new Producto();
                producto.setId(resultSet.getInt("id"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setTipo(resultSet.getString("tipo"));
                producto.setCodigo(resultSet.getString("codigo"));
                producto.setCantidad(resultSet.getInt("cantidad"));
                producto.setVisible(resultSet.getBoolean("visible"));

                return producto;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
