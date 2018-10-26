package datos.daoimpl;

import datos.ConexionBD;
import datos.modelos.MovSuministro;
import negocio.interfaces.MovSuministroDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovSuministroDAOImpl implements MovSuministroDAO {

    private final ConexionBD db;
    private static final String TABLA = "movimiento_suministro";

    public MovSuministroDAOImpl() {
        db = new ConexionBD();
    }

    @Override
    public List<MovSuministro> listarSuministros() {
        List<MovSuministro> movSuministros = new ArrayList<>();

        try {
            db.conectar();

            String query ="SELECT " +
                    "id, " +
                    "tipo, " +
                    "fecha, " +
                    "visible " +
                    "FROM " + TABLA;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                MovSuministro movSuministro = new MovSuministro();
                movSuministro.setId(resultSet.getInt("id"));
                movSuministro.setTipo(resultSet.getString("tipo"));
                movSuministro.setFecha(resultSet.getObject("fecha", LocalDate.class));
                movSuministro.setVisible(resultSet.getBoolean("visible"));

                movSuministros.add(movSuministro);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return movSuministros;
    }

    @Override
    public boolean registrarMovSuministro(MovSuministro movSuministro) {
        try {
            db.conectar();

            String query = "INSERT INTO " + TABLA +"(tipo, fecha) VALUES (?, ?)";
            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setString(1, movSuministro.getTipo());
            ps.setObject(2, movSuministro.getFecha());

            int i = ps.executeUpdate();

            db.desconectar();

            return (i > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean actualizarMovSuministro(MovSuministro movSuministro) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET " +
                    "tipo = ? ," +
                    "fecha = ? " +
                    "WHERE id = ?";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setString(1, movSuministro.getTipo());
            ps.setObject(2, movSuministro.getFecha());
            ps.setInt(3, movSuministro.getId());

            int i = ps.executeUpdate();

            db.desconectar();

            return (i > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarMovSuministro(int movSuministro_id) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET visible = false WHERE id = " + movSuministro_id;

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
    public MovSuministro getMovSuministro(int movSuministro_id) {
        try {
            db.conectar();

            MovSuministro movSuministro = new MovSuministro();

            String query ="SELECT " +
                    "tipo, " +
                    "fecha, " +
                    "visible " +
                    "FROM " + TABLA +
                    " WHERE id = " + movSuministro_id;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            if (resultSet.next()){

                movSuministro.setId(movSuministro_id);
                movSuministro.setTipo(resultSet.getString("tipo"));
                movSuministro.setFecha(resultSet.getObject("fecha", LocalDate.class));
                movSuministro.setVisible(resultSet.getBoolean("visible"));

                return movSuministro;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
