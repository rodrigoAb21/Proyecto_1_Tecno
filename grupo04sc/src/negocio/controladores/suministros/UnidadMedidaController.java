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


    public int registrar(String nombre){
        return dao.registrarUnidadMedida(new UnidadMedida(nombre));
    }

    public boolean editar(int id, String nuevoNombre){
       UnidadMedida unidadMedida = dao.getUnidadMedida(id);
       if (unidadMedida != null){
           unidadMedida.setNombre(nuevoNombre);
           return dao.actualizarUnidadMedida(unidadMedida);
       }
       return false;
    }

    public boolean eliminar(int id) {
        return dao.eliminarUnidadMedida(id);
    }

    public List<UnidadMedida> listarUnidades(){
        return dao.listarUnidadesM();
    }

    public UnidadMedida getUnidadMedida(String nombre){
        return dao.getUnidadMedida(nombre);
    }

    public UnidadMedida getUnidadMedida(int id){
        return dao.getUnidadMedida(id);
    }
}
