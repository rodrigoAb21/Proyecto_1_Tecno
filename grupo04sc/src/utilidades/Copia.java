package utilidades;

import negocio.controladores.suministros.UnidadMedidaController;

import java.util.regex.Pattern;

public class Copia {

    public Copia() {
    }

    public void procesar(Mensaje mensaje){

        String texto =  "(\\s*[^:_]+\\s*)+";
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
                case ("ingresarsuministro"):

                    parametroC = texto + "_" + numeroM1 + "_";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[_]");
                        String nombre = parametros[0];
                        Integer cantidad = Integer.parseInt(parametros[1]);
                        //ingresarSuministro(nombre, cantidad);
                        break;
                    }

                    parametroC = texto + "_" + numeroM1 + "_" + texto + "_";
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[_]");
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
                    System.out.println("comando valido");
                    parametroC = texto + "_";
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
                    parametroC = texto + "//" + texto + texto ;
                    if (Pattern.matches(parametroC, parametroI)) {
                        String[] parametros = parametroI.split("[_]");
                        String nombre = parametros[0];

                        new UnidadMedidaController().registrar(nombre);
                        break;
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
}
