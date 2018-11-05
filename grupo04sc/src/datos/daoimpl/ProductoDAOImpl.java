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
                    "codigo, " +
                    "descripcion, " +
                    "visible " +
                    "FROM " + TABLA  + " WHERE visible = true ";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                Producto producto = new Producto();
                producto.setId(resultSet.getInt("id"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setCodigo(resultSet.getString("codigo"));
                producto.setDescripcion(resultSet.getString("descripcion"));
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

            String query = "INSERT INTO " + TABLA +"(nombre, codigo, descripcion) VALUES (?, ?, ?)";
            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getCodigo());
            ps.setString(3, producto.getDescripcion());


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
                    "codigo = ?, " +
                    "descripcion = ?, " +
                    "WHERE id = ?";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getCodigo());
            ps.setString(3, producto.getDescripcion());
            ps.setInt(4, producto.getId());

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
                    "codigo, " +
                    "descripcion, " +
                    "visible " +
                    "FROM " + TABLA + " WHERE id = " + producto_id + " AND visible = true ";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                Producto producto = new Producto();
                producto.setId(resultSet.getInt("id"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setCodigo(resultSet.getString("codigo"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setVisible(resultSet.getBoolean("visible"));

                return producto;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
