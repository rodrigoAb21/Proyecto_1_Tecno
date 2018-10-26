package negocio.interfaces;

import datos.modelos.MovSuministro;

import java.util.List;

public interface MovSuministroDAO {
    List<MovSuministro> listarSuministros();
    boolean registrarMovSuministro(MovSuministro movSuministro);
    boolean actualizarMovSuministro(MovSuministro movSuministro);
    boolean eliminarMovSuministro(int movSuministro_id);
    MovSuministro getMovSuministro(int movSuministro_id);
}
