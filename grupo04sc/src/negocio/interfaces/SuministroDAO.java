package negocio.interfaces;

import datos.modelos.Suministro;

import java.util.List;

public interface SuministroDAO {
    List<String> listarSuministrosRealizados();
    List<String> listarSuministrosCancelados();
    int registrarSuministro(Suministro suministro);
    boolean actualizarSuministro(Suministro suministro);
    boolean eliminarSuministro(int suministro_id);
    Suministro getSuministro(int suministro_id);
}
