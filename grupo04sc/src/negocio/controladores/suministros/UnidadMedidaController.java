package negocio.controladores.suministros;

import datos.daoimpl.UnidadMedidaDAOImpl;
import datos.modelos.UnidadMedida;
import negocio.interfaces.UnidadMedidaDAO;

import java.util.List;

public class UnidadMedidaController {

    private UnidadMedidaDAO dao;

    public UnidadMedidaController() {
        dao = new UnidadMedidaDAOImpl();
    }


    public boolean registrar(String nombre){
        return dao.registrarUnidadMedida(new UnidadMedida(nombre));
    }

    public boolean editar(String antNombre, String nuevoNombre){
       UnidadMedida unidadMedida = dao.getUnidadMedida(antNombre);
       if (unidadMedida != null){
           unidadMedida.setNombre(nuevoNombre);
           return dao.actualizarUnidadMedida(unidadMedida);
       }
       return false;
    }

    public boolean eliminar(String nombre) {
        UnidadMedida unidadMedida = dao.getUnidadMedida(nombre);
        if (unidadMedida != null){
            return dao.eliminarUnidadMedida(unidadMedida.getId());
        }
        return false;
    }

    public List<UnidadMedida> listarUnidades(){
        return dao.listarUnidadesM();
    }
}
