package negocio.interfaces;

import datos.modelos.ActivoFijo;

import java.util.List;

public interface ActivoFijoDAO {
    List<ActivoFijo> listarActivoFijos();
    int registrarActivoFijo(ActivoFijo activoFijo);
    boolean actualizarActivoFijo(ActivoFijo activoFijo);
    boolean eliminarActivoFijo(int activoFijo_id);
    ActivoFijo getActivoFijo(int activoFijo_id);
}
