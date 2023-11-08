package dao;

import exception.DatosNoCorrectosException;
import connector.ConexionDB;
import model.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que proporciona métodos para acceder a la base de datos y realizar operaciones relacionadas con los empleados.
 */
public class EmpleadoDAO {
    Connection conn = null;

    /**
     * Recupera una lista de todos los empleados almacenados en la base de datos.
     *
     * @return Una lista de objetos Empleado.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     * @throws DatosNoCorrectosException Si los datos recuperados de la base de datos no son correctos.
     */
    public List<Empleado> findAll() throws SQLException, DatosNoCorrectosException {

        List<Empleado> listaEmpleados = new ArrayList<>();

        try {
            conn = ConexionDB.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, nombre, dni, sexo, categoria, anyos FROM empleados");


            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");
                char sexo = rs.getString("sexo").charAt(0);
                int categoria = rs.getInt("categoria");
                double anyos = rs.getDouble("anyos");

                Empleado empleado = new Empleado(id, nombre, dni, sexo, categoria, anyos);
                listaEmpleados.add(empleado);
            }

            for (Empleado empleado : listaEmpleados) {
                empleado.imprime();

            }
        } catch (SQLException e) {

            System.out.println("Ocurrió algún error al conectar u operar con la BD");

        } catch (DatosNoCorrectosException e) {

            throw new RuntimeException(e);

        } finally {

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return listaEmpleados;
    }

    /**
     * Busca un empleado por su número de DNI y lo devuelve.
     *
     * @param dniEmpleado El número de DNI del empleado que se va a buscar.
     * @return El objeto Empleado encontrado o null si no se encuentra ningún empleado con ese DNI.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     * @throws DatosNoCorrectosException Si los datos recuperados de la base de datos no son correctos.
     */
    public Empleado findAByDni(String dniEmpleado) throws SQLException, DatosNoCorrectosException {
        Empleado empleado = null;

        try {
            conn = ConexionDB.getConnection();
            PreparedStatement pt = null;

            String query = "SELECT id, nombre, dni, sexo, categoria, anyos FROM empleados WHERE dni = ?";
            pt = conn.prepareStatement(query);

            pt.setString(1, dniEmpleado);

            ResultSet rs = pt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");
                char sexo = rs.getString("sexo").charAt(0);
                int categoria = rs.getInt("categoria");
                double anyos = rs.getDouble("anyos");

                empleado = new Empleado(id, nombre, dni, sexo, categoria, anyos);

            } else {

                System.out.println("No se encontró ningún empleado con el DNI proporcionado");

            }

        } catch (DatosNoCorrectosException e) {

            throw new RuntimeException(e);

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return empleado;
    }

    /**
     * Busca un empleado por su nombre y lo devuelve.
     *
     * @param nombreEmpleado El nombre del empleado que se va a buscar.
     * @return El objeto Empleado encontrado o null si no se encuentra ningún empleado con ese nombre.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     * @throws DatosNoCorrectosException Si los datos recuperados de la base de datos no son correctos.
     */
    public Empleado findAByName(String nombreEmpleado) throws SQLException, DatosNoCorrectosException {
        Empleado empleado = null;

        try {
            conn = ConexionDB.getConnection();
            PreparedStatement pt = null;

            String query = "SELECT id, nombre, dni, sexo, categoria, anyos FROM empleados WHERE nombre = ?";
            pt = conn.prepareStatement(query);

            pt.setString(1, nombreEmpleado);

            ResultSet rs = pt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");
                char sexo = rs.getString("sexo").charAt(0);
                int categoria = rs.getInt("categoria");
                double anyos = rs.getDouble("anyos");

                empleado = new Empleado(id, nombre, dni, sexo, categoria, anyos);

            } else {

                System.out.println("No se encontró ningún empleado con el nombre proporcionado");

            }

        } catch (DatosNoCorrectosException e) {

            throw new RuntimeException(e);

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return empleado;
    }


