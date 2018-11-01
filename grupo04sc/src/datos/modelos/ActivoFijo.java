package datos.modelos;

public class ActivoFijo {
    private int id;
    private String estado;
    private boolean disponible;
    private String codigo;
    private int producto_id;
    private boolean visible;

    public ActivoFijo() {
    }

    public ActivoFijo(String estado, boolean disponible, String codigo, int producto_id) {
        this.estado = estado;
        this.disponible = disponible;
        this.codigo = codigo;
        this.producto_id = producto_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
