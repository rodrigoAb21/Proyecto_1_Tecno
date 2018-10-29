package utilidades;

import datos.daoimpl.UsuarioDAOImpl;

public class HiloAtencion extends Thread{
    public volatile Mensaje mensaje;

    public HiloAtencion(Mensaje mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        if (esValido(mensaje.getCuenta()))
            new ProcesadorMensaje().procesar(mensaje);
    }

    private boolean esValido(String cuentaCorreo){
        return new UsuarioDAOImpl().validarCorreo(cuentaCorreo);
    }


}
