package datos.daoimpl;

import datos.ConexionBD;
import datos.modelos.MovActivo;
import negocio.interfaces.MovActivoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovActivoDAOImpl implements MovActivoDAO {
    private final ConexionBD db;
    private static final String TABLA = "movimiento_activo";

    public MovActivoDAOImpl() {
        db = new ConexionBD();
    }


    @Override
    public List<MovActivo> listarSuministros() {
        List<MovActivo> movActivos = new ArrayList<>();

        try {
            db.conectar();

            String query ="SELECT " +
                    "id, " +
                    "fecha, " +
                    "visible " +
                    "FROM " + TABLA;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                MovActivo movActivo = new MovActivo();
                movActivo.setId(resultSet.getInt("id"));
                movActivo.setFecha(resultSet.getObject("fecha", LocalDate.class));
                movActivo.setVisible(resultSet.getBoolean("visible"));

                movActivos.add(movActivo);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return movActivos;
    }

    @Override
    public boolean registrarMovSuministro(MovActivo movActivo) {
        try {
            db.conectar();

            String query = "INSERT INTO " + TABLA +"(fecha) VALUES (?)";
            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setObject(2, movActivo.getFecha());

            int i = ps.executeUpdate();

            db.desconectar();

            return (i > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean actualizarMovSuministro(MovActivo movActivo) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET " +
                    "fecha = ? " +
                    "WHERE id = ?";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setObject(1, movActivo.getFecha());
            ps.setInt(2, movActivo.getId());

            int i = ps.executeUpdate();

            db.desconectar();

            return (i > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarMovSuministro(int movActivo_id) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET visible = false WHERE id = " + movActivo_id;

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
    public MovActivo getMovSuministro(int movActivo_id) {
        try {
            db.conectar();

            MovActivo movActivo = new MovActivo();

            String query ="SELECT " +
                    "fecha, " +
                    "visible " +
                    "FROM " + TABLA +
                    " WHERE id = " + movActivo_id;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            if (resultSet.next()){

                movActivo.setId(movActivo_id);
                movActivo.setFecha(resultSet.getObject("fecha", LocalDate.class));
                movActivo.setVisible(resultSet.getBoolean("visible"));

                return movActivo;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
