package datos.modelos;

import java.time.LocalDate;

public class MovSuministro {
    private int id;
    private String tipo;
    private LocalDate fecha;
    private boolean visible;

    public MovSuministro() {
    }

    public MovSuministro(String tipo, LocalDate fecha) {
        this.tipo = tipo;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
