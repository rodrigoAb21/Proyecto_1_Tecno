package datos.modelos;

public class DetalleSuministrar {
    private int id;
    private int cantidad;
    private int suministro_id;
    private int movimiento_suministro_id;

    public DetalleSuministrar() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getSuministro_id() {
        return suministro_id;
    }

    public void setSuministro_id(int suministro_id) {
        this.suministro_id = suministro_id;
    }

    public int getMovimiento_suministro_id() {
        return movimiento_suministro_id;
    }

    public void setMovimiento_suministro_id(int movimiento_suministro_id) {
        this.movimiento_suministro_id = movimiento_suministro_id;
    }
}
