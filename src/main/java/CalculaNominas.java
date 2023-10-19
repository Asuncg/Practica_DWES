import exception.DatosNoCorrectosException;
import model.Empleado;
import model.Nomina;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class CalculaNominas {

public void almacenaSueldo (String dni, double sueldo) throws IOException {

    Map<String, Double> listDniSalario = new HashMap<>();

            listDniSalario.put(dni, sueldo);

        ObjectOutputStream archivoSueldos = new ObjectOutputStream(new FileOutputStream("sueldos.dat"));
        archivoSueldos.writeObject(listDniSalario);
        archivoSueldos.close();

    }

    private static Empleado parseEmpleado(String input) throws DatosNoCorrectosException {
        // Separar la línea en partes (nombre, dni, sexo, categoría, años)
        String[] partes = input.split(",");
        if (partes.length < 5) {
            throw new DatosNoCorrectosException("La línea no tiene suficientes datos.");
        }

        String nombre = partes[0].trim();
        String dni = partes[1].trim();
        char sexo = partes[2].trim().charAt(0);
        int categoria = Integer.parseInt(partes[3].trim());
        int anyos = Integer.parseInt(partes[4].trim());

        // Crear y retornar una instancia de Empleado
        return new Empleado(nombre, dni, sexo, categoria, anyos);
    }

    private static void escribe(Empleado empleado) {
        double sueldo = calculaSalario(empleado);
        empleado.imprime();
        System.out.println("Sueldo: " + sueldo + "€");

    }

    private static double calculaSalario(Empleado empleado) {
        Nomina nomina = new Nomina();
        return nomina.sueldo(empleado.getCategoria(), empleado.anyos);
    }
}
