package datos.modelos;

public class Suministro {
    private int id;
    private int stock_minimo;
    private int stock_maximo;
    private int producto_id;
    private boolean visible;

    public Suministro() {
    }

    public Suministro(int stock_minimo, int stock_maximo, int producto_id) {
        this.stock_minimo = stock_minimo;
        this.stock_maximo = stock_maximo;
        this.producto_id = producto_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock_minimo() {
        return stock_minimo;
    }

    public void setStock_minimo(int stock_minimo) {
        this.stock_minimo = stock_minimo;
    }

    public int getStock_maximo() {
        return stock_maximo;
    }

    public void setStock_maximo(int stock_maximo) {
        this.stock_maximo = stock_maximo;
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
