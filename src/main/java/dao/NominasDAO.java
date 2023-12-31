package dao;

import connector.ConexionDB;
import model.Sueldo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que proporciona métodos para acceder a la base de datos y realizar operaciones relacionadas con las nóminas de los empleados.
 */
public class NominasDAO {

    Connection conn = null;
    /**
     * Busca un registro de sueldo por el número de DNI del empleado y lo devuelve.
     *
     * @param dniEmpleado El número de DNI del empleado cuyo sueldo se va a buscar.
     * @return El objeto Sueldo encontrado o null si no se encuentra ningún registro de sueldo con ese DNI.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public Sueldo findSueldoByDni(String dniEmpleado) throws SQLException {
        Sueldo sueldoEmpleado = null;

        try {
            conn = ConexionDB.getConnection();
            PreparedStatement pt = null;

            String query = "SELECT  dni, sueldo FROM nominas WHERE dni = ?";
            pt = conn.prepareStatement(query);

            pt.setString(1, dniEmpleado);

            ResultSet rs = pt.executeQuery();

            if (rs.next()) {
                String dni = rs.getString("dni");
                double sueldo = rs.getDouble("sueldo");

                sueldoEmpleado = new Sueldo(dni, sueldo);

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
        return sueldoEmpleado;
    }

    /**
     * Actualiza el sueldo de un empleado en la base de datos.
     *
     * @param dniEmpleado El número de DNI del empleado cuyo sueldo se va a actualizar.
     * @param sueldo El nuevo sueldo a actualizar.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public void actualizarSueldo(String dniEmpleado, double sueldo) throws SQLException {

        try {
            conn = ConexionDB.getConnection();
            PreparedStatement pt = null;

            String query = "UPDATE nominas SET sueldo = ? WHERE dni = ?";
            pt = conn.prepareStatement(query);
            pt.setDouble(1, sueldo);
            pt.setString(2, dniEmpleado);

            int rowCount = pt.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Sueldo actualizado");
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
     * Inserta un nuevo registro de sueldo en la base de datos.
     *
     * @param dniEmpleado El número de DNI del empleado cuyo sueldo se va a insertar.
     * @param sueldo El sueldo a insertar.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public void insertarSueldo(String dniEmpleado, double sueldo) throws SQLException {

        try {
            conn = ConexionDB.getConnection();
            PreparedStatement pt = null;

            String query = "INSERT INTO nominas (dni, sueldo) VALUES (?, ?)";
            pt = conn.prepareStatement(query);
            pt.setString(1, dniEmpleado);
            pt.setDouble(2, sueldo);


            int rowCount = pt.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Sueldo actualizado");
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
     * Elimina un registro de sueldo en la base de datos.
     *
     * @param dni El número de DNI del empleado cuyo sueldo se va a eliminar.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public void eliminarSueldo(String dni) throws SQLException {

        try {
            conn = ConexionDB.getConnection();
            PreparedStatement pt = null;

            String query = "DELETE FROM nominas WHERE dni = ?";
            pt = conn.prepareStatement(query);
            pt.setString(1, dni);



            int rowCount = pt.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Sueldo actualizado");
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
