package utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcesadorMensaje {

    public ProcesadorMensaje() {
    }

    public void procesar(Mensaje mensaje){

        String texto =  "(\\s*[^:_]+\\s*)+";
        String numero = "\\s*[0-9]+\\s*";
        String numeroM1 = "\\s*[1-9][0-9]*\\s*";
        String correo = "\\s*[^@_\\s]+@[^@]+\\.[a-zA-Z]{2,}\\s*";
        String esp = "\\s*";

        // validar comando (switch)
            // case: validar el cuerpo
                // si es valido crear controlador y llamar metodo


        String mensajeI = "ingresarUnidadMedida:Metro_";

        if(Pattern.matches("([a-zA-Z]+):.*", mensajeI)){
            String comandoI = mensajeI.substring(0,mensajeI.indexOf(":")-1).toLowerCase();
            String parametroI = mensajeI.substring(mensajeI.indexOf(":")+1);

            String parametroC;

            switch (comandoI) {
                case ("ingresarsuministro"):

                    parametroC = texto+"_"+numeroM1+"_";
                    if(Pattern.matches(parametroC, parametroI)){
                        String [] parametros = parametroI.split("[_]");
                        String nombre = parametros[0];
                        Integer cantidad = Integer.parseInt(parametros[1]);
                        //ingresarSuministro(nombre, cantidad);
                        break;
                    }

                    parametroC = texto+"_"+numeroM1+"_"+texto+"_";
                    if(Pattern.matches(parametroC, parametroI)){
                        String [] parametros = parametroI.split("[_]");
                        String nombre = parametros[0];
                        Integer cantidad = Integer.parseInt(parametros[1]);
                        String descripcion = parametros[2];
                        //ingresarSuministro(nombre, cantidad, descripcion);
                        break;
                    }
                    /*
                    parametroC = texto+"_"+numeroM1+"_"+texto+"_";
                    if(Pattern.matches(parametroC, parametroI)){
                        String [] parametros = parametroI.split("[_]");
                        String nombre = parametros[0];
                        String cantidad = parametros[1];
                        String descripcion = parametros[2];
                        //ingresarSuministro(nombre, cantidad, descripcion);
                        System.out.println(nombre);
                        break;
                    }
                    */

                    //enviar mensaje de error al ingresar los parametros
                    //mostrar lista de instrucciones válidas pertenecientes a este comando
                    break;

                case ("suministrar"):
                    break;


                case ("editarsuministro"):
                    break;

                case ("bajasuministro"):
                    break;

                case ("listarsuministros"):
                    break;

                case ("movimientodesuministros"):
                    break;

                case ("registrarunidadmedida"):

                    parametroC = texto+"_";
                    if(Pattern.matches(parametroC, parametroI)){
                        String [] parametros = parametroI.split("[_]");
                        String nombre = parametros[0];
                        //registrarUnidadMedida(nombre);
                        break;
                    }
                    break;

                case ("editarunidadmedida"):
                    break;

                case ("listarunidadmedida"):
                    break;

                case ("bajaunidadmedida"):
                    break;

                case ("registrarusuario"):
                    break;





                default:
                    //mostrar mensaje indicando que el comando no es válido
                    //mostrar lista de todos los comandos permitidos
                    break;
            }
            // comando desconocido;
        }



    }


/*
    private String getComando(){

    }

    private String getParametros{

    }
*/
}
