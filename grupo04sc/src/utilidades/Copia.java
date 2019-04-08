package utilidades;

import datos.daoimpl.MovSuministroDAOImpl;
import datos.modelos.Categoria;
import datos.modelos.UnidadMedida;
import datos.modelos.Usuario;
import java.util.List;

import negocio.controladores.suministros.SuministroController;
import negocio.controladores.suministros.UnidadMedidaController;
import negocio.controladores.usuarios.UsuarioController;

import java.util.regex.Pattern;
import negocio.controladores.activosfijos.CategoriaController;

public class Copia {

    public Copia() {
    }

    public void procesar(Mensaje mensaje){

        String texto =  "(\\s*[^://]+\\s*)+";
        String textoLetras =  "\\s*[a-zA-Z]+\\s*";
        String numero = "\\s*[0-9]+\\s*";
        String numeroM1 = "\\s*[1-9][0-9]*\\s*";
       
        String mensajeI = mensaje.getAsunto();

        Mensaje respuesta = new Mensaje();
        respuesta.setCuenta(mensaje.getCuenta());
        respuesta.setMensaje("...");

        if(Pattern.matches(textoLetras+":.*", mensajeI)){
            String comandoI = mensajeI.substring(0,mensajeI.indexOf(":")).trim();
            String parametroI = mensajeI.substring(mensajeI.indexOf(":")+1);

            String parametroC;
            switch (comandoI) {

                //-------------------------------- CATEGORIA ----------------------------------------------

                case ("RegistrarCategoria"):
                    parametroC = texto + "//" + texto + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        String nombre = parametros[0];
                        int cat_sup = 0;
                        if(!parametros[2].equals("_")){
                            cat_sup = Integer.parseInt(parametros[2]);
                        }
                        int id = new CategoriaController().registrar(nombre, cat_sup);
                        if (id > 0){
                            respuesta.setAsunto("Se registro la categoria.");
                            respuesta.setMensaje(Respuesta.categoria(new CategoriaController()
                                    .getCategoria(id)));
                        }else {
                            respuesta.setAsunto("No se pudo registrar la Categoria.");
                        }
                    }else{
                        respuesta.setAsunto("Parametros incorrectos al registrar la categoria.");
                        respuesta.setMensaje(Ejemplo.RegistrarCategoria);
                    }
                    new CorreoGmail().enviar(respuesta);
                    break;

                case ("EditarCategoria"):
                    parametroC = numero + "//" + texto + "//" + texto + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int id = Integer.parseInt(parametros[0]);
                        String nuevo = parametros[2];
                        int cat_sup = 0;
                        if(!parametros[4].equals("_")){
                            cat_sup = Integer.parseInt(parametros[4]);
                        }

                        if (new CategoriaController().editar(id, nuevo, cat_sup)){
                            respuesta.setAsunto("Se edito la categoria.");
                            respuesta.setMensaje(Respuesta.categoria(new CategoriaController()
                                    .getCategoria(id)));
                        }else {
                            respuesta.setAsunto("No se pudo editar la categoria");
                        }

                    }else{
                        respuesta.setAsunto("Parametros incorrectos al editar la categoria.");
                        respuesta.setMensaje(Ejemplo.EditarCategoria);
                    }
                    new CorreoGmail().enviar(respuesta);
                    break;

                case ("EliminarCategoria"):
                    parametroC = numero + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int id = Integer.parseInt(parametros[0]);

                        if (new CategoriaController().eliminar(id)){
                            respuesta.setAsunto("Se elimino la categoria.");
                        }else {
                            respuesta.setAsunto("No se pudo eliminar la categoria.");
                        }
                    }else{
                        respuesta.setAsunto("Parametros incorrectos al eliminar la categoria.");
                        respuesta.setMensaje(Ejemplo.EliminarCategoria);
                    }

                    new CorreoGmail().enviar(respuesta);
                    break;

                case ("ListarCategorias"):
                    List<Categoria> categorias = new CategoriaController().listarCategorias();
                    respuesta.setAsunto("Lista de categorias.");
                    if (categorias.size() > 0 ){
                        respuesta.setMensaje(Respuesta.listaCategoria(categorias));
                    }else {
                        respuesta.setMensaje("Lista vacia.");
                    }

                    break;
                
                //-------------------------------- UNIDAD DE MEDIDA ----------------------------------------------

