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
        <td><%= empleado.nombre %></td>
        <td colspan="6">
            <a href="modificarEmpleado.jsp?campo=nombre&valor=<%= empleado.nombre %>">Modificar Nombre</a>
        </td>
    </tr>
    <tr>
        <th>DNI</th>
        <td><%= empleado.dni %></td>
        <td colspan="6">
            <a href="modificarEmpleado.jsp?campo=dni&valor=<%= empleado.dni %>">Modificar</a>
        </td>
    </tr>
    <tr>
        <th>Sexo</th>
        <td><%= empleado.sexo %></td>
        <td colspan="6">
            <a href="modificarEmpleado.jsp?campo=dni&valor=<%= empleado.sexo %>">Modificar</a>
        </td>
    </tr>
    <tr>
        <th>Años</th>
        <td><%= empleado.anyos %></td>
        <td colspan="6">
            <a href="modificarEmpleado.jsp?campo=anyos&valor=<%= empleado.anyos %>">Modificar</a>
        </td>
    </tr>
    <tr>

    </tr>
    <tr>
        <th>Categoría</th>
        <td><%= empleado.getCategoria() %></td>
        <td colspan="6">
            <a href="modificarEmpleado.jsp?campo=categoria&valor=<%= empleado.getCategoria() %>">Modificar</a>
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
<p><a href="index.jsp">Volver</a></p>
</body>
</html>
