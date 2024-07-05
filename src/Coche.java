public class Coche extends Vehiculo {
    private int numeroPuertas;

    public Coche(String matricula, String marca, String modelo, int velocidad, int numeroPuertas) {
        super(matricula, marca, modelo, velocidad);
        this.numeroPuertas = numeroPuertas;
    }

    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Matrícula: " + getMatricula());
        System.out.println("Marca: " + getMarca());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Velocidad: " + getVelocidad() + " km/h");
        System.out.println("Número de puertas: " + getNumeroPuertas());
    }
}