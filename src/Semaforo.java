public class Semaforo {
    private String ubicacion;
    private boolean estado; // true = verde, false = rojo

    public Semaforo(String ubicacion) {
        this.ubicacion = ubicacion;
        this.estado = true; // Inicialmente en verde
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void cambiarEstado() {
        estado = !estado;
    }
}