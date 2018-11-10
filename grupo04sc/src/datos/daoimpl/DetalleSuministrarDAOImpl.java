package datos.daoimpl;

import datos.ConexionBD;
import datos.modelos.DetalleSuministrar;
import negocio.interfaces.DetalleSuministrarDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetalleSuministrarDAOImpl implements DetalleSuministrarDAO {

    private final ConexionBD db;
    private static final String TABLA = "detalle_suministrar";

    public DetalleSuministrarDAOImpl(){
        db = new ConexionBD();
    }


    @Override
    public int registrarDetalle(DetalleSuministrar detalle) {
        try{
            db.conectar();

            String query = "INSERT INTO " + TABLA +"(cantidad, suministro_id, movimiento_suministro_id) VALUES (?, ?, ?)";
            PreparedStatement ps = db.getConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, detalle.getCantidad());
            ps.setInt(2, detalle.getSuministro_id());
            ps.setInt(3, detalle.getMovimiento_suministro_id());

            int i = ps.executeUpdate();
            db.desconectar();
            if (i > 0){
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public DetalleSuministrar getDetalle(int mov_id) {
        try {
            db.conectar();

            String query ="SELECT * " +
                    "FROM " + TABLA + " WHERE movimiento_suministro_id = " + mov_id;

            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            db.desconectar();

            while (resultSet.next()){

                DetalleSuministrar detalle = new DetalleSuministrar();
                detalle.setId(resultSet.getInt("id"));
                detalle.setCantidad(resultSet.getInt("cantidad"));
                detalle.setMovimiento_suministro_id(resultSet.getInt("movimiento_suministro_id"));
                detalle.setSuministro_id(resultSet.getInt("suministro_id"));

                return detalle;
            }

        } catch (SQLException e) {
            System.out.println("getDetalle " + e.getMessage());
        }

        return null;
    }

    
}
