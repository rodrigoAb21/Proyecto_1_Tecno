package utilidades;

public class HiloCorreo extends Thread {
    public volatile boolean estado = true;

    @Override
    public void run() {
        System.out.println("Se inicio el hilo del corrreo");
        while (estado){
            // Verificar si existe correo nuevo extraigo el primero y elimino

            // si existe, crear un nuevo hilo atencion

            // intervalo de espera
        }
    }
}
