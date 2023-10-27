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

/**
 * Clase que gestiona las peticiones relacionadas con la tabla de empleados.
 */
@WebServlet(description = "administra peticiones para la tabla empleados", urlPatterns = {"/empleados"})
public class EmpleadoController extends HttpServlet {

    public EmpleadoController() {
        super();
    }

    /**
     * Inicializa el servlet. Carga el controlador de la base de datos.
     *
     * @throws ServletException Si hay un error en la inicialización.
     */
    public void init() throws ServletException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Manejar excepción en caso de que el controlador no se pueda cargar
            e.printStackTrace();
        }
    }

    /**
     * Maneja las peticiones GET realizadas al servlet.
     *
     * @param request  La solicitud HTTP recibida.
     * @param response La respuesta HTTP que se enviará.
     * @throws ServletException Si hay un error en el servlet.
     * @throws IOException      Si hay un error de E/S.
     */
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

            } catch (DatosNoCorrectosException e) {
                throw new RuntimeException(e);
            }
            //Finalmente divido los formularios de búsqueda en dos, para bsucar o bien por DNI o por nombre
            //Ya que según yo entiendo, busco a un único empleado así que busco por campos supuestamente "Únicos"
            //Y digo supuestamente porque podría haber dos "Paco"
            //Código más que optimizable, pero me ciño al enunciado
        } else if ("buscar_dni".equals(opcion)) {
            // Búsqueda por DNI
            String dni = request.getParameter("dni");
            Empleado empleado = null;

            if (dni != null && !dni.isEmpty()) {
                EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                try {
                    empleado = empleadoDAO.findAByDni(dni);
                } catch (SQLException | DatosNoCorrectosException e) {
                    e.printStackTrace();
                }
            }

            if (empleado != null) {
                request.setAttribute("empleado", empleado);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/empleadoencontrado.jsp");
                requestDispatcher.forward(request, response);
            } else {
                response.sendRedirect("/Practica_DWES/views/empleadonoencontrado.jsp");
            }
        } else if ("buscar_nombre".equals(opcion)) {
            // Búsqueda por nombre
            String nombre = request.getParameter("nombre");
            Empleado empleado = null;

            if (nombre != null && !nombre.isEmpty()) {
                EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                try {
                    empleado = empleadoDAO.findAByName(nombre);
                } catch (SQLException | DatosNoCorrectosException e) {
                    e.printStackTrace();

                }
            }

            if (empleado != null) {
                request.setAttribute("empleado", empleado);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/empleadoencontrado.jsp");
                requestDispatcher.forward(request, response);
            } else {
                response.sendRedirect("/Practica_DWES/views/empleadonoencontrado.jsp");
            }
        }
    }
}
