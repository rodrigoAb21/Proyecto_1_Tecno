package negocio.interfaces;

import datos.modelos.MovActivo;

import java.util.List;

public interface MovActivoDAO {
    List<MovActivo> listarSuministros();
    boolean registrarMovSuministro(MovActivo movActivo);
    boolean actualizarMovSuministro(MovActivo movActivo);
    boolean eliminarMovSuministro(int movActivo_id);
    MovActivo getMovSuministro(int movActivo_id);
}
