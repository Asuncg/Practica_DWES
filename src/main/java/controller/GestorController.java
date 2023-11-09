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
import model.Nomina;
import model.Sueldo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Clase que gestiona las peticiones relacionadas con la tabla de empleados.
 */
@WebServlet(description = "administra peticiones para la tabla empleados", urlPatterns = {"/empresa"})

public class GestorController extends HttpServlet {

    public GestorController() {
        super();
    }

    private String content = "";

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

        switch (opcion) {
            case "listar":
                listarEmpleados(request, response);
                break;

            case "buscar_dni":
                buscarPorDNI(request, response);
                break;

            case "buscar_nombre":
                buscarPorNombre(request, response);
                break;

            case "buscarEmpleado":
                buscarEmpleado(request, response);
                break;

            case "consultarSueldo":
                consultarSueldo(request, response);
                break;

            case "crearempleado":
                mostrarFormCrearEmpleado(request, response);
                break;

            case "modificarempleadoform":
                mostrarFormModificarEmpleado(request, response);
                break;

            case "sueldoByDni":
                obtenerSueldoPorDNI(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String opcion = request.getParameter("opcion");

        switch (opcion) {
            case "modificarEmpleado":
                modificarEmpleado(request, response);
                break;

            case "altaempleado":
                darDeAltaEmpleado(request, response);
                break;

            case "bajaempleado":
                darDeBajaEmpleado(request, response);
                break;
        }
    }

    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    }

    private void buscarPorDNI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    }

    private void buscarPorNombre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    }

    private void buscarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        content = "forms/buscarempleado.jsp";

        request.setAttribute("content", content);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void consultarSueldo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        content = "forms/sueldoform.jsp";

        request.setAttribute("content", content);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormCrearEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        content = "forms/crearempleadoform.jsp";

        request.setAttribute("content", content);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormModificarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            content = "forms/modificarempleado.jsp";

            request.setAttribute("content", content);
            request.setAttribute("empleado", empleado);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private void obtenerSueldoPorDNI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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


    private void modificarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String dni = request.getParameter("dni");
        char sexo = request.getParameter("sexo").charAt(0);
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        double anyos = Double.parseDouble(request.getParameter("anyos"));
        double sueldo;

        //Hacer comprobaciones si tengo tiempo

        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        //Actualizar Sueldo
        Nomina nomina = new Nomina();

        sueldo = nomina.sueldo(categoria, anyos);

        try {
            NominasDAO nominasDAO = new NominasDAO();
            nominasDAO.actualizarSueldo(dni, sueldo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        empleadoDAO.modificarEmpleado(id, nombre, dni, sexo, categoria, anyos);

        String content = "views/empleadoguardado.jsp";

        request.setAttribute("content", content);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);

    }

    private void darDeAltaEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String dni = request.getParameter("dni");
        char sexo = request.getParameter("sexo").charAt(0);
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        double anyos = Double.parseDouble(request.getParameter("anyos"));
        double sueldo;

        //Hacer comprobaciones si tengo tiempo

        //Actualizar Sueldo
        Nomina nomina = new Nomina();

        sueldo = nomina.sueldo(categoria, anyos);

        try {
            NominasDAO nominasDAO = new NominasDAO();
            nominasDAO.insertarSueldo(dni, sueldo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        try {
            empleadoDAO.altaEmpleado(nombre, dni, sexo, categoria, anyos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String content = "views/empleadoguardado.jsp";

        request.setAttribute("content", content);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);

    }

    private void darDeBajaEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String dni = request.getParameter("dni");

        //Hacer comprobaciones si tengo tiempo
        try {
            NominasDAO nominasDAO = new NominasDAO();
            nominasDAO.eliminarSueldo(dni);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        empleadoDAO.bajaEmpleado(id);

        String content = "views/empleadobaja.jsp";

        request.setAttribute("content", content);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }
}