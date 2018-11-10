package datos.daoimpl;

import datos.ConexionBD;
import datos.modelos.MovSuministro;
import negocio.interfaces.MovSuministroDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public List<MovSuministro> listarMovimientos() {
        List<MovSuministro> movSuministros = new ArrayList<>();

        try {
            db.conectar();

            String query ="SELECT * FROM " + TABLA + " ORDER BY (id) asc";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                MovSuministro movSuministro = new MovSuministro();
                movSuministro.setId(resultSet.getInt("id"));
                movSuministro.setFecha(resultSet.getObject("fecha", LocalDate.class));
                movSuministro.setTipo(resultSet.getString("tipo"));
                movSuministro.setDpto(resultSet.getString("dpto"));
                movSuministro.setEncargado(resultSet.getString("encargado"));
                movSuministro.setObservacion(resultSet.getString("observacion"));
                movSuministro.setEstado(resultSet.getString("estado"));

                movSuministros.add(movSuministro);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return movSuministros;
    }

    @Override
    public int registrarMovSuministro(MovSuministro movSuministro) {
        try {
            db.conectar();

            String query = "INSERT INTO " + TABLA +"(fecha, tipo, dpto, encargado, observacion) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = db.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, movSuministro.getFecha());
            ps.setString(2, movSuministro.getTipo());
            ps.setString(3, movSuministro.getDpto());
            ps.setString(4, movSuministro.getEncargado());
            ps.setString(5, movSuministro.getObservacion());
            


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
    public boolean actualizarMovSuministro(MovSuministro movSuministro) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET " +
                    "fecha = ?, " +
                    "tipo = ? ," +
                    "dpto = ?, " +
                    "encargado = ?, " +
                    "observacion = ?, " +
                    "estado = ? " +
                    "WHERE id = ?";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setObject(1, movSuministro.getFecha());
            ps.setString(2, movSuministro.getTipo());
            ps.setString(3, movSuministro.getDpto());
            ps.setString(4, movSuministro.getEncargado());
            ps.setString(5, movSuministro.getObservacion());
            ps.setString(6, movSuministro.getEstado());
            ps.setInt(7, movSuministro.getId());

            int i = ps.executeUpdate();

            db.desconectar();

            return (i > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean cancelarMovimiento(int movSuministro_id) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET estado = 'Cancelado' WHERE id = " + movSuministro_id;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            int i = ps.executeUpdate();

            db.desconectar();

            return (i > 0);

        } catch (SQLException e) {
            System.out.println("getMovSuministro" + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean restablecerMovimiento(int movSuministro_id) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET estado = 'Realizado' WHERE id = " + movSuministro_id;

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

            String query ="SELECT * FROM " + TABLA +
                    " WHERE id = " + movSuministro_id;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            if (resultSet.next()){

                movSuministro.setId(resultSet.getInt("id"));
                movSuministro.setTipo(resultSet.getString("tipo"));
                movSuministro.setFecha(resultSet.getObject("fecha", LocalDate.class));
                movSuministro.setEstado(resultSet.getString("estado"));
                movSuministro.setDpto(resultSet.getString("dpto"));
                movSuministro.setEncargado(resultSet.getString("encargado"));
                movSuministro.setObservacion(resultSet.getString("observacion"));

                return movSuministro;
            }

        } catch (SQLException e) {
            System.out.println("getMovSuministro" + e.getMessage());
        }
        return null;
    }

    @Override
    public String getMovSuministroString(int movSuministro_id) {
        try {
            db.conectar();

            String query ="select movimiento_suministro.id as id, movimiento_suministro.fecha as fecha, producto.nombre as producto," +
                    "  detalle_suministrar.cantidad as cantidad, unidad_medida.nombre as unidad, movimiento_suministro.dpto as dpto," +
                    "  movimiento_suministro.encargado as encargado, movimiento_suministro.tipo as tipo" +
                    " from movimiento_suministro, detalle_suministrar, suministro, producto, unidad_medida" +
                    " where suministro.producto_id = producto.id and suministro.unidad_medida_id = unidad_medida.id" +
                    "  and detalle_suministrar.suministro_id = suministro.id" +
                    "  and detalle_suministrar.movimiento_suministro_id = movimiento_suministro.id and movimiento_suministro.estado = 'Realizado'" +
                    " and movimiento_suministro.id = " + movSuministro_id;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            if (resultSet.next()){
                String encargado = "-----";
                String dpto = "-----";
                
                if (resultSet.getString("encargado") != null) encargado = resultSet.getString("encargado");
                if (resultSet.getString("dpto") != null) dpto = resultSet.getString("dpto");

                return "ID: " + resultSet.getInt("id") +
                        "\nTipo: " + resultSet.getString("tipo") +
                        "\nFecha: " +resultSet.getObject("fecha",LocalDate.class) +
                        "\nSuministro: " + resultSet.getString("producto") +
                        "\nCant: " +resultSet.getInt("cantidad") +
                        "\nUM: " + resultSet.getString("unidad") +
                        "\nDpto: " + dpto +
                        "\nEncargado: " + encargado;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
