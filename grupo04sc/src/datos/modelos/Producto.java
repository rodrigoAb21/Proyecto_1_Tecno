package datos.modelos;

public class Producto {
    private int id;
    private String nombre;
    private String tipo;
    private String codigo;
    private int cantidad;
    private boolean visible;

    public Producto() {
    }

    public Producto(String nombre, String tipo, String codigo, int cantidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
