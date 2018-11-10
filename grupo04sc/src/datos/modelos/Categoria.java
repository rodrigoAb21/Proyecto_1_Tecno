package datos.modelos;

public class Categoria {
    private int id;
    private String nombre;
    private String codigo;
    private int categoria_sup;
    public boolean visible;

    public Categoria() {
    }

    public Categoria(String nombre, String codigo, int categoria_sup) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.categoria_sup = categoria_sup;
        this.visible = true;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCategoria_sup() {
        return categoria_sup;
    }

    public void setCategoria_sup(int categoria_sup) {
        if(categoria_sup!=-1){
        this.categoria_sup = categoria_sup;
        return;
        }
        this.categoria_sup = 0;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}


