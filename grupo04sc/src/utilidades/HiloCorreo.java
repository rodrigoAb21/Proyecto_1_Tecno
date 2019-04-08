package utilidades;

public class HiloCorreo extends Thread {
    public volatile boolean estado = true;

    @Override
    public void run() {
        System.out.println("Se inicio el hilo del corrreo");
        while (estado){
            // Verificar si existe correo nuevo extraigo el primero y elimino
//            Mensaje mensaje = new ClienteMail().obtemerPrimerMensaje();
            Mensaje mensaje = new ClienteMail().obtemerPrimerMensaje();
            if (mensaje != null){
                System.out.println("Iniciando el hilo atencion");
                new HiloAtencion(mensaje).start();
            }

            // si existe, crear un nuevo hilo atencion
            intervaloEspera();
        }
        System.out.println("Saliendo del while infinito");
    }

    public void intervaloEspera(){
        try {
            sleep(10 * 1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
