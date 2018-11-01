package datos.modelos;

import java.time.LocalDate;

public class Revaluo {
    private int id;
    private String detalle;
    private LocalDate fecha;
    private String tipo;
    private int activo_fijo_id;
    private boolean visible;

    public Revaluo() {
    }

    public Revaluo(String detalle, LocalDate fecha, String tipo, int activo_fijo_id) {
        this.detalle = detalle;
        this.fecha = fecha;
        this.tipo = tipo;
        this.activo_fijo_id = activo_fijo_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getActivo_fijo_id() {
        return activo_fijo_id;
    }

    public void setActivo_fijo_id(int activo_fijo_id) {
        this.activo_fijo_id = activo_fijo_id;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
