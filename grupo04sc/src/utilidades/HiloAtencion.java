package utilidades;

import datos.daoimpl.UsuarioDAOImpl;

public class HiloAtencion extends Thread{
    public volatile Mensaje mensaje;

    public HiloAtencion(Mensaje mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        System.out.println(mensaje.getCuenta());
            System.out.println(mensaje.getAsunto());
            System.out.println(mensaje.getMensaje());
        if (esValido(mensaje.getCuenta())){
            new ProcesadorMensaje().procesar(mensaje);
        }
        else
            System.out.println("correo invalido");

    }

    private boolean esValido(String cuentaCorreo){
        return new UsuarioDAOImpl().validarCorreo(cuentaCorreo);
    }






}
