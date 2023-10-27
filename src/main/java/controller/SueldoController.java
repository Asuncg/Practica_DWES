package controller;

import dao.NominasDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Sueldo;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Clase que gestiona las peticiones relacionadas con la tabla de sueldos.
 */
@WebServlet(description = "administra peticiones para la tabla sueldo", urlPatterns = {"/sueldo"})

public class SueldoController extends HttpServlet {

    public SueldoController() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
     * Inicializa el servlet. Carga el controlador de la base de datos.
     */
    public void init() {
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
        // Obtener el DNI del parámetro en la URL
        String dni = request.getParameter("dni");

        if (dni != null && !dni.isEmpty()) {
            // Realizar la consulta en la base de datos utilizando NominasDAO
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
            request.setAttribute("dni", dni);
            request.setAttribute("sueldo", sueldo);

            // Enviar la solicitud y respuesta al JSP de resultados
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/sueldoempleado.jsp");
            dispatcher.forward(request, response);
        } else {
            // Enviar respuesta de error
            response.setContentType("text/plain");
            response.getWriter().write("DNI no proporcionado.");
        }
    }
}




