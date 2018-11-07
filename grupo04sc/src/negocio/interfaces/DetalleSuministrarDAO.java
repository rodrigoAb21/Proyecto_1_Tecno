package negocio.interfaces;

import datos.modelos.DetalleSuministrar;

import java.time.LocalDate;
import java.util.List;

public interface DetalleSuministrarDAO {
    int registrarDetalle(DetalleSuministrar detalleSuministrar);
    DetalleSuministrar getDetalle(int movimiento_id);
}
