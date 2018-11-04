package utilidades;

import datos.modelos.UnidadMedida;

import java.util.List;

public class Respuesta {
    public static String unidadMedida(UnidadMedida unidadMedida){
        return "ID: " + unidadMedida.getId() + "\nNombre: " + unidadMedida.getNombre();
    }

    public static String listaUnidadMedida(List<UnidadMedida> unidadMedidas){
        String respuesta = "";
        for (UnidadMedida unidadMedida : unidadMedidas){
            respuesta = respuesta + "ID: " + unidadMedida.getId() + ",  Nombre: " + unidadMedida.getNombre() + "\n";
        }
        return respuesta;
    }
}
