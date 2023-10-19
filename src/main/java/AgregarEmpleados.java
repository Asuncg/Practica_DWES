import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AgregarEmpleados {

    public void agregarEmpleadoTxt(String nombre, String dni, char sexo, int cat, double anyos) throws IOException {

        FileWriter listaEmpleados = null;
        PrintWriter incluye = null;

        try {
            listaEmpleados = new FileWriter("listaEmpleados.txt", true); // Abre el archivo en modo de apertura para añadir
            incluye = new PrintWriter(listaEmpleados);

            incluye.println(nombre + "," + dni + "," + sexo + "," + cat + "," + anyos);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (listaEmpleados != null) {
                listaEmpleados.close();
            }
        }
    }
}
