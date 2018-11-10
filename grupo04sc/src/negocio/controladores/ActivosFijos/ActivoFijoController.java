/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.controladores.ActivosFijos;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;
import datos.daoimpl.ActivoFijoDAOImpl;
import datos.daoimpl.CategoriaDAOImpl;
import datos.daoimpl.ProductoDAOImpl;
import datos.modelos.ActivoFijo;
import datos.modelos.Producto;
import negocio.interfaces.ActivoFijoDAO;
import negocio.interfaces.ProductoDAO;
import negocio.interfaces.CategoriaDAO;

/**
 *
 * @author ASUS
 */
public class ActivoFijoController {

    private ActivoFijoDAO daoActivo;
    private ProductoDAO daoProducto;
    private CategoriaDAO daoCategoria;

    public ActivoFijoController() {
        daoActivo = new ActivoFijoDAOImpl();
        daoProducto = new ProductoDAOImpl();
        daoCategoria = new CategoriaDAOImpl();
    }

    public int registrar(String nombre, int idCategoria, String descripcion, int cantidadActivos, String nroFactura, int costo) {
        if (daoCategoria.getCategoria(idCategoria) != null) {
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setCategoria_id(idCategoria);
            producto.setDescripcion(descripcion);
            producto.setCodigo(nombre.substring(0,3));
            int idProducto = daoProducto.registrarProducto(producto);
            if ( idProducto > 0) { 
                for (int i = 0; i < cantidadActivos; i++) {
                     ActivoFijo activo = new ActivoFijo();
                     activo.setProducto_id(idProducto);
                     activo.setVisible(true);
                     activo.setDisponible(true);
                     activo.setNroFactura(nroFactura);
                     activo.setCosto(costo);
                     activo.setCodigo("");
                     activo.setEstado("recien ingresado");
                     daoActivo.registrarActivoFijo(activo);
                }
                return  idProducto;
            }
        }
        return -1;
    }
    
    public int registrar( int idProducto, int cantidad, String nroFactura, int costo){
//        if(daoProducto.getProducto(i|)){
//            
//        }
            return 0;
    }

    public static void main(String[] args) {
            System.out.println(new ActivoFijoController().registrar( "kokokokokoko", 9, "kkkkkkkkkkkke", 3,"010",10));
    }
}
