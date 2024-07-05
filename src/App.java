import java.io.IOException;
import java.util.Scanner;

public class App {
  
    public static void main(String[] args) {
        String archivoNombre = "datos.txt";
        SistemaDeGestionDeTrafico sistema = null;
        Scanner scanner = new Scanner(System.in);

        try {
            sistema = new SistemaDeGestionDeTrafico(archivoNombre);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int opcion = 0;

        while (opcion!= 9) {
            System.out.println("\nMenú principal:");
            System.out.println("1. Agregar vehículo");
            System.out.println("2. Eliminar vehículo");
            System.out.println("3. Mostrar vehículos");
            System.out.println("4. Agregar semáforo");
            System.out.println("5. Eliminar semáforo");
            System.out.println("6. Mostrar semáforos");
            System.out.println("7. Simular tráfico");
            System.out.println("8. Guardar datos");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la matrícula del vehículo: ");
                    scanner.nextLine();
                    String matricula = scanner.nextLine();
                    System.out.print("Ingrese la marca del vehículo: ");
                    String marca = scanner.nextLine();
                    System.out.print("Ingrese el modelo del vehículo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Ingrese la velocidad del vehículo: ");
                    int velocidad = scanner.nextInt();
                    System.out.print("Ingrese el número de puertas del vehículo (0 si es un camión): ");
                    int numeroPuertas = scanner.nextInt();
                    if (numeroPuertas > 0) {
                        Vehiculo coche = new Coche(matricula, marca, modelo, velocidad, numeroPuertas);
                        sistema.agregarVehiculo(coche);
                    } else {
                        System.out.print("Ingrese la capacidad de carga del camión: ");
                        int capacidadCarga = scanner.nextInt();
                        Vehiculo camion = new Camion(matricula, marca, modelo, velocidad, capacidadCarga);
                        sistema.agregarVehiculo(camion);
                    }
                    break;
                case 2:
                    System.out.print("Ingrese la matrícula del vehículo a eliminar: ");
                    scanner.nextLine();
                    matricula = scanner.nextLine();
                    sistema.eliminarVehiculo(matricula);
                    break;
                case 3:
                    sistema.mostrarVehiculos();
                    break;
                case 4:
                    System.out.print("Ingrese la ubicación del semáforo: ");
                    scanner.nextLine();
                    String ubicacion = scanner.nextLine();
                    Semaforo semaforo = new Semaforo(ubicacion);
                    sistema.agregarSemaforo(semaforo);
                    break;
                case 5:
                    System.out.print("Ingrese la ubicación del semáforo a eliminar: ");
                    scanner.nextLine();
                    ubicacion = scanner.nextLine();
                    sistema.eliminarSemaforo(ubicacion);
                    break;
                case 6:
                    sistema.mostrarSemaforos();
                    break;
                case 7:
                    sistema.simularTrafico();
                    break;
                case 8:
                    try {
                        sistema.cerrarArchivo();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 9:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }

        try {
            sistema.cerrarArchivo();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}