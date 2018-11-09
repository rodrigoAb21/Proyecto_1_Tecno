package negocio.interfaces;

import datos.modelos.Suministro;

import java.util.List;

public interface SuministroDAO {
    List<String> listarMovimientosRealizados();
    List<String> listarMovimientosCancelados();
    List<String> listarSuministros();
    int registrarSuministro(Suministro suministro);
    boolean actualizarSuministro(Suministro suministro);
    boolean eliminarSuministro(int suministro_id);
    Suministro getSuministro(int suministro_id);
    String getSumiString(int sum_id);
    String getMovString(int mov_id);
}
