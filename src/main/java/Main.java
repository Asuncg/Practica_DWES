import exception.DatosNoCorrectosException;
import model.Empleado;
import model.Sueldo;
import dao.EmpleadoDAO;
import dao.NominasDAO;
import model.Nomina;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, DatosNoCorrectosException, IOException {

        NominasDAO nominasDAO = new NominasDAO();
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        int option;

        do {
            System.out.println("");
            System.out.println("Menú para la gestión de empleados");
            System.out.println("1. Mostrar una lista de todos los empleados ");
            System.out.println("2. Mostras sueldo de un empleado: ");
            System.out.println("3. Modificación de empleado ");
            System.out.println("4. Recalcular y actualizar el sueldo de un empleado ");
            System.out.println("5. Recalcular y actualizar los sueldos de todos los empleados. ");
            System.out.println("6. Realizar una copia de seguridad de la base de datos en ficheros. ");
            System.out.println("0. Salir ");
            System.out.println("Elija una opción: ");

            option = sc.nextInt();
            String dniEmpleado;
            double sueldo;

            switch (option) {
                case (0):
                    break;
                case (1):
                    empleadoDAO.findAll();
                    break;
                case (2):
                    System.out.println("Indica el dni del empleado (11111111A): ");
                    dniEmpleado = sc.next();
                    Sueldo sueldoEmpleado = nominasDAO.findSueldoByDni(dniEmpleado);
                    sueldoEmpleado.imprime();
                    break;
                case (3):
                    int optionSubmenu;

                    do {
                        System.out.println("1. Modificar nombre: ");
                        System.out.println("2. Modificar dni: ");
                        System.out.println("3. Modificar sexo: ");
                        System.out.println("4. Modificar categoría: ");
                        System.out.println("5. Modificar años trabajados: ");
                        System.out.println("0. Volver al menú principal");
                        System.out.println("Indique una opción:");

                        optionSubmenu = sc.nextInt();

                        switch (optionSubmenu) {
                            case(0):
                                break;
                            case(1):
                                System.out.println("Indica el dni del empleado para modificar su nombre");
                                dniEmpleado = sc.next();
                                sc.nextLine();
                                System.out.println("Indica el nuevo nombre del empleado");
                                String nuevoNombre =  sc.nextLine();
                                empleadoDAO.cambiarNombre(dniEmpleado, nuevoNombre);
                                break;
                            case(2):
                                System.out.println("Indica el dni del empleado para modificar su dni");
                                dniEmpleado = sc.next();
                                sc.nextLine();
                                System.out.println("Indica el nuevo dni: (12345678A)");
                                String nuevoDni = sc.nextLine();
                                empleadoDAO.cambiarDni(dniEmpleado, nuevoDni);
                                break;
                            case(3):
                                System.out.println("Indica el dni del empleado para modificar su sexo");
                                dniEmpleado = sc.next();
                                sc.nextLine();
                                System.out.println("Indica el nuevo sexo del empleado F/M");
                                String nuevoSexo = sc.nextLine();
                                empleadoDAO.cambiarSexo(dniEmpleado, nuevoSexo);
                                break;
                            case(4):
                                System.out.println("Indica el dni del empleado para modificar su categoria");
                                dniEmpleado = sc.next();
                                sc.nextLine();
                                System.out.printf("Indica la nueva categoría del 1 al 10: ");
                                int categoria = sc.nextInt();
                                empleadoDAO.cambiarCategoria(dniEmpleado, categoria);
                                break;
                            case(5):
                                System.out.println("Indica el dni del empleado para modificar sus años trabajados");
                                dniEmpleado = sc.next();
                                sc.nextLine();
                                System.out.printf("Indica los años trabajados: ");
                                int anyos = sc.nextInt();
                                empleadoDAO.incrementarAnyos(dniEmpleado, anyos);
                                break;
                            default:
                                System.out.println("Opción no valida, por favor indique una opción correcta");

                        }
                    } while (optionSubmenu != 0);
                    break;
                case (4):
                    System.out.println("Indica el dni del empleado (11111111A): ");
                    dniEmpleado = sc.next();
                    Empleado empleado = empleadoDAO.findAByDni(dniEmpleado);
                    sueldo = calculaSueldo(empleado);
                    nominasDAO.actualizarSueldo(dniEmpleado, sueldo);
                    break;
                case (5):
                   List<Empleado> listaEmpleados= new ArrayList<>(empleadoDAO.findAll());
                   
                case (6):
                case (7):
                default:
                    System.out.printf("Opcion no válida, por favor ingrese una opción correcta");
            }
        } while (option != 0);


    }

    //Almacenar dni y sueldo total de un empleado en BD
    //Scanner sc = new Scanner(System.in);
    //System.out.println("Indica el dni del empleado (11111111A): ");
    //String dni = sc.next();
    //Empleado empleado = consultaEmpleadoDB.findAByDni(dni); // Localiza el empleado en la base de datos

    //double sueldo = calculaSueldo(empleado); // Calcula el sueldo

    //consultasNominasDB.insertarSueldo(dni, sueldo); // Almacena el sueldo

    //Agregar nuevo empleado de forma individual y agregar su sueldo en la tabla nóminas
    //Agregarlo como backup al archivo listaEmpleado.txt y sueldos.dat
    //altaEmpleado();

    //Agregar nuevos empleados desde fichero y agregar su sueldo en la tabla nóminas
    //altaEmpleado("listaEmpleados2.txt");


    public static double calculaSueldo(Empleado empleado) throws SQLException, DatosNoCorrectosException {

        Nomina nomina = new Nomina();

        return nomina.sueldo(empleado.getCategoria(), empleado.anyos);

    }

    public static void altaEmpleado() throws DatosNoCorrectosException, SQLException {
        Scanner sc = new Scanner(System.in);
        NominasDAO nominasDAO = new NominasDAO();
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        System.out.println("Indica el nombre y apellidos del empleado: ");
        String nombre = sc.next();
        System.out.println("Indica el dni del empleado (11111111A): ");
        String dni = sc.next();
        System.out.println("Indica el sexo del empleado F/M: ");
        char sexo = sc.next().charAt(0);
        System.out.println("Indica la categoría del empleado del 1 al 10: ");
        int cat = sc.nextInt();
        System.out.println("Indicca los años trabajados del usuario: ");
        double anyos = sc.nextDouble();

        Empleado empleado = new Empleado(nombre, dni, sexo, cat, anyos);

        empleadoDAO.altaEmpleado(nombre, dni, sexo, cat, anyos);

        double sueldo = calculaSueldo(empleado);

        nominasDAO.insertarSueldo(empleado.dni, sueldo);
    }

    public static void altaEmpleado(String nombreArchivo) throws SQLException, DatosNoCorrectosException, IOException {

        NominasDAO nominasDAO = new NominasDAO();
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        try {
            FileReader fileReader = new FileReader(nombreArchivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length == 5) {
                    String nombre = datos[0].trim();
                    String dni = datos[1].trim();
                    char sexo = datos[2].trim().charAt(0);
                    int cat = Integer.parseInt(datos[3].trim());
                    double anyos = Double.parseDouble(datos[4].trim());

                    AgregarEmpleados agregarTxt = new AgregarEmpleados();

                    agregarTxt.agregarEmpleadoTxt(nombre, dni, sexo, cat, anyos);

                    Empleado empleado = new Empleado(nombre, dni, sexo, cat, anyos);

                    empleadoDAO.altaEmpleado(nombre, dni, sexo, cat, anyos);

                    double sueldo = calculaSueldo(empleado);

                    CalculaNominas calculaNominas = new CalculaNominas();

                    calculaNominas.almacenaSueldo(dni, sueldo);

                    nominasDAO.insertarSueldo(dni, sueldo);

                } else {
                    System.out.println("Error en el formato de datos en el archivo: " + nombreArchivo);
                }
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + nombreArchivo);
        }
    }


}

