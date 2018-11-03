package utilidades;

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
        

        if(Pattern.matches(textoLetras+":.*", mensajeI)){
            System.out.println("entro");
            String comandoI = mensajeI.substring(0,mensajeI.indexOf(":")).toLowerCase().trim();
            String parametroI = mensajeI.substring(mensajeI.indexOf(":")+1);

            String parametroC;
            System.out.println(comandoI);
            switch (comandoI) {

                case ("registrarunidadmedida"):
                    System.out.println("comando valido");
                    parametroC = texto + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[_]");
                        String nombre = parametros[0];

                        new UnidadMedidaController().registrar(nombre);
                        break;
                    }
                    break;

                case ("editarunidadmedida"):
                    break;

                case ("eliminarunidadmedida"):
                    break;

                case ("listarunidadmedida"):
                    break;

                case ("bajaunidadmedida"):
                    break;

                case ("registrarusuario"):
                    System.out.println("comando valido");
                    parametroC = textoLetras + "//" + textoLetras + "//" + numero + "//" + texto + "//" ;
                    if (Pattern.matches(parametroC, parametroI)) {
                        System.out.println("Entre!");
                        String[] parametros = parametroI.split("[//]");
                        String nombre = parametros[0];
                        String apellido = parametros[2];
                        String carnet = parametros[4];
                        String email = parametros[6];

                        Mensaje respuesta = new Mensaje();
                        respuesta.setCuenta(mensaje.getCuenta());
                        respuesta.setAsunto("Respuesta registrar usuario");
                        if (new UsuarioController().registrarUsuario(nombre, apellido, carnet, email)){
                            System.out.println("OK");
//                            respuesta.setMensaje("Es usuario " + nombre + " " +apellido +" fue registrado correctamente");
                        }else {
                            System.out.println("Prrr!");
//                            respuesta.setMensaje("No se pudo registrar el usuario " + nombre + " " + apellido + " revise" +
//                                    " haber escrito correctamente.");
                        }
//                        new CorreoGmail().enviar(respuesta);
                    }
                    
                    
                    
                    
                    break;

                case ("editarusuario"):
                    System.out.println("comando valido");
                    parametroC = numero + "//" + texto + "//" + texto + "//" + texto + "//" + texto + "//" ;
                    if (Pattern.matches(parametroC, parametroI)) {
                        System.out.println("Entre!");
                        String[] parametros = parametroI.split("[//]");
                        int id = Integer.parseInt(parametros[0]);
                        String nombre = parametros[2];
                        String apellido = parametros[4];
                        String carnet = parametros[6];
                        String email = parametros[8];

                        Mensaje respuesta = new Mensaje();
                        respuesta.setCuenta(mensaje.getCuenta());
                        respuesta.setAsunto("Respuesta registrar usuario");
                        if (new UsuarioController().editarUsuario(id, nombre, apellido, carnet, email)){
                            System.out.println("OK");
//                            respuesta.setMensaje("Es usuario " + nombre + " " +apellido +" fue registrado correctamente");
                        }else {
                            System.out.println("Prrr!");
//                            respuesta.setMensaje("No se pudo registrar el usuario " + nombre + " " + apellido + " revise" +
//                                    " haber escrito correctamente.");
                        }
//                        new CorreoGmail().enviar(respuesta);
                    }else{
                        System.out.println("Revisar parametros!");
                    }


                    break;

                    
                case("eliminarusuario"):
                    System.out.println("comando valido");
                    parametroC = numero + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        System.out.println("Entre!");
                        String[] parametros = parametroI.split("[//]");
                        int id = Integer.parseInt(parametros[0]);

                        Mensaje respuesta = new Mensaje();
                        respuesta.setCuenta(mensaje.getCuenta());
                        respuesta.setAsunto("Respuesta registrar usuario");
                        if (new UsuarioController().eliminarUsuario(id)){
                            System.out.println("OK");
//                            respuesta.setMensaje("Es usuario " + nombre + " " +apellido +" fue registrado correctamente");
                        }else {
                            System.out.println("Prrr!");
//                            respuesta.setMensaje("No se pudo registrar el usuario " + nombre + " " + apellido + " revise" +
//                                    " haber escrito correctamente.");
                        }
//                        new CorreoGmail().enviar(respuesta);
                    }else{
                        System.out.println("Revisar parametros!");
                    }
                    
                    
                    break;
                    
                    

                case("obtenerusuario"):
                    System.out.println("comando valido");
                    parametroC = numero + "//";
                    if (Pattern.matches(parametroC, parametroI)) {
                        System.out.println("Entre!");
                        String[] parametros = parametroI.split("[//]");
                        int id = Integer.parseInt(parametros[0]);

                        Mensaje respuesta = new Mensaje();
                        respuesta.setCuenta(mensaje.getCuenta());
                        respuesta.setAsunto("Respuesta registrar usuario");
                        Usuario u = new UsuarioController().getUsuario(id);
                        if (u != null){
                            System.out.println("OK");
                            System.out.println(u.getId());
                            System.out.println(u.getNombre());
                            System.out.println(u.getApellido());
        //                            respuesta.setMensaje("Es usuario " + nombre + " " +apellido +" fue registrado correctamente");
                        }else {
                            System.out.println("Prrr!");
        //                            respuesta.setMensaje("No se pudo registrar el usuario " + nombre + " " + apellido + " revise" +
        //                                    " haber escrito correctamente.");
                        }
        //                        new CorreoGmail().enviar(respuesta);
                    }else{
                        parametroC = texto + "//";
                        if (Pattern.matches(parametroC, parametroI)) {
                            System.out.println("Entre!");
                        String[] parametros = parametroI.split("[//]");
                        String email = parametros[0];

                        Mensaje respuesta = new Mensaje();
                        respuesta.setCuenta(mensaje.getCuenta());
                        respuesta.setAsunto("Respuesta registrar usuario");
                        Usuario u = new UsuarioController().getUsuario(email);
                        if (u != null){
                            System.out.println("OK");
                            System.out.println(u.getId());
                            System.out.println(u.getNombre());
                            System.out.println(u.getApellido());
        //                            respuesta.setMensaje("Es usuario " + nombre + " " +apellido +" fue registrado correctamente");
                        }else {
                            System.out.println("Prrr!");
        //                            respuesta.setMensaje("No se pudo registrar el usuario " + nombre + " " + apellido + " revise" +
        //                                    " haber escrito correctamente.");
                        }
        //                        new CorreoGmail().enviar(respuesta);
                        }else{
                        System.out.println("Revisar parametros!");
                        }
                    }


                    break;
                    
                    
                    
                    case("listarusuarios"):
                        System.out.println("comando valido");
                        List<Usuario> usuarios = new UsuarioController().listarUsuarios();
                        if (usuarios.size() > 0) {
                            System.out.println("Hay!");
                            //formar un mensaje con todos los usuarios
                        }else{
                            System.out.println("No hay");
                        }


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
        m.setAsunto("listarusuarios:");
        c.procesar(m);
    }
}
