package utilidades;

import datos.modelos.Categoria;
import datos.modelos.UnidadMedida;
import datos.modelos.Usuario;

import java.util.List;

public class Respuesta {
    public static String categoria(Categoria categoria){
        return "ID: " + categoria.getId() + "\nNombre: " + categoria.getNombre() + "\nCodigo: " + categoria.getCodigo()
                + "\nCat_Superior: " + categoria.getCategoria_sup();
    }

    public static String listaCategoria(List<Categoria> categorias){
        String respuesta = "";
        for (Categoria categoria : categorias){
            respuesta = respuesta + "ID: " + categoria.getId() + ", Nombre: " + categoria.getNombre() + ", Codigo: " + categoria.getCodigo()
                + ", Cat_Superior: " + categoria.getCategoria_sup() + " \n";
        }
        return respuesta;
    }
    
    
    
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

    public static String usuario(Usuario usuario){
        return "ID: " + usuario.getId() + "\nNombre:" + usuario.getNombre() + "\nApellido:" + usuario.getApellido() +
                "\nCI: " + usuario.getCi() + "\nEmail: " + usuario.getEmail();
    }

    public static String listaUsuario(List<Usuario> usuarios){
        String respuesta = "";
        for (Usuario usuario : usuarios){
            respuesta = respuesta +  "ID: " + usuario.getId() + ",   Nombre:" + usuario.getNombre() + ",  Apellido:" + usuario.getApellido() +
                    ",   CI: " + usuario.getCi() + ",   Email: " + usuario.getEmail() + "\n";
        }
        return respuesta;
    }

    public static String listaMovimientos(List<String> mov) {
        String respuesta = "";
        for (String fila : mov){
            respuesta = respuesta + fila + "\n";
        }
        return respuesta;
    }

    public static String listaSuministros(List<String> sum) {
        String respuesta = "";
        for (String fila : sum){
            respuesta = respuesta + fila + "\n";
        }
        return respuesta;
    }
}
