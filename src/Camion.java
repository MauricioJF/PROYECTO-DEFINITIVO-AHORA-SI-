public class Camion extends Vehiculo {
    private int capacidadCarga;

    public Camion(String matricula, String marca, String modelo, int velocidad, int capacidadCarga) {
        super(matricula, marca, modelo, velocidad);
        this.capacidadCarga = capacidadCarga;
    }

    public int getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(int capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Matrícula: " + getMatricula());
        System.out.println("Marca: " + getMarca());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Velocidad: " + getVelocidad() + " km/h");
        System.out.println("Capacidad de carga: " + getCapacidadCarga() + " kg");
    }
}