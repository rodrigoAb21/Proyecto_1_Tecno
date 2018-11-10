package datos.daoimpl;

import datos.ConexionBD;
import datos.modelos.ActivoFijo;
import negocio.interfaces.ActivoFijoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ActivoFijoDAOImpl implements ActivoFijoDAO {
    private final ConexionBD db;
    private static final String TABLA = "activo_fijo";

    public ActivoFijoDAOImpl() {
        db = new ConexionBD();
    }

    @Override
    public List<ActivoFijo> listarActivoFijos() {
        List<ActivoFijo> activoFijos = new ArrayList<>();

        try {
            db.conectar();

            String query ="SELECT " +
                    "id, " +
                    "estado, " +
                    "disponible, " +
                    "codigo, " +
                    "producto_id, " +
                    "visible " +
                    "FROM " + TABLA;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                ActivoFijo activoFijo = new ActivoFijo();
                activoFijo.setId(resultSet.getInt("id"));
                activoFijo.setEstado(resultSet.getString("estado"));
                activoFijo.setDisponible(resultSet.getBoolean("disponible"));
                activoFijo.setCodigo(resultSet.getString("codigo"));
                activoFijo.setProducto_id(resultSet.getInt("producto_id"));
                activoFijo.setVisible(resultSet.getBoolean("visible"));

                activoFijos.add(activoFijo);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return activoFijos;
    }

    @Override
    public int registrarActivoFijo(ActivoFijo activoFijo) {
        try {
            db.conectar();

            String query = "INSERT INTO " + TABLA +"(estado, disponible, codigo, producto_id, nro_factura, costo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = db.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, activoFijo.getEstado());
            ps.setBoolean(2, activoFijo.isDisponible());
            ps.setString(3, activoFijo.getCodigo());
            ps.setInt(4, activoFijo.getProducto_id());
            ps.setString(5, activoFijo.getNroFactura());
            ps.setInt(6,activoFijo.getCosto());
            ps.setString(5, TABLA);

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
    public boolean actualizarActivoFijo(ActivoFijo activoFijo) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET " +
                    "estado = ?, " +
                    "disponible = ?, " +
                    "codigo = ?, " +
                    "producto_id = ?, " +
                    "WHERE id = ?";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setString(1, activoFijo.getEstado());
            ps.setBoolean(2, activoFijo.isDisponible());
            ps.setString(3, activoFijo.getCodigo());
            ps.setInt(4, activoFijo.getProducto_id());
            ps.setInt(5, activoFijo.getId());

            int i = ps.executeUpdate();

            db.desconectar();

            return (i > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarActivoFijo(int activoFijo_id) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET visible = false WHERE id = " + activoFijo_id;

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
    public ActivoFijo getActivoFijo(int activoFijo_id) {
        try {
            db.conectar();

            String query ="SELECT " +
                    "id, " +
                    "estado, " +
                    "disponible, " +
                    "codigo, " +
                    "producto_id, " +
                    "visible " +
                    "FROM " + TABLA + " WHERE id = " + activoFijo_id;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                ActivoFijo activoFijo = new ActivoFijo();
                activoFijo.setId(resultSet.getInt("id"));
                activoFijo.setEstado(resultSet.getString("estado"));
                activoFijo.setDisponible(resultSet.getBoolean("disponible"));
                activoFijo.setCodigo(resultSet.getString("codigo"));
                activoFijo.setProducto_id(resultSet.getInt("producto_id"));
                activoFijo.setVisible(resultSet.getBoolean("visible"));

                return activoFijo;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
