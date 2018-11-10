package utilidades;

import datos.modelos.Categoria;
import datos.modelos.UnidadMedida;
import java.util.List;
import negocio.controladores.suministros.UnidadMedidaController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import negocio.controladores.CategoriaController.CategoriaController;

public class ProcesadorMensaje {

    public ProcesadorMensaje() {
    }

    public void procesar(Mensaje mensaje) {

        String texto = "(\\s*[^:_]+\\s*)+//$";
        String textoLetras = "\\s*[a-zA-Z]+\\s*";
        String numero = "\\s*[0-9]+\\s*//$";
        String numeroM1 = "\\s*[1-9][0-9]*\\s*//$";
        String correo = "\\s*[^@_\\s]+@[^@]+\\.[a-zA-Z]{2,}\\s*//$";
        String esp = "\\s*";

        String mensajeI = mensaje.getAsunto();

        if (Pattern.matches(textoLetras + ":.*", mensajeI)) {
            System.out.println("entro");
            String comandoI = mensajeI.substring(0, mensajeI.indexOf(":")).toLowerCase().trim();
            String parametroI = mensajeI.substring(mensajeI.indexOf(":") + 1);

            String parametroC;
            System.out.println(comandoI);
            switch (comandoI) {

                //------------------- CU: GESTIONAR CATEGORIA ----------------------------------------
                
                case ("registrarCategoria"):

                    parametroC = texto;
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        String nombreCat = parametros[0];
                        int id = new CategoriaController().registrar(nombreCat);
                         if (id > 0) {
                            //respuesta.setAsunto("Se creó la categoria.");
                            //respuesta.setMensaje(Respuesta.categoria(new CategoriaController()
                            //        .getCategoria(id)));
                        } else {
                            //respuesta.setAsunto("No se pudo crear la subcategoria");
                        }
                    } else {
                        //respuesta.setAsunto("Parametros incorrectos al registrar la categoria.");
                        //respuesta.setMensaje(Ejemplo.RegistrarCategoria);
                    }
                    //new ClienteMail().enviar(respuesta);
                    break;

                case ("editarCategoria"):

                    parametroC = numeroM1 + texto ;
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int id = Integer.parseInt(parametros[0]);
                        String nombre = parametros[1];
                        if (new CategoriaController().editar(id, nombre)) {
                            //respuesta.setAsunto("Se edito la categoria.");
                        } else {
                            //respuesta.setAsunto("No se pudo editar la categoria");
                        }
                    } else {
                        //respuesta.setAsunto("Parametros incorrectos al editar la categoria.");
                        //respuesta.setMensaje(Ejemplo.EditarCategoria);
                    }
                    //new ClienteMail().enviar(respuesta);
                    break;

                case ("crearSubcategoria"):

                    parametroC = numeroM1 + texto;
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int idC = Integer.parseInt(parametros[0]);
                        String nombreSub = parametros[1];
                        int id = new CategoriaController().crearSubcategoria(idC, nombreSub);
                        if (id > 0) {
                            //respuesta.setAsunto("Se creó la subcategoria.");
                            //respuesta.setMensaje(Respuesta.categoria(new CategoriaController()
                            //        .getCategoria(id)));
                        } else {
                            //respuesta.setAsunto("No se pudo crear la subcategoria");
                        }
                    } else {
                        //respuesta.setAsunto("Parametros incorrectos al crear la subcategoria.");
                        //respuesta.setMensaje(Ejemplo.CrearSubcategoria);
                    }
                    //new ClienteMail().enviar(respuesta);
                    break;

                case ("agregarSubcategoria"):

