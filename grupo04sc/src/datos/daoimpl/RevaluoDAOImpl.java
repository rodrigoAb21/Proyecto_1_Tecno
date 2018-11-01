package datos.daoimpl;

import datos.ConexionBD;
import datos.modelos.Revaluo;
import negocio.interfaces.RevaluoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RevaluoDAOImpl implements RevaluoDAO {

    private final ConexionBD db;
    private static final String TABLA = "revaluo";

    public RevaluoDAOImpl() {
        db = new ConexionBD();
    }

    @Override
    public List<Revaluo> listarRevaluos() {
        List<Revaluo> revaluos = new ArrayList<>();

        try {
            db.conectar();

            String query ="SELECT " +
                    "id, " +
                    "detalle, " +
                    "fecha, " +
                    "tipo, " +
                    "activo_fijo_id, " +
                    "visible " +
                    "FROM " + TABLA;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                Revaluo revaluo = new Revaluo();
                revaluo.setId(resultSet.getInt("id"));
                revaluo.setDetalle(resultSet.getString("detalle"));
                revaluo.setFecha(resultSet.getObject("fecha", LocalDate.class));
                revaluo.setTipo(resultSet.getString("tipo"));
                revaluo.setActivo_fijo_id(resultSet.getInt("activo_fijo_id"));
                revaluo.setVisible(resultSet.getBoolean("visible"));

                revaluos.add(revaluo);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return revaluos;
    }

    @Override
    public boolean registrarRevaluo(Revaluo revaluo) {
        try {
            db.conectar();

            String query = "INSERT INTO " + TABLA +"(detalle, fecha, tipo, activo_fijo_id) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setString(1, revaluo.getDetalle());
            ps.setObject(2, revaluo.getFecha());
            ps.setString(3, revaluo.getTipo());
            ps.setInt(4, revaluo.getActivo_fijo_id());

            int i = ps.executeUpdate();

            db.desconectar();

            return (i > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean actualizarRevaluo(Revaluo revaluo) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET " +
                    "detalle = ?, " +
                    "fecha = ?, " +
                    "tipo = ?, " +
                    "activo_fijo_id = ?, " +
                    "WHERE id = ?";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setString(1, revaluo.getDetalle());
            ps.setObject(2, revaluo.getFecha());
            ps.setString(3, revaluo.getTipo());
            ps.setInt(4, revaluo.getActivo_fijo_id());
            ps.setInt(5, revaluo.getId());

            int i = ps.executeUpdate();

            db.desconectar();

            return (i > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarRevaluo(int revaluo_id) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET visible = false WHERE id = " + revaluo_id;

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
    public Revaluo getRevaluo(int revaluo_id) {
        try {
            db.conectar();

            String query ="SELECT " +
                    "id, " +
                    "detalle, " +
                    "fecha, " +
                    "tipo, " +
                    "activo_fijo_id, " +
                    "visible " +
                    "FROM " + TABLA + " WHERE id = " + revaluo_id;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                Revaluo revaluo = new Revaluo();
                revaluo.setId(resultSet.getInt("id"));
                revaluo.setDetalle(resultSet.getString("detalle"));
                revaluo.setFecha(resultSet.getObject("fecha", LocalDate.class));
                revaluo.setTipo(resultSet.getString("tipo"));
                revaluo.setActivo_fijo_id(resultSet.getInt("activo_fijo_id"));
                revaluo.setVisible(resultSet.getBoolean("visible"));

                return revaluo;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
