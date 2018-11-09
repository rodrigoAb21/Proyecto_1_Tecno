package negocio.interfaces;

import datos.modelos.MovSuministro;

import java.util.List;

public interface MovSuministroDAO {
    List<MovSuministro> listarMovimientos();
    int registrarMovSuministro(MovSuministro movSuministro);
    boolean actualizarMovSuministro(MovSuministro movSuministro);
    boolean cancelarMovimiento(int movSuministro_id);
    boolean restablecerMovimiento(int movSuministro_id);
    MovSuministro getMovSuministro(int movSuministro_id);
    String getMovSuministroString(int movSuministro_id);
}
