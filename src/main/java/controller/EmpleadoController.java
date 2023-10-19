package controller;

import exception.DatosNoCorrectosException;
import dao.EmpleadoDAO;
import model.Empleado;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(description = "administra peticiones para la tabla empleados", urlPatterns = {"/empleados"})

public class EmpleadoController extends HttpServlet {

    public EmpleadoController() {
        super();
        // TODO Auto-generated constructor stub
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

        // TODO Auto-generated method stub

        String opcion = request.getParameter("opcion");

        if (opcion.equals("listar")) {

            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            List<Empleado> lista = new ArrayList<>();
            try {

                lista = empleadoDAO.findAll();
                for (Empleado empleado : lista) {
                    System.out.println(empleado);
                }

                request.setAttribute("listaEmpleados", lista);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
                requestDispatcher.forward(request, response);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (DatosNoCorrectosException e) {
                throw new RuntimeException(e);
            }
        }
    }

}