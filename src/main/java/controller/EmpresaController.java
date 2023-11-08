package controller;

import dao.EmpleadoDAO;
import dao.NominasDAO;
import exception.DatosNoCorrectosException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Empleado;
import model.Sueldo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Clase que gestiona las peticiones relacionadas con la tabla de empleados.
 */
@WebServlet(description = "administra peticiones para la tabla empleados", urlPatterns = {"/empresa"})

public class EmpresaController extends HttpServlet {

    public EmpresaController() {
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
        String content = "";

        if ("listar".equals(opcion)) {
            // Listar todos los empleados
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            try {
                List<Empleado> lista = empleadoDAO.findAll();

                content = "views/listar.jsp";

                request.setAttribute("content", content);
                request.setAttribute("listaEmpleados", lista);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (DatosNoCorrectosException e) {
                throw new RuntimeException(e);
            }

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

                content = "views/empleadoencontrado.jsp";

                request.setAttribute("content", content);
                request.setAttribute("empleado", empleado);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
            } else {
                content = "views/empleadonoencontrado.jsp";

                request.setAttribute("content", content);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
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
                content = "views/empleadoencontrado.jsp";

                request.setAttribute("content", content);
                request.setAttribute("empleado", empleado);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
            } else {
                content = "views/empleadonoencontrado.jsp";

                request.setAttribute("content", content);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
            }

        } else if ("buscarEmpleado".equals(opcion)) {

            content = "forms/buscarempleado.jsp";

            request.setAttribute("content", content);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);

        } else if ("consultarSueldo".equals(opcion)) {

            content = "forms/sueldoform.jsp";

            request.setAttribute("content", content);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);

        } else if ("crearempleado".equals(opcion)) {

            content = "forms/crearempleadoform.jsp";

            request.setAttribute("content", content);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);

        } else if ("sueldoByDni".equals(opcion)) {

            String dni = request.getParameter("dni");

            if (dni != null && !dni.isEmpty()) {
                NominasDAO nominasDAO = new NominasDAO();
                double sueldo = 0;
                try {
                    Sueldo sueldoObj = nominasDAO.findSueldoByDni(dni);
                    if (sueldoObj != null) {
                        sueldo = sueldoObj.getSueldo();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                // Establecer atributos en la solicitud para pasar al JSP de resultados
                content = "views/sueldoempleado.jsp";

                request.setAttribute("content", content);
                request.setAttribute("dni", dni);
                request.setAttribute("sueldo", sueldo);

                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            } else {
                // Enviar respuesta de error
                response.setContentType("text/plain");
                response.getWriter().write("DNI no proporcionado.");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String opcion = request.getParameter("opcion");

        if ("modificarEmpleado".equals(opcion)) {

            // Recupera los parámetros del formulario
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String dni = request.getParameter("dni");
            char sexo = request.getParameter("sexo").charAt(0);
            int categoria = Integer.parseInt(request.getParameter("categoria"));
            double anyos = Double.parseDouble(request.getParameter("anyos"));


            // Crea una instancia del DAO
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();

            // Llama al método del DAO para modificar el empleado
            empleadoDAO.modificarEmpleado(id, nombre, dni, sexo, categoria, anyos);
            String content = "views/empleadoguardado.jsp";

            request.setAttribute("content", content);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);


        } else if ("crearEmpleado".equals(opcion)) {

            // Recupera los parámetros del formulario
            String nombre = request.getParameter("nombre");
            String dni = request.getParameter("dni");
            char sexo = request.getParameter("sexo").charAt(0);
            int categoria = Integer.parseInt(request.getParameter("categoria"));
            double anyos = Double.parseDouble(request.getParameter("anyos"));


            // Crea una instancia del DAO
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();

            // Llama al método del DAO para modificar el empleado
            try {
                empleadoDAO.altaEmpleado( nombre, dni, sexo, categoria, anyos);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            String content = "views/empleadoguardado.jsp";

            request.setAttribute("content", content);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
