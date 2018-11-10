package negocio.interfaces;

import datos.modelos.DetalleSuministrar;

public interface DetalleSuministrarDAO {
    int registrarDetalle(DetalleSuministrar detalleSuministrar);
    DetalleSuministrar getDetalle(int movimiento_id);
}
