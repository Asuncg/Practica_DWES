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
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcion");

        if ("listar".equals(opcion)) {
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            List<Empleado> lista = new ArrayList<>();
            try {
                lista = empleadoDAO.findAll();
                request.setAttribute("listaEmpleados", lista);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
                requestDispatcher.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (DatosNoCorrectosException e) {
                throw new RuntimeException(e);
            }
        } else if ("buscar".equals(opcion)) {
            String dni = request.getParameter("dni");
            String nombre = request.getParameter("nombre");
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();

            Empleado empleadoEncontrado = null;  // Variable para almacenar el empleado encontrado

            if (dni != null && !dni.isEmpty()) {
                try {
                    empleadoEncontrado = empleadoDAO.findAByDni(dni);
                } catch (SQLException | DatosNoCorrectosException e) {
                    throw new RuntimeException(e);
                }
            } else if (nombre != null && !nombre.isEmpty()) {
                try {
                    empleadoEncontrado = empleadoDAO.findAByName(nombre);
                } catch (SQLException | DatosNoCorrectosException e) {
                    throw new RuntimeException(e);
                }
            }

            if (empleadoEncontrado != null) {
                request.setAttribute("empleado", empleadoEncontrado);  // Almacena el empleado encontrado en el atributo "empleado"
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/empleado.jsp");
                requestDispatcher.forward(request, response);
            } else {
                // Manejar cuando no se encuentra un empleado
                response.sendRedirect("/views/empleado_no_encontrado.jsp");
            }
        }
    }
}

