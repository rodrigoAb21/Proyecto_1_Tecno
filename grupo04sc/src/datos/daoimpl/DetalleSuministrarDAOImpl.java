package datos.daoimpl;

import datos.modelos.DetalleSuministrar;
import negocio.interfaces.DetalleSuministrarDAO;

import java.time.LocalDate;
import java.util.List;

public class DetalleSuministrarDAOImpl implements DetalleSuministrarDAO {

    public DetalleSuministrarDAOImpl() {
    }

    @Override
    public int registrarDetalle(DetalleSuministrar detalleSuministrar) {
        return -1;
    }

    @Override
    public boolean eliminarDetalle(int detalle_id) {
        return false;
    }

    @Override
    public List<DetalleSuministrar> listarDetalle(int movimiento_id) {
        return null;
    }

    @Override
    public List<DetalleSuministrar> listarDetalles(LocalDate fecha) {
        return null;
    }
}