    /**
     * Agrega un nuevo empleado a la base de datos.
     *
     * @param nombre El nombre del empleado.
     * @param dni El número de DNI del empleado.
     * @param sexo El sexo del empleado.
     * @param categoria La categoría del empleado.
     * @param anyos Los años trabajados por el empleado.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public void altaEmpleado(String nombre, String dni, char sexo, int categoria, double anyos) throws SQLException {
        try {
            conn = ConexionDB.getConnection();
            PreparedStatement pt = null;

            String query = "INSERT INTO empleados (nombre, dni, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)";
            pt = conn.prepareStatement(query);
            pt.setString(1, nombre);
            pt.setString(2, dni);
            pt.setString(3, String.valueOf(sexo)); // Convertimos el char en String
            pt.setInt(4, categoria);
            pt.setDouble(5, anyos);

            int rowCount = pt.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Empleado agregado correctamente");
            } else {
                System.out.println("No se pudo agregar el empleado");
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió algún error al conectar u operar con la BD");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Cambia la categoría de un empleado en la base de datos.
     *
     * @param dniEmpleado El número de DNI del empleado cuya categoría se va a cambiar.
     * @param cat La nueva categoría a asignar al empleado.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public void cambiarCategoria(String dniEmpleado, int cat) throws SQLException {

        try {
            conn = ConexionDB.getConnection();
            PreparedStatement pt = null;

            String query = "UPDATE empleados SET categoria = ? WHERE dni = ?";
            pt = conn.prepareStatement(query);
            pt.setInt(1, cat);
            pt.setString(2, dniEmpleado);

            int rowCount = pt.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Categoría modificada");
            } else {
                System.out.println("No se encontró ningún empleado con el DNI proporcionado");
            }


        } finally {

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Incrementa los años trabajados por un empleado en la base de datos.
     *
     * @param dniEmpleado El número de DNI del empleado cuyos años trabajados se van a incrementar.
     * @param anyos Los años a incrementar en el registro del empleado.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public void incrementarAnyos(String dniEmpleado, double anyos) throws SQLException {

        try {
            conn = ConexionDB.getConnection();
            PreparedStatement pt = null;

            String query = "UPDATE empleados SET anyos = ? WHERE dni = ?";
            pt = conn.prepareStatement(query);
            pt.setDouble(1, anyos);
            pt.setString(2, dniEmpleado);

            int rowCount = pt.executeUpdate();

            if (rowCount > 0) {
                System.out.println("años trabajados modificados");
            } else {
                System.out.println("No se encontró ningún empleado con el DNI proporcionado");
            }


        } finally {

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Cambia el nombre de un empleado en la base de datos.
     *
     * @param dniEmpleado El número de DNI del empleado cuyo nombre se va a cambiar.
     * @param nombre El nuevo nombre a asignar al empleado.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public void cambiarNombre(String dniEmpleado, String nombre) throws SQLException {

        try {
            conn = ConexionDB.getConnection();
            PreparedStatement pt = null;

            String query = "UPDATE empleados SET nombre = ? WHERE dni = ?";
            pt = conn.prepareStatement(query);
            pt.setString(1, nombre);
            pt.setString(2, dniEmpleado);

            int rowCount = pt.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Nombre modificado");
            } else {
                System.out.println("No se encontró ningún empleado con el DNI proporcionado");
            }

        } finally {

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Cambia el sexo de un empleado en la base de datos.
     *
     * @param dniEmpleado El número de DNI del empleado cuyo sexo se va a cambiar.
     * @param sexo El nuevo sexo a asignar al empleado.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public void cambiarSexo(String dniEmpleado, String sexo) throws SQLException {

        try {
            conn = ConexionDB.getConnection();
            PreparedStatement pt = null;

            String query = "UPDATE empleados SET sexo = ? WHERE dni = ?";
            pt = conn.prepareStatement(query);
            pt.setString(1, sexo);
            pt.setString(2, dniEmpleado);

            int rowCount = pt.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Sexo modificado");
            } else {
                System.out.println("No se encontró ningún empleado con el DNI proporcionado");
            }

        } finally {

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Cambia el número de DNI de un empleado en la base de datos.
     *
     * @param dniEmpleado El número de DNI del empleado cuyo DNI se va a cambiar.
     * @param nuevoDni El nuevo número de DNI a asignar al empleado.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public void cambiarDni(String dniEmpleado, String nuevoDni) throws SQLException {

        try {
            conn = ConexionDB.getConnection();
            PreparedStatement pt = null;

            String query = "UPDATE empleados SET dni = ? WHERE dni = ?";
            pt = conn.prepareStatement(query);
            pt.setString(1, nuevoDni);
            pt.setString(2, dniEmpleado);

            int rowCount = pt.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Dni modificado");
            } else {
                System.out.println("No se encontró ningún empleado con el DNI proporcionado");
            }

        } finally {

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void modificarEmpleado(int id, String nombre, String dni, char sexo, int categoria, double anyos) {
        try {
            conn = ConexionDB.getConnection();
            PreparedStatement pt = null;

            String query = "UPDATE empleados SET nombre = ?, dni = ?, sexo = ?, categoria = ?, anyos = ? WHERE id = ?";
            pt = conn.prepareStatement(query);
            pt.setString(1, nombre);
            pt.setString(2, dni);
            pt.setString(3, String.valueOf(sexo)); // Convierto el char en String
            pt.setInt(4, categoria);
            pt.setDouble(5, anyos);
            pt.setInt(6, id);

            int rowCount = pt.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Empleado modificado correctamente");
            } else {
                System.out.println("No se encontró ningún empleado con el ID proporcionado");
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió algún error al conectar u operar con la BD");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
