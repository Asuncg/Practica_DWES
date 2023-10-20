package dao;

import exception.DatosNoCorrectosException;
import connector.ConexionDB;
import model.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    Connection conn = null;

    public List<Empleado> findAll() throws SQLException, DatosNoCorrectosException {

        List<Empleado> listaEmpleados = new ArrayList<>();

        try {
            conn = ConexionDB.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombre, dni, sexo, categoria, anyos FROM empleados");


            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");
                char sexo = rs.getString("sexo").charAt(0);
                int categoria = rs.getInt("categoria");
                double anyos = rs.getDouble("anyos");

                Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
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

    public Empleado findAByDni(String dniEmpleado) throws SQLException, DatosNoCorrectosException {
        Empleado empleado = null;

        try {
            conn = ConexionDB.getConnection();
            PreparedStatement pt = null;

            String query = "SELECT nombre, dni, sexo, categoria, anyos FROM empleados WHERE dni = ?";
            pt = conn.prepareStatement(query);

            pt.setString(1, dniEmpleado);

            ResultSet rs = pt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");
                char sexo = rs.getString("sexo").charAt(0);
                int categoria = rs.getInt("categoria");
                double anyos = rs.getDouble("anyos");

                empleado = new Empleado(nombre, dni, sexo, categoria, anyos);

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

    public Empleado findAByName(String nombreEmpleado) throws SQLException, DatosNoCorrectosException {
        Empleado empleado = null;

        try {
            conn = ConexionDB.getConnection();
            PreparedStatement pt = null;

            String query = "SELECT nombre, dni, sexo, categoria, anyos FROM empleados WHERE dni = ?";
            pt = conn.prepareStatement(query);

            pt.setString(1, nombreEmpleado);

            ResultSet rs = pt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");
                char sexo = rs.getString("sexo").charAt(0);
                int categoria = rs.getInt("categoria");
                double anyos = rs.getDouble("anyos");

                empleado = new Empleado(nombre, dni, sexo, categoria, anyos);

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

}
