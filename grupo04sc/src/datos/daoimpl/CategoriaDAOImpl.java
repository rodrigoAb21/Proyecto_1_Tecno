package datos.daoimpl;

import datos.ConexionBD;
import datos.modelos.Categoria;
import negocio.interfaces.CategoriaDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO {

    private final ConexionBD db;
    private static final String TABLA = "categoria";

    public CategoriaDAOImpl() {
        db = new ConexionBD();
    }


    @Override
    public List<Categoria> listarCategorias() {
        List<Categoria> categorias = new ArrayList<>();

        try {
            db.conectar();

            String query ="SELECT " +
                    "id, " +
                    "nombre, " +
                    "codigo, " +
                    "categoria_sup, " +
                    "visible " +
                    "FROM " + TABLA + " WHERE visible = true ORDER BY (categoria.id) asc";

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                Categoria categoria = new Categoria();
                categoria.setId(resultSet.getInt("id"));
                categoria.setNombre(resultSet.getString("nombre"));
                categoria.setCodigo(resultSet.getString("codigo"));
                categoria.setVisible(resultSet.getBoolean("visible"));

                categorias.add(categoria);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return categorias;
    }

    @Override
    public int registrarCategoria(Categoria categoria) {
        try {
            db.conectar();

            if (categoria.getCategoria_sup() > 0){
                String query = "INSERT INTO " + TABLA +"(nombre, codigo, categoria_sup) VALUES (?, ?, ?)";
                PreparedStatement ps = db.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, categoria.getNombre());
                ps.setString(2, categoria.getCodigo());
                ps.setInt(3, categoria.getCategoria_sup());

                int i = ps.executeUpdate();
                db.desconectar();
                if (i > 0){
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }
            }else {
                String query = "INSERT INTO " + TABLA +"(nombre, codigo) VALUES (?, ?)";
                PreparedStatement ps = db.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, categoria.getNombre());
                ps.setString(2, categoria.getCodigo());

                int i = ps.executeUpdate();

                db.desconectar();

                if (i > 0){
                  ResultSet generateKeys = ps.getGeneratedKeys();
                  if (generateKeys.next()) {
                      return generateKeys.getInt(1);
                  }
                }
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public boolean actualizarCategoria(Categoria categoria) {
        try {
            db.conectar();

            if (categoria.getCategoria_sup() > 0){
                String query ="UPDATE " + TABLA + " SET " +
                        "nombre = ?, " +
                        "codigo = ?, " +
                        "categoria_sup = ? " +
                        "WHERE id = ?";

                PreparedStatement ps = db.getConexion().prepareStatement(query);
                ps.setString(1, categoria.getNombre());
                ps.setString(2, categoria.getCodigo());
                ps.setInt(3, categoria.getCategoria_sup());
                ps.setInt(4, categoria.getId());

                int i = ps.executeUpdate();

                db.desconectar();

                return (i > 0);
            } else {
                String query ="UPDATE " + TABLA + " SET " +
                        "nombre = ?, " +
                        "codigo = ? " +
                        "WHERE id = ?";

                PreparedStatement ps = db.getConexion().prepareStatement(query);
                ps.setString(1, categoria.getNombre());
                ps.setString(2, categoria.getCodigo());
                ps.setInt(3, categoria.getId());

                int i = ps.executeUpdate();

                db.desconectar();

                return (i > 0);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarCategoria(int categoria_id) {
        try {
            db.conectar();

            String query ="UPDATE " + TABLA + " SET visible = false WHERE id = " + categoria_id;

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
    public Categoria getCategoria(int categoria_id) {
        try {
            db.conectar();

            Categoria categoria = new Categoria();

            String query ="SELECT " +
                    "nombre, " +
                    "codigo, " +
                    "categoria_sup, " +
                    "visible " +
                    "FROM " + TABLA +
                    " WHERE id = " + categoria_id;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            if (resultSet.next()){

                categoria.setId(categoria_id);
                categoria.setNombre(resultSet.getString("nombre"));
                categoria.setCodigo(resultSet.getString("codigo"));
                categoria.setCategoria_sup(resultSet.getInt("categoria_sup"));
                categoria.setVisible(resultSet.getBoolean("visible"));

                return categoria;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public static void main(String[] args) {
        CategoriaDAO c = new CategoriaDAOImpl();
        Categoria cc = new Categoria("Limpieza", "RR", 2);
        cc.setId(1);
        System.out.println(c.eliminarCategoria(2));
    }
}
