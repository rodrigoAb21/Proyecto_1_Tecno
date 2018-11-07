package datos.daoimpl;

import datos.ConexionBD;
import datos.modelos.Producto;
import negocio.interfaces.ProductoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

            String query ="SELECT * " +
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
                producto.setCategoria_id(resultSet.getInt("categoria_id"));
                producto.setVisible(resultSet.getBoolean("visible"));

                productos.add(producto);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return productos;
    }

    @Override
    public int registrarProducto(Producto producto) {
        try {
            db.conectar();

            String query = "INSERT INTO " + TABLA +"(nombre, codigo, descripcion, categoria_id) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = db.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getCodigo());
            ps.setString(3, producto.getDescripcion());
            ps.setInt(4, producto.getCategoria_id());

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
    public boolean actualizarProducto(Producto producto) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET " +
                    "nombre = ?, " +
                    "codigo = ?, " +
                    "descripcion = ?, " +
                    "categoria_id = ? " +
                    "WHERE id = ?";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getCodigo());
            ps.setString(3, producto.getDescripcion());
            ps.setInt(4, producto.getCategoria_id());
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

            String query ="SELECT * FROM " + TABLA + " WHERE id = " + producto_id + " AND visible = true ";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                Producto producto = new Producto();
                producto.setId(resultSet.getInt("id"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setCodigo(resultSet.getString("codigo"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setCategoria_id(resultSet.getInt("categoria_id"));
                producto.setVisible(resultSet.getBoolean("visible"));

                return producto;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
