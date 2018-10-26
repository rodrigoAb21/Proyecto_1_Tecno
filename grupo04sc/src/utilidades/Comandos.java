package utilidades;

import java.awt.*;

public class Comandos {
    // TIPO_DE_DATO
    public static final String TEXTO = "\\s{0,}([a-zA-Z]+\\s{0,})+";
    public static final String NUM_MAY1 = "[1-9]\\d{0,}";
    public static final String NUM = "\\d{0,}";
    public static final String ESPACIO = "\\s{0,}";
    public static final String CORREO = "\\s{0,}[^\\s^@]+\\.[a-zA-Z]{2,}\\s{0,}";
    // COMANDO
    public static final String REGISTRAR_USUARIO_COMANDO = "REGISTRAR_USUARIO:";
    public static final String REGISTRAR_USUARIO_PARAMETROS = TEXTO;

    /*
    "REGISTRAR_ USUARIO:" , nombre(TEXTO), apellido(TEXTO), ci(NUMERO), correo(CORREO)
    "INGRESAR_SUMINISTRO:" producto(TEXTO)(cantidad(NUM_MAY1))
                           producto(TEXTO)(cantidad(NUM_MAY1)), descripcion(TEXTO)
                           producto(TEXTO)(cantidad(NUM_MAY1), medida(TEXTO), atributo("min"|"max")(valor(NUM))
                           //producto(TEXTO)(cantidad(NUM_MAY1), medida(TEXTO), (atributo("min"|"max")(valor(NUM)))
    "SUMINISTRAR:"
    */


}
