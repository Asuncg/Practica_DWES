<%@ page import="model.Empleado" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resultado de Búsqueda de Empleado</title>
</head>
<body>
<h1>Resultado de Búsqueda de Empleado</h1>
<%
    Empleado empleado = (Empleado) request.getAttribute("empleado");

    if (empleado != null) {
%>
<table>
    <tr>
        <th>Nombre</th>
        <th>DNI</th>
        <th>Sexo</th>
        <th>Categoría</th>
        <th>Años</th>
        <th>Acciones</th>
    </tr>
    <tr>
        <td><%= empleado.nombre %></td>
        <td><%= empleado.dni %></td>
        <td><%= empleado.sexo %></td>
        <td><%= empleado.getCategoria() %></td>
        <td><%= empleado.anyos %></td>
        <td>
            <a href="modificarEmpleado.jsp?dni=<%= empleado.dni %>">Modificar DNI</a><br>
            <a href="modificarEmpleado.jsp?nombre=<%= empleado.nombre %>">Modificar Nombre</a><br>
            <a href="modificarEmpleado.jsp?anyos=<%= empleado.anyos %>">Modificar Años Trabajados</a><br>
            <a href="modificarEmpleado.jsp?categoria=<%= empleado.getCategoria() %>">Modificar Categoría</a><br>
            <!-- Agrega más botones para otros campos si es necesario -->
        </td>
    </tr>
</table>
<%
} else {
%>
<p>No se encontró ningún empleado con los criterios de búsqueda proporcionados.</p>
<%
    }
%>
</body>
</html>
