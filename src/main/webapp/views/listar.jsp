<%@ page import="model.Empleado" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<% List<Empleado> empleados = (List<Empleado>) request.getAttribute("listaEmpleados"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Listar Productos</title>
</head>
<body>
<table border="1">
    <tr>
        <td>
            <h1>Listar Productos</h1>
            <table border="1">
                <tr>
                    <td>Nombre</td>
                    <td>DNI</td>
                    <td>Sexo</td>
                    <td>Categoria</td>
                    <td>Años trabajados</td>
                </tr>
                <% for (Empleado empleado : empleados) { %>
                <tr>
                    <td><%= empleado.nombre %></td>
                    <td><%= empleado.dni %></td>
                    <td><%= empleado.sexo %></td>
                    <td><%= empleado.getCategoria() %></td>
                    <td><%= empleado.anyos %></td>
                </tr>
                <% } %>
            </table>
        </td>
    </tr>
</table>
<p><a href="index.jsp">Volver</a></p>
</body>
</html>