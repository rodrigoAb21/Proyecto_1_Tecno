package negocio.controladores.suministros;

import datos.daoimpl.*;
import datos.modelos.Categoria;
import datos.modelos.MovSuministro;
import datos.modelos.Producto;
import datos.modelos.UnidadMedida;
import negocio.interfaces.*;

import java.time.LocalDate;
import java.util.Date;

public class SuministroController {
    private ProductoDAO prodDAO;
    private SuministroDAO sumiDAO;
    private DetalleSuministrarDAO detaDAO;
    private CategoriaDAO catDAO;
    private UnidadMedidaDAO uniDAO;
    private MovSuministroDAO movDAO;

    public SuministroController() {
        prodDAO = new ProductoDAOImpl();
        sumiDAO = new SuministroDAOImpl();
        detaDAO = new DetalleSuministrarDAOImpl();
        movDAO = new MovSuministroDAOImpl();
        catDAO = new CategoriaDAOImpl();
        uniDAO = new UnidadMedidaDAOImpl();
    }

    public boolean ingresoNuevo(String nombre, String descripcion, int unidadM_id, int categoria_id, int stockMin,
                                int stockMax, int cantidad){
        UnidadMedida unidadMedida = uniDAO.getUnidadMedida(unidadM_id);
        if (unidadMedida!= null){
            Categoria categoria = catDAO.getCategoria(categoria_id);
            if (categoria != null){
                String codigo = nombre.substring(0,2) + categoria.getNombre().substring(0,2);

                Producto producto = new Producto();
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);
                producto.setCategoria_id(categoria_id);
                producto.setCodigo(codigo);
                if (prodDAO.registrarProducto(producto)){
//                    producto = prodDAO.getProducto(nombre);
                }else return false;


                MovSuministro movimiento = new MovSuministro();
                movimiento.setTipo("Ingreso");
                movimiento.setFecha(LocalDate.now());

                if(movDAO.registrarMovSuministro(movimiento)){

                }else {

                }


            }
        }
        return false;
    }
}
