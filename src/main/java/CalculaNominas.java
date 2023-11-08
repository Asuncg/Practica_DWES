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


    private static double calculaSalario(Empleado empleado) {
        Nomina nomina = new Nomina();
        return nomina.sueldo(empleado.getCategoria(), empleado.anyos);
    }
}
