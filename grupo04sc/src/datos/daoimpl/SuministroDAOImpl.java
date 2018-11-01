package datos.daoimpl;

import datos.ConexionBD;
import datos.modelos.Suministro;
import negocio.interfaces.SuministroDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuministroDAOImpl implements SuministroDAO {

    private final ConexionBD db;
    private static final String TABLA = "suministro";

    public SuministroDAOImpl() {
        db = new ConexionBD();
    }

    @Override
    public List<Suministro> listarSuministros() {
        List<Suministro> suministros = new ArrayList<>();

        try {
            db.conectar();

            String query ="SELECT " +
                    "id, " +
                    "stock_minimo, " +
                    "stock_maximo, " +
                    "producto_id, " +
                    "visible " +
                    "FROM " + TABLA;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                Suministro suministro = new Suministro();
                suministro.setId(resultSet.getInt("id"));
                suministro.setStock_minimo(resultSet.getInt("stock_minimo"));
                suministro.setStock_maximo(resultSet.getInt("stock_maximo"));
                suministro.setProducto_id(resultSet.getInt("producto_id"));
                suministro.setVisible(resultSet.getBoolean("visible"));

                suministros.add(suministro);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return suministros;
    }

    @Override
    public boolean registrarSuministro(Suministro suministro) {
        try {
            db.conectar();

            String query = "INSERT INTO " + TABLA +"(stock_minimo, stock_maximo, producto_id) VALUES (?, ?, ?)";
            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setInt(1, suministro.getStock_minimo());
            ps.setInt(2, suministro.getStock_maximo());
            ps.setInt(3, suministro.getProducto_id());

            int i = ps.executeUpdate();

            db.desconectar();

            return (i > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean actualizarSuministro(Suministro suministro) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET " +
                    "stock_minimo = ?, " +
                    "stock_maximo = ?, " +
                    "producto_id = ?, " +
                    "WHERE id = ?";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setInt(1, suministro.getStock_minimo());
            ps.setInt(2, suministro.getStock_maximo());
            ps.setInt(3, suministro.getProducto_id());
            ps.setInt(4, suministro.getId());

            int i = ps.executeUpdate();

            db.desconectar();

            return (i > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarSuministro(int suministro_id) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET visible = false WHERE id = " + suministro_id;

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
    public Suministro getSuministro(int suministro_id) {
        try {
            db.conectar();

            String query ="SELECT " +
                    "id, " +
                    "nombre, " +
                    "tipo, " +
                    "codigo, " +
                    "cantidad, " +
                    "visible " +
                    "FROM " + TABLA + " WHERE id = " + suministro_id;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                Suministro suministro = new Suministro();
                suministro.setId(resultSet.getInt("id"));
                suministro.setStock_minimo(resultSet.getInt("stock_minimo"));
                suministro.setStock_maximo(resultSet.getInt("stock_maximo"));
                suministro.setProducto_id(resultSet.getInt("producto_id"));
                suministro.setVisible(resultSet.getBoolean("visible"));

                return suministro;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