                case ("RegistrarUnidadMedida"):
                    parametroC = texto + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        String nombre = parametros[0];
                        int id = new UnidadMedidaController().registrar(nombre);
                        if (id > 0){
                            respuesta.setAsunto("Se registro la unidad de medida.");
                            respuesta.setMensaje(Respuesta.unidadMedida(new UnidadMedidaController()
                                    .getUnidadMedida(id)));
                        }else {
                            respuesta.setAsunto("No se pudo registrar la unidad de medida.");
                        }
                    }else{
                        respuesta.setAsunto("Parametros incorrectos al registrar la unidad de medida.");
                        respuesta.setMensaje(Ejemplo.RegistrarUnidadMedida);
                    }
                    new CorreoGmail().enviar(respuesta);
                    break;

                case ("EditarUnidadMedida"):
                    System.out.println("comando valido");
                    parametroC = numero + "//" + texto + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int id = Integer.parseInt(parametros[0]);
                        String nuevo = parametros[2];

                        if (new UnidadMedidaController().editar(id, nuevo)){
                            respuesta.setAsunto("Se edito la unidad de medida.");
                            respuesta.setMensaje(Respuesta.unidadMedida(new UnidadMedidaController()
                                    .getUnidadMedida(nuevo)));
                        }else {
                            respuesta.setAsunto("No se pudo editar la unidad de medida");
                        }

                    }else{
                        respuesta.setAsunto("Parametros incorrectos al editar la unidad de medida.");
                        respuesta.setMensaje(Ejemplo.EditarUnidadMedida);
                    }
                    new CorreoGmail().enviar(respuesta);
                    break;

                case ("EliminarUnidadMedida"):
                    parametroC = numero + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int id = Integer.parseInt(parametros[0]);

                        if (new UnidadMedidaController().eliminar(id)){
                            respuesta.setAsunto("Se elimino la unidad de medida.");
                        }else {
                            respuesta.setAsunto("No se pudo eliminar la unidad de medida.");
                        }
                    }else{
                        respuesta.setAsunto("Parametros incorrectos al eliminar la unidad de medida.");
                        respuesta.setMensaje(Ejemplo.EliminarUnidadMedida);
                    }

                    new CorreoGmail().enviar(respuesta);
                    break;

                case ("ListarUnidadMedida"):
                    List<UnidadMedida> unidadMedidas = new UnidadMedidaController().listarUnidades();
                    respuesta.setAsunto("Lista de unidades de medida.");
                    if (unidadMedidas.size() > 0 ){
                        respuesta.setMensaje(Respuesta.listaUnidadMedida(unidadMedidas));
                        
                    }else {
                        respuesta.setMensaje("Lista vacia.");
                    }

                    break;



                //-------------------------------- USUARIO ----------------------------------------------

                case ("RegistrarUsuario"):
                    parametroC = textoLetras + "//" + textoLetras + "//" + numero + "//" + texto + "//" ;
                    if (Pattern.matches(parametroC, parametroI)) {
                        System.out.println("Entre!");
                        String[] parametros = parametroI.split("[//]");
                        String nombre = parametros[0];
                        String apellido = parametros[2];
                        String carnet = parametros[4];
                        String email = parametros[6];

                        respuesta.setAsunto("Respuesta registrar usuario");
                        int id = new UsuarioController().registrarUsuario(nombre, apellido, carnet, email);
                        if (id > 0){
                            respuesta.setAsunto("Se registro un nuevo usuario");
                            respuesta.setMensaje(Respuesta.usuario(new UsuarioController().getUsuario(id)));
                        }else {
                            respuesta.setAsunto("No se pudo registrar el usuario.");
                        }
                    }else{
                        respuesta.setAsunto("Parametros incorrectos al registrar un nuevo usuario.");
                        respuesta.setMensaje(Ejemplo.RegistrarUsuario);
                    }
                    new CorreoGmail().enviar(respuesta);

                    break;

                case ("EditarUsuario"):
                    parametroC = numero + "//" + texto + "//" + texto + "//" + texto + "//" + texto + "//" ;
                    
                    
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");

                        int id = Integer.parseInt(parametros[0]);
                        String nombre = parametros[2];
                        String apellido = parametros[4];
                        String carnet = parametros[6];
                        String email = parametros[8];

                        if (new UsuarioController().editarUsuario(id, nombre, apellido, carnet, email)){
                            respuesta.setAsunto("Se edito al usuario con id: " + id);
                            respuesta.setMensaje(Respuesta.usuario(new UsuarioController().getUsuario(id)));
                        }else {
                            respuesta.setAsunto("No se pudo editar el usuario.");
                        }
                    }else{
                        respuesta.setAsunto("Parametros incorrectos al editar el usuario.");
                        respuesta.setMensaje(Ejemplo.EditarUsuario);
                    }
                    new CorreoGmail().enviar(respuesta);

                    break;

                    
                case("EliminarUsuario"):
                    parametroC = numero + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int id = Integer.parseInt(parametros[0]);

                        if (new UsuarioController().eliminarUsuario(id)){
                            respuesta.setAsunto("Se elimino al usuario con id: " + id);
                        }else {
                            respuesta.setAsunto("No se pudo eliminar al usuario.");
                        }
                    }else{
                        respuesta.setAsunto("Parametros incorrectos al eliminar al usuario.");
                        respuesta.setMensaje(Ejemplo.EliminarUsuario);
                    }
                    
                    
                    break;

                case("RecuperarUsuario"):
                    parametroC = numero + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int id = Integer.parseInt(parametros[0]);

                        if (new UsuarioController().recuperarUsuario(id)){
                            respuesta.setAsunto("Se recupero al usuario con id: " + id);
                        }else {
                            respuesta.setAsunto("No se pudo recuperar al usuario.");
                        }
                    }else{
                        respuesta.setAsunto("Parametros incorrectos al recuperar al usuario.");
                        respuesta.setMensaje(Ejemplo.RecuperarUsuario);
                    }


                    break;


                case("ListarUsuarios"):
                    List<Usuario> usuarios = new UsuarioController().listarUsuarios();
                    respuesta.setAsunto("Lista de usuarios");
                    if (usuarios.size() > 0) {
                        respuesta.setMensaje(Respuesta.listaUsuario(usuarios));
                    }else{
                        respuesta.setMensaje("Lista vacia.");
                    }

                    break;

                case("ListarUsuariosEliminados"):
                    List<Usuario> eliminados = new UsuarioController().listarUsuariosEliminados();
                    respuesta.setAsunto("Lista de usuarios");
                    if (eliminados.size() > 0) {
                        respuesta.setMensaje(Respuesta.listaUsuario(eliminados));
                    }else{
                        respuesta.setMensaje("Lista vacia.");
                    }

                    break;




                //-------------------------------- SUMINISTRO ----------------------------------------------

                case("IngresoNuevoSuministro"):
                    parametroC = texto + "//" + texto + "//" + numeroM1 + "//" + numeroM1 + "//" + numero + "//" + numeroM1
                            + "//" + numeroM1 + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        String nombre = parametros[0];
                        String desc = parametros[2];
                        int um_id = Integer.parseInt(parametros[4]);
                        int cat_id = Integer.parseInt(parametros[6]);
                        int min = Integer.parseInt(parametros[8]);
                        int max = Integer.parseInt(parametros[10]);
                        int cant = Integer.parseInt(parametros[12]);

                        int id = new SuministroController().ingresoNuevo(nombre, desc, um_id, cat_id, min, max, cant);
                        if (id > 0){
                            respuesta.setAsunto("Se registro un ingreso de un nuevo suministro");
                            respuesta.setMensaje(new MovSuministroDAOImpl().getMovSuministroString(id));
                        }else {
                            respuesta.setAsunto("No se pudo realizar el ingreso del nuevo suministro.");
                        }
                    }else{
                        respuesta.setAsunto("Parametros incorrectos al ingresar un nuevo suministro.");
                        respuesta.setMensaje(Ejemplo.IngresoNuevoSuministro);
                    }
                    new CorreoGmail().enviar(respuesta);
                    break;

                case("IngresoSuministro"):
                    parametroC = numeroM1 + "//" + numeroM1 + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int sum_id = Integer.parseInt(parametros[0]);
                        int cant = Integer.parseInt(parametros[2]);

                        int id = new SuministroController().ingreso(sum_id, cant);
                        if (id > 0){
                            respuesta.setAsunto("Se registro un ingreso de un suministro");
                            respuesta.setMensaje(new MovSuministroDAOImpl().getMovSuministroString(id));
                        }else {
                            respuesta.setAsunto("No se pudo realizar el ingreso del suministro.");
                        }
                    }else{
                        respuesta.setAsunto("Parametros incorrectos al ingresar un suministro.");
                        respuesta.setMensaje(Ejemplo.IngresoSuministro);
                    }
                    new CorreoGmail().enviar(respuesta);
                    break;

                case("SalidaSuministro"):
                    parametroC = numeroM1 + "//" + numeroM1 + "//" + texto + "//" + texto + "//" + texto + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int sum_id = Integer.parseInt(parametros[0]);
                        int cant = Integer.parseInt(parametros[2]);
                        String encargado = parametros[4];
                        String dpto = parametros[6];
                        String obs = parametros[8];

                        int id = new SuministroController().salida(sum_id, cant, encargado, dpto, obs);
                        if (id > 0){
                            respuesta.setAsunto("Se registro una salida de un nuevo suministro");
                            respuesta.setMensaje(new MovSuministroDAOImpl().getMovSuministroString(id));
                        }else {
                            respuesta.setAsunto("No se pudo realizar la salida del suministro.");
                        }
                    }else{
                        respuesta.setAsunto("Parametros incorrectos al realizar la salida suministro.");
                        respuesta.setMensaje(Ejemplo.SalidaSuministro);
                    }
                    new CorreoGmail().enviar(respuesta);
                    break;

                case("DevolucionSuministro"):
                    parametroC = numeroM1 + "//" + numeroM1 + "//" + texto + "//" + texto + "//" + texto + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int sum_id = Integer.parseInt(parametros[0]);
                        int cant = Integer.parseInt(parametros[2]);
                        String encargado = parametros[4];
                        String dpto = parametros[6];
                        String obs = parametros[8];

                        int id = new SuministroController().devolucion(sum_id, cant, encargado, dpto, obs);
                        if (id > 0){
                            respuesta.setAsunto("Se registro una devolucion de un nuevo suministro");
                            respuesta.setMensaje(new MovSuministroDAOImpl().getMovSuministroString(id));
                        }else {
                            respuesta.setAsunto("No se pudo realizar la devolucion del suministro.");
                        }
                    }else{
                        respuesta.setAsunto("Parametros incorrectos al realizar la devolucion suministro.");
                        respuesta.setMensaje(Ejemplo.DevolucionSuministro);
                    }
                    new CorreoGmail().enviar(respuesta);
                    break;

                case("EditarSuministro"):
                    parametroC = numero + "//" + texto + "//" + texto + "//" + texto + "//" + texto + "//" + texto +
                            "//" + texto + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int id_sum = Integer.parseInt(parametros[0]);
                        String nombre = parametros[2];
                        String descr = parametros[4];
                        String um_id = parametros[6];
                        String cat_id = parametros[8];
                        String min = parametros[10];
                        String max = parametros[12];

                        if (new SuministroController().editarSuministro(id_sum, nombre, descr, um_id, cat_id, min, max)){
                            respuesta.setAsunto("Se edito al suministro");
                            respuesta.setMensaje(new SuministroController().getSuministro(id_sum));
                        }else {
                            respuesta.setAsunto("No se pudo realizar la edicion del suministro.");
                        }
                    }else{
                        respuesta.setAsunto("Parametros incorrectos al editar un suministro.");
                        respuesta.setMensaje(Ejemplo.EditarSuministro);
                    }
                    new CorreoGmail().enviar(respuesta);
                    break;

                case("EliminarSuministro"):
                    parametroC = numero + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int id_sum = Integer.parseInt(parametros[0]);

                        if (new SuministroController().eliminarSuministro(id_sum)){
                            respuesta.setAsunto("Se elimino al suministro");
                        }else {
                            respuesta.setAsunto("No se pudo eliminar al suministro.");
                        }
                    }else{
                        respuesta.setAsunto("Parametros incorrectos al eliminar un suministro.");
                        respuesta.setMensaje(Ejemplo.EliminarSuministro);
                    }
                    new CorreoGmail().enviar(respuesta);
                    break;

                case("CancelarMovimiento"):
                    parametroC = numero + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int id_mov = Integer.parseInt(parametros[0]);

                        if (new SuministroController().cancelarMovimiento(id_mov)){
                            respuesta.setAsunto("Se cancelo el movimiento");
                        }else {
                            respuesta.setAsunto("No se pudo cancelar al movimiento.");
                        }
                    }else{
                        respuesta.setAsunto("Parametros incorrectos al cancelar al movimiento.");
                        respuesta.setMensaje(Ejemplo.CancelarMovimiento);
                    }
                    new CorreoGmail().enviar(respuesta);
                    break;

                case("RestablecerMovimiento"):
                    parametroC = numero + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int id_mov = Integer.parseInt(parametros[0]);

                        if (new SuministroController().restablecerMovimiento(id_mov)){
                            respuesta.setAsunto("Se restablecio el movimiento");
                            respuesta.setMensaje(new MovSuministroDAOImpl().getMovSuministroString(id_mov));
                        }else {
                            respuesta.setAsunto("No se pudo restablecer al movimiento.");
                        }
                    }else{
                        respuesta.setAsunto("Parametros incorrectos al restablecer al movimiento.");
                        respuesta.setMensaje(Ejemplo.RestablecerMovimiento);
                    }
                    new CorreoGmail().enviar(respuesta);
                    break;



                case("ListarMovimientos"):
                    List<String> mov = new SuministroController().listarMovimientos();
                    respuesta.setAsunto("Lista de movimientos");
                    if (mov.size() > 0) {
                        respuesta.setMensaje(Respuesta.listaMovimientos(mov));
                    }else{
                        respuesta.setMensaje("Lista vacia.");
                    }
                    new CorreoGmail().enviar(respuesta);
                    break;

                case("ListarMovimientosCancelados"):
                    List<String> movC = new SuministroController().listarMovimientosCancelados();
                    respuesta.setAsunto("Lista de movimientos cancelados");
                    if (movC.size() > 0) {
                        respuesta.setMensaje(Respuesta.listaMovimientos(movC));
                    }else{
                        respuesta.setMensaje("Lista vacia.");
                    }
                    new CorreoGmail().enviar(respuesta);
                    break;

                case("InventarioSuministros"):
                    List<String> sum = new SuministroController().listarSuministros();
                    respuesta.setAsunto("Lista de movimientos");
                    if (sum.size() > 0) {
                        respuesta.setMensaje(Respuesta.listaSuministros(sum));

                    }else{
                        respuesta.setMensaje("Lista vacia.");
                    }
                    new CorreoGmail().enviar(respuesta);
                    break;

                case("VerSuministro"):
                    parametroC = numero + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int id_sum = Integer.parseInt(parametros[0]);
                        String xx = new SuministroController().getSuministro(id_sum);
                        if (xx != null){
                            respuesta.setAsunto("Ver Suministro");
                            respuesta.setMensaje(xx);

                        }else {
                            respuesta.setAsunto("No se pudo mostrar el suministro.");
                        }
                    }else{
                        respuesta.setAsunto("Parametros incorrectos al mostrar el suministro.");
                        respuesta.setMensaje(Ejemplo.VerSuministro);
                    }
                    new CorreoGmail().enviar(respuesta);
                    break;

                case("VerMovimiento"):
                    parametroC = numero + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[//]");
                        int id_mov = Integer.parseInt(parametros[0]);
                        String yy = new SuministroController().getMovString(id_mov);
                        if (yy != null){
                            respuesta.setAsunto("Ver Movimiento");
                            respuesta.setMensaje(yy);
                        }else {
                            respuesta.setAsunto("No se pudo mostrar el movimiento.");
                        }
                    }else{
                        respuesta.setAsunto("Parametros incorrectos al mostrar el movimiento.");
                        respuesta.setMensaje(Ejemplo.VerMovimiento);
                    }
                    new CorreoGmail().enviar(respuesta);
                    break;

                default:
                    //mostrar mensaje indicando que el comando no es válido
                    //mostrar lista de todos los comandos permitidos
                    System.out.println("comando invalido");
                    break;
            }
        }else{
            System.out.println("formato de comando invalido");
        }




    }
    public static void main(String[] args) {
        Copia c = new Copia();
        Mensaje m = new Mensaje();
        m.setCuenta("rodrigo.abasto21@gmail.com");
        m.setAsunto("EditarUsuario:1//Juan//Perez//3342342//rodrigo.abasto21@gmail.com//");
        c.procesar(m);
    }
}
