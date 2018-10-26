package utilidades;

public class Mensaje {
    private String cuenta;
    private String asunto;
    private String mensaje;

    public Mensaje() {
    }

    public Mensaje(String cuenta, String asunto, String mensaje) {
        this.cuenta = cuenta;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
