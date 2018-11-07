package negocio.controladores.suministros;

import datos.daoimpl.*;
import datos.modelos.*;
import negocio.interfaces.*;

import java.time.LocalDate;
import java.util.List;

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

    public int ingresoNuevo(String nombre, String descripcion, int unidadM_id, int categoria_id, int stockMin,
                                int stockMax, int cantidad){
        // verificamos q exista la unidad de medida
        UnidadMedida unidadMedida = uniDAO.getUnidadMedida(unidadM_id);
        if (unidadMedida!= null){
            //verificamos q exista la categoria
            Categoria categoria = catDAO.getCategoria(categoria_id);
            if (categoria != null){
                //creamos un nuevo producto ya q es un ingreso "Nuevo"
                String codigo = nombre.substring(0,3) + "-" + categoria.getNombre().substring(0,3);
                Producto producto = new Producto();
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);
                producto.setCategoria_id(categoria_id);
                producto.setCodigo(codigo);

                int producto_id = prodDAO.registrarProducto(producto);
                if (producto_id > 0){
                    //si se creo bien el producto proseguimos a crear un suministro
                    Suministro suministro = new Suministro();
                    suministro.setProducto_id(producto_id);
                    suministro.setStock_minimo(stockMin);
                    suministro.setStock_maximo(stockMax);
                    suministro.setStock(cantidad);
                    suministro.setUnidad_medida_id(unidadM_id);

                    int suministro_id = sumiDAO.registrarSuministro(suministro);
                    if (suministro_id > 0){
                        //si se creo bien el suministro proseguimos a crear un movimiento

                        MovSuministro movimiento = new MovSuministro();
                        movimiento.setTipo("Ingreso");
                        movimiento.setFecha(LocalDate.now());


                        int mov_id = movDAO.registrarMovSuministro(movimiento);
                        if (mov_id > 0){
                            //si se creo bien el movimiento proseguimos en crear el detalle y retornamos su id
                            DetalleSuministrar detalle = new DetalleSuministrar();
                            detalle.setCantidad(cantidad);
                            detalle.setSuministro_id(suministro_id);
                            detalle.setMovimiento_suministro_id(mov_id);

                            if (detaDAO.registrarDetalle(detalle) > 0){
                                return mov_id;
                            }
                        }else {
                            //si falla debemos hacer un rollback
                        }
                    }else{
                        //si falla debemos hacer un rollback
                    }

                }else return -1;

            }
        }
        return -1;
    }

    public int ingreso(int suministro_id, int cantidad){
        MovSuministro movimiento = new MovSuministro();
        movimiento.setTipo("Ingreso");
        movimiento.setFecha(LocalDate.now());
        int mov_id = movDAO.registrarMovSuministro(movimiento);
        if (mov_id > 0){
            //si se creo bien el movimiento proseguimos en crear el detalle y retornamos su id
            DetalleSuministrar detalle = new DetalleSuministrar();
            detalle.setCantidad(cantidad);
            detalle.setSuministro_id(suministro_id);
            detalle.setMovimiento_suministro_id(mov_id);

            int detalle_id = detaDAO.registrarDetalle(detalle);
            if (detalle_id > 0){
                if (actualizarStock(suministro_id, cantidad))
                    return mov_id;
            }
        }
        return  -1;
    }


    int salida(int suministro_id, int cantidad, String encargado, String dpto, String observacion){
        MovSuministro movimiento = new MovSuministro();
        movimiento.setTipo("Salida");
        movimiento.setFecha(LocalDate.now());
        movimiento.setEncargado(encargado);
        movimiento.setDpto(dpto);
        movimiento.setObservacion(observacion);
        int mov_id = movDAO.registrarMovSuministro(movimiento);
        if (mov_id > 0){
            //si se creo bien el movimiento proseguimos en crear el detalle y retornamos su id
            DetalleSuministrar detalle = new DetalleSuministrar();
            detalle.setCantidad(cantidad);
            detalle.setSuministro_id(suministro_id);
            detalle.setMovimiento_suministro_id(mov_id);


            int detalle_id = detaDAO.registrarDetalle(detalle);
            if (detalle_id > 0){
                if (actualizarStock(suministro_id, (cantidad * -1)))
                    return mov_id;
            }
        }
        return -1;
    }


    int devolucion(int suministro_id, int cantidad, String encargado, String dpto, String observacion){
        MovSuministro movimiento = new MovSuministro();
        movimiento.setTipo("Devolucion");
        movimiento.setFecha(LocalDate.now());
        movimiento.setEncargado(encargado);
        movimiento.setDpto(dpto);
        movimiento.setObservacion(observacion);
        int mov_id = movDAO.registrarMovSuministro(movimiento);
        if (mov_id > 0){
            //si se creo bien el movimiento proseguimos en crear el detalle y retornamos su id
            DetalleSuministrar detalle = new DetalleSuministrar();
            detalle.setCantidad(cantidad);
            detalle.setSuministro_id(suministro_id);
            detalle.setMovimiento_suministro_id(mov_id);

            int detalle_id = detaDAO.registrarDetalle(detalle);
            if (detalle_id > 0){
                if (actualizarStock(suministro_id, cantidad))
                    return mov_id;
            }
        }
        return -1;
    }

    boolean actualizarStock(int suministro_id, int cantidad){
        Suministro suministro = sumiDAO.getSuministro(suministro_id);
        int nuevo = suministro.getStock() + cantidad;
        suministro.setStock(nuevo);
        if (sumiDAO.actualizarSuministro(suministro)){
            return true;
        }
        return false;
    }


    List<String> listarMovimientos(){
        List<String> lista = sumiDAO.listarSuministros();
        return lista;
    }


    boolean eliminarMovimiento(int id){
        MovSuministro movimiento = movDAO.getMovSuministro(id);
        DetalleSuministrar detalle = detaDAO.getDetalle(id);
        if (movimiento.getTipo().equals("Salida")){
            actualizarStock(detalle.getSuministro_id(), detalle.getCantidad());
            return movDAO.cancelarMovimiento(id);
        }else{
            actualizarStock(detalle.getSuministro_id(), detalle.getCantidad() * -1);
            return movDAO.cancelarMovimiento(id);
        }

    }

    boolean restablecerMovimiento(int id){
        MovSuministro movimiento = movDAO.getMovSuministro(id);
        DetalleSuministrar detalle = detaDAO.getDetalle(id);
        if (movimiento.getTipo().equals("Salida")){
            actualizarStock(detalle.getSuministro_id(), detalle.getCantidad() * -1);
            return movDAO.restablecerMovimiento(id);
        }else{
            actualizarStock(detalle.getSuministro_id(), detalle.getCantidad());
            return movDAO.restablecerMovimiento(id);
        }

    }










}
