package datos.modelos;

import java.time.LocalDate;

public class MovActivo {
    private int id;
    private LocalDate fecha;
    private boolean visible;

    public MovActivo() {
    }

    public MovActivo(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