                    parametroC = numeroM1 + numeroM1 ;
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int idSup = Integer.parseInt(parametros[0]);
                        int idSub = Integer.parseInt(parametros[1]);
                        if (new CategoriaController().agregarSubcategoria(idSup, idSub)) {
                            //respuesta.setAsunto("La subcategoria se agregó.");
                        } else {
                            //respuesta.setAsunto("No se pudo agregar la subcategoria");
                        }
                    } else {
                        //respuesta.setAsunto("Parametros incorrectos al agregar la subcategoria.");
                        //respuesta.setMensaje(Ejemplo.AgregarSubcategoria);
                    }
                    //new ClienteMail().enviar(respuesta);
                    break;

                case ("quitarSubcategoria"):

                    parametroC = numeroM1 + numeroM1 ;
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int idSup = Integer.parseInt(parametros[0]);
                        int idSub = Integer.parseInt(parametros[1]);
                        if (new CategoriaController().agregarSubcategoria(idSup, idSub)) {
                            //respuesta.setAsunto("La subcategoria se agregó.");
                        } else {
                            //respuesta.setAsunto("No se pudo agregar la subcategoria");
                        }
                    } else {
                        //respuesta.setAsunto("Parametros incorrectos al agregar la subcategoria.");
                        //respuesta.setMensaje(Ejemplo.AgregarSubcategoria);
                    }
                    //new ClienteMail().enviar(respuesta);
                    break;

                case ("eliminarCategoria"):

                    parametroC = numeroM1 ;
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int id = Integer.parseInt(parametros[0]);
                        if (new CategoriaController().eliminar(id)) {
                            //respuesta.setAsunto("Se elimino la categoria.");
                        } else {
                            //respuesta.setAsunto("No se pudo eliminar la categoria");
                        }
                    } else {
                        //respuesta.setAsunto("Parametros incorrectos al eliminar la categoria.");
                        //respuesta.setMensaje(Ejemplo.EliminarCategoria);
                    }
                    //new ClienteMail().enviar(respuesta);
                    break;

                case ("listarCategorias"):
                    List<Categoria> categorias = new CategoriaController().listarCategorias();
                    //respuesta.setAsunto("Lista de categorias.");
                    if (categorias.size() > 0) {
                        //respuesta.setMensaje(Respuesta.listaCategorias(categorias));
                    } else {
                        //respuesta.setMensaje("Lista vacia.");
                    }
                    break;
                    
                //--------------------- CU: REGISTRAR INGRESO DE ACTIVOS FIJOS ----------------------- 
                    
                case ("ingresarActivoFijo"):
                    parametroC = texto + numeroM1 + numeroM1 + texto + numero + numeroM1;
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int nombre = Integer.parseInt(parametros[0]);;
                        int cantidad = Integer.parseInt(parametros[1]);
                        int idCategoria = Integer.parseInt(parametros[2]);
                        String descripcion = parametros[3];
                        String nroFactura = parametros[4];
                        int costo = Integer.parseInt(parametros[5]);
                        //
                    }else{
                        parametroC = numeroM1 + numeroM1 + numero + numeroM1;
                        if (Pattern.matches(parametroC, parametroI)) {
                            String[] parametros = parametroI.split("[//]");
                            int idActivo = Integer.parseInt(parametros[0]);
                            int cantidad = Integer.parseInt(parametros[1]);
                            String nroFactura = parametros[2];
                            int costo = Integer.parseInt(parametros[3]);
                            //

                        }else{
                            //respuesta.setAsunto("Parametros incorrectos...");
                            //respuesta.setMensaje(Ejemp);
                        }
                    }
                    //new ClienteMail().enviar(respuesta);
                    break;
                    
                    //--------------- CU: GESTIONAR ACTIVOS FIJOS ------------------
                 
                case ("editarActivoFijo"):
                    parametroC = numeroM1 + texto + texto + texto ;
                    if (Pattern.matches(parametroC, parametroI)){
                        String[] parametros = parametroI.split("[//]");
                        int idActivo = Integer.parseInt(parametros[0]);
                        String nombreNuevo = parametros[1];
                        String categoria = parametros[2];
                        String descripcion = parametros[3];
                        //
                    }else{
                        //respuesta.setAsunto("Parametros incorrectos...");
                        //respuesta.setMensaje(Ejemplo);
                    }
                    //new ClienteMail().enviar(respuesta);
                    break;
                
               case ("bajaActivo"):
                    parametroC = numeroM1;
                    if (Pattern.matches(parametroC, parametroI)){
                        String[] parametros = parametroI.split("[//]");
                        int idActivo = Integer.parseInt(parametros[0]);
                        //
                    }else{
                        parametroC = numeroM1 + texto;
                        if (Pattern.matches(parametroC, parametroI)){
                        String[] parametros = parametroI.split("[//]");
                        int idActivo = Integer.parseInt(parametros[0]);
                        String descripcion = parametros[1];
                        //
                        }else{
                            //respuesta.setAsunto("Parametros incorrectos...");
                            //respuesta.setMensaje(Ejemplo);
                        }
                    }
                    //new ClienteMail().enviar(respuesta);    
                    break;     
                    
                case ("inventarioActivos"):
                    //List<Acti> categorias = new CategoriaController().listarCategorias();
                    //respuesta.setAsunto("Lista de categorias.");
                    //if (categorias.size() > 0) {
                        //respuesta.setMensaje(Respuesta.listaCategorias(categorias));
                    //} else {
                        //respuesta.setMensaje("Lista vacia.");
                    //}
                    //new ClienteMail().enviar(respuesta);
                    break;
                
                //----------------- CU: REGISTRAR MOVIMIENTO DE ACTIVO FIJO ----------------    
                case ("movimientoActivo"):
                    parametroC = numeroM1 + texto + texto + texto;
                    if (Pattern.matches(parametroC, parametroI)){
                        String[] parametros = parametroI.split("[//]");
                        int idActivo = Integer.parseInt(parametros[0]);
                        String sucursal = parametros[1];
                        String departamento = parametros[2];
                        String observacion = parametros[3];
                        //
                    }else{
                        //respuesta.setAsunto("Parametros incorrectos...");
                        //respuesta.setMensaje(Ejemplo);
                    }    
                    //new ClienteMail().enviar(respuesta);
                    break;
                    
                case ("listarMovimientos"):
                    //List<Acti> categorias = new CategoriaController().listarCategorias();
                    //respuesta.setAsunto("Lista de categorias.");
                    //if (categorias.size() > 0) {
                        //respuesta.setMensaje(Respuesta.listaCategorias(categorias));
                    //} else {
                        //respuesta.setMensaje("Lista vacia.");
                    //}
                    //new ClienteMail().enviar(respuesta);
                    break;
                    
                case ("asignarActivo"):
                    parametroC = numeroM1 + texto + texto + textoLetras + texto;
                    if (Pattern.matches(parametroC, parametroI)){
                        String[] parametros = parametroI.split("[//]");
                        int idActivo = Integer.parseInt(parametros[0]);
                        String sucursal = parametros[1];
                        String departamento = parametros[2];
                        String encargado = parametros[3];
                        String observacion = parametros[4];
                        //
                    }else{
                        //respuesta.setAsunto("Parametros incorrectos...");
                        //respuesta.setMensaje(Ejemplo);
                    }
                    //new ClienteMail().enviar(respuesta);
                    break;
                    
                //------------------------- CU: REVALUO ------------------------
                    
                case ("revaluarActivo"):
                    parametroC = numeroM1 + numero + texto;
                    if (Pattern.matches(parametroC, parametroI)){
                        String[] parametros = parametroI.split("[//]");
                        int idActivo = Integer.parseInt(parametros[0]);
                        int nuevoCosto = Integer.parseInt(parametros[1]);
                        String detalle = parametros[2];
                        //
                    }else{
                        //respuesta.setAsunto("Parametros incorrectos...");
                        //respuesta.setMensaje(Ejemplo);
                    }
                    //new ClienteMail().enviar(respuesta);
                    break;
                    
                default:
                    //mostrar mensaje indicando que el comando no es válido
                    //mostrar lista de todos los comandos permitidos
                    System.out.println("comando invalido");
                    break;
            }
        } else {
            System.out.println("formato de comando invalido");
        }

    }


    /*
    private String getComando(){

    }

    private String getParametros{

    }
     */
}
