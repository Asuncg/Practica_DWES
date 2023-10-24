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
        <td><%= empleado.nombre %>
        </td>
        <td><a href="modificarEmpleado.jsp?nombre=<%= empleado.nombre %>">Modificar</a><br></td>
    </tr>
    <tr>
        <th>DNI</th>
        <td><%= empleado.dni %>
        </td>
        <td><a href="modificarEmpleado.jsp?dni=<%= empleado.dni %>">Modificar</a><br></td>
    </tr>
    <tr>
        <th>Sexo</th>
        <td><%= empleado.sexo %>
        </td>
        <td><a href="modificarEmpleado.jsp?nombre=<%= empleado.nombre %>">Modificar</a><br></td>
    </tr>
    <tr>
        <th>Categoría</th>
        <td><%= empleado.getCategoria() %>
        </td>
        <td><a href="modificarEmpleado.jsp?categoria=<%= empleado.getCategoria() %>">Modificar</a><br></td>
    </tr>
    <tr>
        <th>Años</th>
        <td><%= empleado.anyos %>
        </td>
        <td><a href="modificarEmpleado.jsp?anyos=<%= empleado.anyos %>">Modificar</a><br></td>
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
