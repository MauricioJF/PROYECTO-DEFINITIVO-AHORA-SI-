import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class SistemaDeGestionDeTrafico {
    private List<Vehiculo> vehiculos;
    private List<Semaforo> semaforos;
    private RandomAccessFile archivo;

    public SistemaDeGestionDeTrafico(String archivoNombre) throws IOException {
        vehiculos = new ArrayList<>();
        semaforos = new ArrayList<>();
        archivo = new RandomAccessFile(archivoNombre, "rw");
        cargarDatos();
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
        try {
            archivo.seek(archivo.length());
            archivo.writeUTF(vehiculo.getMatricula());
            archivo.writeUTF(vehiculo.getMarca());
            archivo.writeUTF(vehiculo.getModelo());
            archivo.writeInt(vehiculo.getVelocidad());
            if (vehiculo instanceof Coche) {
                archivo.writeInt(((Coche) vehiculo).getNumeroPuertas());
                archivo.writeInt(0); // Escribimos 0 para indicar que no es un camión
            } else if (vehiculo instanceof Camion) {
                archivo.writeInt(0); // Escribimos 0 para indicar que no es un coche
                archivo.writeInt(((Camion) vehiculo).getCapacidadCarga());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eliminarVehiculo(String matricula) {
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getMatricula().equals(matricula)) {
                vehiculos.remove(i);
                try {
                    archivo.setLength(archivo.length() - 40); // 40 bytes por vehículo
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public void mostrarVehiculos() {
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.mostrarInformacion();
            System.out.println();
        }
    }

    public void agregarSemaforo(Semaforo semaforo) {
        semaforos.add(semaforo);
    }

    public void eliminarSemaforo(String ubicacion) {
        for (int i = 0; i < semaforos.size(); i++) {
            if (semaforos.get(i).getUbicacion().equals(ubicacion)) {
                semaforos.remove(i);
                break;
            }
        }
    }

    public void mostrarSemaforos() {
        for (Semaforo semaforo : semaforos) {
            System.out.println("Ubicación: " + semaforo.getUbicacion());
            System.out.println("Estado: " + (semaforo.getEstado() ? "Verde" : "Rojo"));
            System.out.println();
        }
    }

    public void simularTrafico() {
        for (Vehiculo vehiculo : vehiculos) {
            // Simulación de tráfico
            System.out.println("Vehículo " + vehiculo.getMatricula() + " está en movimiento");
            try {
                Thread.sleep(2000); // Simulación de 2 segundos de tráfico
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Semaforo semaforo : semaforos) {
            // Simulación de cambio de estado del semáforo
            semaforo.cambiarEstado();
            System.out.println("Semaforo en " + semaforo.getUbicacion() + " cambió a " + (semaforo.getEstado() ? "Verde" : "Rojo"));
        }
    }

    public void cerrarArchivo() throws IOException {
        archivo.close();
    }

    private void cargarDatos() {
        try {
            long longitud = archivo.length();
            long posicionActual = 0;
            while (posicionActual < longitud) {
                String matricula = archivo.readUTF();
                String marca = archivo.readUTF();
                String modelo = archivo.readUTF();
                int velocidad = archivo.readInt();
                int numeroPuertas = archivo.readInt();
                int capacidadCarga = archivo.readInt();

                if (numeroPuertas > 0) {
                    Vehiculo coche = new Coche(matricula, marca, modelo, velocidad, numeroPuertas);
                    agregarVehiculo(coche);
                } else {
                    Vehiculo camion = new Camion(matricula, marca, modelo, velocidad, capacidadCarga);
                    agregarVehiculo(camion);
                }

               posicionActual += 40; // 40 bytes por vehículo
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}