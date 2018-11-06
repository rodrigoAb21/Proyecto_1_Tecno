package utilidades;

import datos.modelos.UnidadMedida;
import datos.modelos.Usuario;
import java.util.List;
import negocio.controladores.suministros.UnidadMedidaController;
import negocio.controladores.usuarios.UsuarioController;

import java.util.regex.Pattern;

public class Copia {

    public Copia() {
    }

    public void procesar(Mensaje mensaje){

        String texto =  "(\\s*[^://]+\\s*)+";
        String textoLetras =  "\\s*[a-zA-Z]+\\s*";
        String numero = "\\s*[0-9]+\\s*";
        String numeroM1 = "\\s*[1-9][0-9]*\\s*";
        String correo = "\\s*[^@_\\s]+@[^@]+\\.[a-zA-Z]{2,}\\s*";
        String esp = "\\s*";

        String mensajeI = mensaje.getAsunto();

        Mensaje respuesta = new Mensaje();
        respuesta.setCuenta(mensaje.getCuenta());

        if(Pattern.matches(textoLetras+":.*", mensajeI)){
            System.out.println("entro");
            String comandoI = mensajeI.substring(0,mensajeI.indexOf(":")).toLowerCase().trim();
            String parametroI = mensajeI.substring(mensajeI.indexOf(":")+1);

            String parametroC;
            System.out.println(comandoI);
            switch (comandoI) {

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
                    new ClienteMail().enviar(respuesta);
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
                    new ClienteMail().enviar(respuesta);
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

                    new ClienteMail().enviar(respuesta);
                    break;

                case ("listarunidadmedida"):
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
                    break;

                case("IngresoSuministro"):
                    break;

                case("SalidaSuministro"):
                    break;

                case("DevolucionSuministro"):
                    break;

                case("EditarSuministro"):
                    break;

                case("EliminarSuministro"):
                    break;

                case("InventarioSuministro"):
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
        m.setAsunto("registrarusuario:Mateo//Kuljkis//8181035//mateo@gmail.com//");
        c.procesar(m);
    }
}
