package negocio.interfaces;

import datos.modelos.Revaluo;

import java.util.List;

public interface RevaluoDAO {
    List<Revaluo> listarRevaluos();
    boolean registrarRevaluo(Revaluo revaluo);
    boolean actualizarRevaluo(Revaluo revaluo);
    boolean eliminarRevaluo(int revaluo_id);
    Revaluo getRevaluo(int revaluo_id);
}
