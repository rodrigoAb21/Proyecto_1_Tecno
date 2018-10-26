package negocio.interfaces;

import datos.modelos.UnidadMedida;

import java.util.List;

public interface UnidadMedidaDAO {
    List<UnidadMedida> listarUnidadesM();
    boolean registrarUnidadMedida(UnidadMedida unidadMedida);
    boolean actualizarUnidadMedida(UnidadMedida unidadMedida);
    boolean eliminarUnidadMedida(int unidadM_id);
    UnidadMedida getUnidadMedida(int unidadM_id);
}
