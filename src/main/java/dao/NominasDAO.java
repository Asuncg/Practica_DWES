package dao;

import connector.ConexionDB;
import model.Sueldo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NominasDAO {

    Connection conn = null;

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
                System.out.println("Sueldo insertado");
            } else {
                System.out.println("No se pudo insertar el sueldo");
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

    public List<Sueldo> findAllSueldos() {

        List<Sueldo> listaSueldos = new ArrayList<>();

        try {
            conn = ConexionDB.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT dni, sueldo FROM nominas");


            while (rs.next()) {
                String dni = rs.getString("dni");
                double sueldo = rs.getDouble("sueldo");

                Sueldo sueldos = new Sueldo(dni, sueldo);
                listaSueldos.add(sueldos);
            }

            for (Sueldo sueldo : listaSueldos) {
                sueldo.imprime();

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
        return listaSueldos;
    }

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

}
