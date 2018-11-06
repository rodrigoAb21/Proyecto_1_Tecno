package negocio.interfaces;

import datos.modelos.DetalleSuministrar;

import java.time.LocalDate;
import java.util.List;

public interface DetalleSuministrarDAO {
    int registrarDetalle(DetalleSuministrar detalleSuministrar);
    boolean eliminarDetalle(int detalle_id);
    List<DetalleSuministrar> listarDetalle(int movimiento_id);
    List<DetalleSuministrar> listarDetalles(LocalDate fecha);
}
