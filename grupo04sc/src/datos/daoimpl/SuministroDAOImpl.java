package datos.daoimpl;

import datos.ConexionBD;
import datos.modelos.Suministro;
import negocio.interfaces.SuministroDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SuministroDAOImpl implements SuministroDAO {

    private final ConexionBD db;
    private static final String TABLA = "suministro";

    public SuministroDAOImpl() {
        db = new ConexionBD();
    }

    @Override
    public List<String> listarSuministros() {
        List<String> lista = new ArrayList<>();

        try {
            db.conectar();

            String query ="select movimiento_suministro.id as id, movimiento_suministro.fecha as fecha, producto.nombre as producto," +
                    "  detalle_suministrar.cantidad as cantidad, unidad_medida.nombre as unidad, movimiento_suministro.dpto as dpto," +
                    "  movimiento_suministro.encargado as encargado" +
                    " from movimiento_suministro, detalle_suministrar, suministro, producto, unidad_medida" +
                    " where suministro.producto_id = producto.id and suministro.unidad_medida_id = unidad_medida.id" +
                    "  and detalle_suministrar.suministro_id = suministro.id" +
                    "  and detalle_suministrar.movimiento_suministro_id = movimiento_suministro.id";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                String fila = "ID: " + resultSet.getInt("id") +", Fecha: " +resultSet.getObject("fecha",
                        LocalDate.class) + ", Suministro: " + resultSet.getString("producto") + ", Cant: " +
                        resultSet.getInt("cantidad") + ", UM: " + resultSet.getString("unidad") +
                        ", Dpto: " + resultSet.getString("dpto") + ", Encargado: " + resultSet.getString("encargado") ;

                lista.add(fila);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

    @Override
    public int registrarSuministro(Suministro suministro) {
        try {
            db.conectar();

            String query = "INSERT INTO " + TABLA +"(stock_minimo, stock_maximo, stock, producto_id) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = db.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, suministro.getStock_minimo());
            ps.setInt(2, suministro.getStock_maximo());
            ps.setInt(3, suministro.getStock());
            ps.setInt(4, suministro.getProducto_id());

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
    public boolean actualizarSuministro(Suministro suministro) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET " +
                    "stock_minimo = ?, " +
                    "stock_maximo = ?, " +
                    "stock = ?, " +
                    "producto_id = ? " +
                    "WHERE id = ?";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ps.setInt(1, suministro.getStock_minimo());
            ps.setInt(2, suministro.getStock_maximo());
            ps.setInt(3, suministro.getStock());
            ps.setInt(4, suministro.getProducto_id());
            ps.setInt(5, suministro.getId());

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

            String query ="SELECT * FROM " + TABLA + " WHERE id = " + suministro_id;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                Suministro suministro = new Suministro();
                suministro.setId(resultSet.getInt("id"));
                suministro.setStock_minimo(resultSet.getInt("stock_minimo"));
                suministro.setStock_maximo(resultSet.getInt("stock_maximo"));
                suministro.setStock(resultSet.getInt("stock"));
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
