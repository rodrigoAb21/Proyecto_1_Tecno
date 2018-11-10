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
    public List<String> listarMovimientosRealizados() {
        List<String> lista = new ArrayList<>();

        try {
            db.conectar();

            String query ="select movimiento_suministro.id as id, movimiento_suministro.fecha as fecha, producto.nombre as producto," +
                    "  detalle_suministrar.cantidad as cantidad, unidad_medida.nombre as unidad, movimiento_suministro.dpto as dpto," +
                    "  movimiento_suministro.encargado as encargado, movimiento_suministro.tipo as tipo" +
                    " from movimiento_suministro, detalle_suministrar, suministro, producto, unidad_medida" +
                    " where suministro.producto_id = producto.id and suministro.unidad_medida_id = unidad_medida.id" +
                    "  and detalle_suministrar.suministro_id = suministro.id" +
                    "  and detalle_suministrar.movimiento_suministro_id = movimiento_suministro.id and movimiento_suministro.estado = 'Realizado'" +
                    " ORDER BY (movimiento_suministro.id) asc";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){
                String encargado = "-----";
                String dpto = "-----";

                if (resultSet.getString("encargado") != null) encargado = resultSet.getString("encargado");
                if (resultSet.getString("dpto") != null) dpto = resultSet.getString("encargado");


                String fila = "ID: " + resultSet.getInt("id") + ",  Tipo: " + resultSet.getString("tipo") + ",  Fecha: " +resultSet.getObject("fecha",
                        LocalDate.class) + ", Suministro: " + resultSet.getString("producto") + ", Cant: " +
                        resultSet.getInt("cantidad") + ", UM: " + resultSet.getString("unidad") +
                        ", Dpto: " + dpto + ", Encargado: " + encargado ;

                lista.add(fila);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

    @Override
    public List<String> listarMovimientosCancelados() {
        List<String> lista = new ArrayList<>();

        try {
            db.conectar();

            String query ="select movimiento_suministro.id as id, movimiento_suministro.fecha as fecha, producto.nombre as producto," +
                    "  detalle_suministrar.cantidad as cantidad, unidad_medida.nombre as unidad, movimiento_suministro.dpto as dpto," +
                    "  movimiento_suministro.encargado as encargado, movimiento_suministro.tipo as tipo" +
                    " from movimiento_suministro, detalle_suministrar, suministro, producto, unidad_medida" +
                    " where suministro.producto_id = producto.id and suministro.unidad_medida_id = unidad_medida.id" +
                    "  and detalle_suministrar.suministro_id = suministro.id" +
                    "  and detalle_suministrar.movimiento_suministro_id = movimiento_suministro.id and movimiento_suministro.estado = 'Cancelado' " +
                    " ORDER BY (movimiento_suministro.id) asc ";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                String encargado = "-----";
                String dpto = "-----";

                if (resultSet.getString("encargado") != null) encargado = resultSet.getString("encargado");
                if (resultSet.getString("dpto") != null) dpto = resultSet.getString("dpto");

                String fila = "ID: " + resultSet.getInt("id") + ",Tipo: " + resultSet.getString("tipo") + ",  Fecha: " +resultSet.getObject("fecha",
                        LocalDate.class) + ", Suministro: " + resultSet.getString("producto") + ", Cant: " +
                        resultSet.getInt("cantidad") + ", UM: " + resultSet.getString("unidad") +
                        ", Dpto: " + dpto + ", Encargado: " + encargado;

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

            String query = "INSERT INTO " + TABLA +"(stock_minimo, stock_maximo, stock, producto_id, unidad_medida_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = db.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, suministro.getStock_minimo());
            ps.setInt(2, suministro.getStock_maximo());
            ps.setInt(3, suministro.getStock());
            ps.setInt(4, suministro.getProducto_id());
            ps.setInt(5, suministro.getUnidad_medida_id());

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
            System.out.println("actualizarSuministro" + e.getMessage());
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
            System.out.println("getSuministro" + e.getMessage());
        }

        return null;
    }

    @Override
    public String getSumiString(int sum_id) {
        try {
            db.conectar();

            String query ="SELECT suministro.id as id, producto.nombre as nombre, suministro.stock as stock, " +
                    "suministro.stock_minimo as minimo,suministro.stock_maximo  as maximo, unidad_medida.nombre " +
                    "as unidad, categoria.nombre as categoria, producto.descripcion as descripcion " +
                    "FROM producto,suministro, categoria, unidad_medida  " +
                    "WHERE suministro.id = "+ sum_id +" and suministro.producto_id = producto.id and suministro.unidad_medida_id = unidad_medida.id " +
                    "and producto.categoria_id = categoria.id";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            if (resultSet.next()){

                return "ID: " + resultSet.getInt("id") +
                        "\nNombre: " + resultSet.getString("nombre") +
                        "\nStock: "+resultSet.getInt("stock") +
                        "\nMin: " + resultSet.getInt("minimo") +
                        "\nMax: " + resultSet.getInt("maximo") +
                        "\nUM: " + resultSet.getString("unidad") +
                        "\nCategoria: " + resultSet.getString("categoria") +
                        "\nDescripcion: " + resultSet.getString("descripcion");

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<String> listarSuministros() {
        List<String> lista = new ArrayList<>();

        try {
            db.conectar();

            String query ="SELECT suministro.id as id, producto.nombre as nombre, suministro.stock as stock, " +
                    "suministro.stock_minimo as minimo,suministro.stock_maximo  as maximo, unidad_medida.nombre " +
                    "as unidad, categoria.nombre as categoria " +
                    "FROM producto,suministro, categoria, unidad_medida  " +
                    "WHERE suministro.producto_id = producto.id and suministro.unidad_medida_id = unidad_medida.id " +
                    "and producto.categoria_id = categoria.id and suministro.visible = true " +
                    "ORDER BY (suministro.id) asc ";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                String fila = "ID: " + resultSet.getInt("id") + ",Nombre: " + resultSet.getString("nombre") + ",  Stock: "
                        +resultSet.getInt("stock") + ", Min: " + resultSet.getInt("minimo") + ", Max: " +
                        resultSet.getInt("maximo") + ", UM: " + resultSet.getString("unidad") +
                        ", Categoria: " + resultSet.getString("categoria");

                lista.add(fila);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

    @Override
    public String getMovString(int mov_id) {
        try {
            db.conectar();

            String query ="select movimiento_suministro.id as id, movimiento_suministro.fecha as fecha, producto.nombre as producto," +
                    "  detalle_suministrar.cantidad as cantidad, unidad_medida.nombre as unidad, movimiento_suministro.dpto as dpto," +
                    "  movimiento_suministro.encargado as encargado, movimiento_suministro.tipo as tipo, movimiento_suministro.observacion as observacion, movimiento_suministro.estado as estado" +
                    " from movimiento_suministro, detalle_suministrar, suministro, producto, unidad_medida" +
                    " where movimiento_suministro.id = " + mov_id +" and suministro.producto_id = producto.id and suministro.unidad_medida_id = unidad_medida.id" +
                    "  and detalle_suministrar.suministro_id = suministro.id" +
                    "  and detalle_suministrar.movimiento_suministro_id = movimiento_suministro.id";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            if (resultSet.next()){
                String obs = "-----";
                String encargado = "-----";
                String dpto = "-----";

                if (resultSet.getString("observacion") != null) obs = resultSet.getString("observacion");
                if (resultSet.getString("encargado") != null) encargado = resultSet.getString("encargado");
                if (resultSet.getString("dpto") != null) dpto = resultSet.getString("dpto");

                return "ID: " + resultSet.getInt("id") +
                        "\nFecha: "+resultSet.getObject("fecha",LocalDate.class) +
                        "\nTipo: " + resultSet.getString("tipo") +
                        "\nSuministro: " + resultSet.getString("producto") +
                        "\nCantidad: " + resultSet.getInt("cantidad") +
                        "\nUM: " + resultSet.getString("unidad") +
                        "\nDpto: " + dpto +
                        "\nEncargado: " + encargado +
                        "\nEstado: " + resultSet.getString("estado") +
                        "\nObservacion: " + obs;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
