<%@ page import="model.Empleado" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<% List<Empleado> empleados = (List<Empleado>) request.getAttribute("listaEmpleados"); %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Listar Productos</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>
<div class="container">
    <h1>Listar Productos</h1>
    <div class="styled-div">
        <div class="headerlistar">
            <div class="celllistar">Nombre</div>
            <div class="celllistar">DNI</div>
            <div class="celllistar">Sexo</div>
            <div class="celllistar">Categoría</div>
            <div class="celllistar">Años trabajados</div>
        </div>
        <% for (Empleado empleado : empleados) { %>
        <div class="rowlistar">
            <div class="celllistar"><%= empleado.nombre %></div>
            <div class="celllistar"><%= empleado.dni %></div>
            <div class="celllistar"><%= empleado.sexo %></div>
            <div class="celllistar"><%= empleado.getCategoria() %></div>
            <div class="celllistar"><%= empleado.anyos %></div>
        </div>
        <% } %>
    </div>
</div>
<a class="button" href="index.jsp">Volver al menú</a>

</body>
</html>
