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
import java.util.ArrayList;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        String opcion = request.getParameter("opcion");

        if ("listar".equals(opcion)) {
            // Listar todos los empleados
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            List<Empleado> lista = new ArrayList<>();
            try {
                lista = empleadoDAO.findAll();
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

            if ((dni != null && !dni.isEmpty()) || (nombre != null && !nombre.isEmpty())) {
                EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                List<Empleado> empleados = new ArrayList<>();
                try {
                    // Intentar buscar por DNI
                    if (dni != null && !dni.isEmpty()) {
                        Empleado empleadoDNI = empleadoDAO.findAByDni(dni);
                        if (empleadoDNI != null) {
                            empleados.add(empleadoDNI);
                        }
                    }
                    // Intentar buscar por nombre
                    if (nombre != null && !nombre.isEmpty()) {
                        Empleado empleadoNombre = empleadoDAO.findAByName(nombre);
                        if (empleadoNombre != null) {
                            empleados.add(empleadoNombre);
                        }
                    }

                    request.setAttribute("empleados", empleados);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ResultadoBusqueda.jsp");
                    requestDispatcher.forward(request, response);
                } catch (SQLException | DatosNoCorrectosException e) {
                    e.printStackTrace();
                    // Manejar excepciones adecuadamente
                }
            } else {
                // Manejar cuando no se proporciona un valor en la búsqueda
                response.sendRedirect("BusquedaEmpleado.jsp");
            }
        }
    }
}
