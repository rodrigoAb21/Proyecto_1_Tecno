package negocio.controladores.suministros;

import datos.daoimpl.UnidadMedidaDAOImpl;
import datos.modelos.UnidadMedida;
import negocio.interfaces.UnidadMedidaDAO;

public class UnidadMedidaController {

    private UnidadMedidaDAO dao;

    public UnidadMedidaController() {
        dao = new UnidadMedidaDAOImpl();
    }

    public boolean registrar(String nombre){
        return dao.registrarUnidadMedida(new UnidadMedida(nombre));
    }

}
