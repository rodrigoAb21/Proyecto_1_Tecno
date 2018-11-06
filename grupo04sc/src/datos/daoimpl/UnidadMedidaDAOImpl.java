package datos.daoimpl;

import datos.ConexionBD;
import datos.modelos.UnidadMedida;
import negocio.interfaces.UnidadMedidaDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UnidadMedidaDAOImpl implements UnidadMedidaDAO {

    private final ConexionBD db;
    private static final String TABLA = "unidad_medida";

    public UnidadMedidaDAOImpl() {
        db = new ConexionBD();
    }

    @Override
    public List<UnidadMedida> listarUnidadesM() {
        List<UnidadMedida> unidades = new ArrayList<>();

        try {
            db.conectar();

            String query ="SELECT " +
                    "id, " +
                    "nombre, " +
                    "visible " +
                    "FROM " + TABLA + " WHERE visible = true ORDER BY id ASC";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                UnidadMedida unidadMedida = new UnidadMedida();
                unidadMedida.setId(resultSet.getInt("id"));
                unidadMedida.setNombre(resultSet.getString("nombre"));
                unidadMedida.setVisible(resultSet.getBoolean("visible"));

                unidades.add(unidadMedida);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return unidades;
    }

    @Override
    public int registrarUnidadMedida(UnidadMedida unidadMedida) {
        try {
            db.conectar();

            String query = "INSERT INTO " + TABLA +"(nombre) VALUES (?)";
            PreparedStatement ps = db.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, unidadMedida.getNombre());

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
    public boolean actualizarUnidadMedida(UnidadMedida unidadMedida) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET " +
                    "nombre = ? " +
                    "WHERE id = ?";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setString(1, unidadMedida.getNombre());
            ps.setInt(2, unidadMedida.getId());

            int i = ps.executeUpdate();

            db.desconectar();

            return (i > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarUnidadMedida(int unidadM_id) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET visible = false WHERE id = " + unidadM_id;

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
    public UnidadMedida getUnidadMedida(int unidadM_id) {
        try {
            db.conectar();

            UnidadMedida unidadMedida = new UnidadMedida();

            String query ="SELECT " +
                    "nombre, " +
                    "visible " +
                    "FROM " + TABLA +
                    " WHERE id = " + unidadM_id + " AND visible = true ";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            if (resultSet.next()){

                unidadMedida.setId(unidadM_id);
                unidadMedida.setNombre(resultSet.getString("nombre"));
                unidadMedida.setVisible(resultSet.getBoolean("visible"));

                return unidadMedida;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public UnidadMedida getUnidadMedida(String nombre) {
        try {
            db.conectar();

            UnidadMedida unidadMedida = new UnidadMedida();

            String query ="SELECT " +
                    "id, " +
                    "nombre, " +
                    "visible " +
                    "FROM " + TABLA +
                    " WHERE nombre = \'" + nombre + "\'  AND visible = true ";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            if (resultSet.next()){

                unidadMedida.setId(resultSet.getInt("id"));
                unidadMedida.setNombre(resultSet.getString("nombre"));
                unidadMedida.setVisible(resultSet.getBoolean("visible"));

                return unidadMedida;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
