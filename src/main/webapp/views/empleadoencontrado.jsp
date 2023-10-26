<%@ page import="model.Empleado" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resultado de Búsqueda de Empleado</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>
<div class="container">
    <h1>Resultado de Búsqueda de Empleado</h1>
    <%
        Empleado empleado = (Empleado) request.getAttribute("empleado");

        if (empleado != null) {
    %>
    <div class="styled-div">
        <div class="row">
            <div class="header">Nombre</div>
            <div class="cell"><%= empleado.nombre %></div>
            <div class="cell">
                <a href="modificarEmpleado.jsp?campo=nombre&valor=<%= empleado.nombre %>">Modificar</a>
            </div>
        </div>
        <div class="row">
            <div class="header">DNI</div>
            <div class="cell"><%= empleado.dni %></div>
            <div class="cell">
                <a href="modificarEmpleado.jsp?campo=dni&valor=<%= empleado.dni %>">Modificar</a>
            </div>
        </div>
        <div class="row">
            <div class="header">Sexo</div>
            <div class="cell"><%= empleado.sexo %></div>
            <div class="cell">
                <a href="modificarEmpleado.jsp?campo=dni&valor=<%= empleado.sexo %>">Modificar</a>
            </div>
        </div>
        <div class="row">
            <div class="header">Años</div>
            <div class="cell"><%= empleado.anyos %></div>
            <div class="cell">
                <a href="modificarEmpleado.jsp?campo=anyos&valor=<%= empleado.anyos %>">Modificar</a>
            </div>
        </div>
        <div class="row">
            <div class="header">Categoría</div>
            <div class="cell"><%= empleado.getCategoria() %></div>
            <div class="cell">
                <a href="modificarEmpleado.jsp?campo=categoria&valor=<%= empleado.getCategoria() %>">Modificar</a>
            </div>
        </div>
    </div>

    <%
    } else {
    %>
    <p>No se encontró ningún empleado con los criterios de búsqueda proporcionados.</p>
    <%
        }
    %>
<p>
    <a class="button" href="index.jsp">Volver</a>

</p>
</div>
</body>
</html>
