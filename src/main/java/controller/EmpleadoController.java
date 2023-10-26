package controller;

import dao.EmpleadoDAO;
import exception.DatosNoCorrectosException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Empleado;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(description = "administra peticiones para la tabla empleados", urlPatterns = {"/empleados"})
public class EmpleadoController extends HttpServlet {

    public EmpleadoController() {
        super();
    }

    public void init() throws ServletException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Manejar excepción en caso de que el controlador no se pueda cargar
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcion");

        if ("listar".equals(opcion)) {
            // Listar todos los empleados
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            try {
                List<Empleado> lista = empleadoDAO.findAll();
                request.setAttribute("listaEmpleados", lista);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
                requestDispatcher.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejar excepciones adecuadamente
            } catch (DatosNoCorrectosException e) {
                throw new RuntimeException(e);
            }
        } else if ("buscar".equals(opcion)) {
            // Búsqueda por DNI o nombre
            String dni = request.getParameter("dni");
            String nombre = request.getParameter("nombre");
            Empleado empleado = null; // Variable para almacenar el empleado encontrado

            if (dni != null && !dni.isEmpty()) {
                EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                try {
                    // Intentar buscar por DNI
                    empleado = empleadoDAO.findAByDni(dni);
                } catch (SQLException | DatosNoCorrectosException e) {
                    e.printStackTrace();
                    // Manejar excepciones adecuadamente
                }
            } else if (nombre != null && !nombre.isEmpty()) {
                EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                try {
                    // Intentar buscar por nombre
                    empleado = empleadoDAO.findAByName(nombre);
                } catch (SQLException | DatosNoCorrectosException e) {
                    e.printStackTrace();
                    // Manejar excepciones adecuadamente
                }
            }

            if (empleado != null) {
                // Si se encontró un empleado, lo establecemos como un atributo y reenviamos la solicitud al JSP.
                request.setAttribute("empleado", empleado);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/empleadoencontrado.jsp");
                requestDispatcher.forward(request, response);
            } else {
                // Manejar cuando no se encuentra un empleado
                response.sendRedirect("BusquedaEmpleado.jsp");
            }
        }
    }
}
