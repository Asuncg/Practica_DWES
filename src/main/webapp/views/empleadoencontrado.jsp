<%@ page import="model.Empleado" %><%--
  Created by IntelliJ IDEA.
  User: Asun
  Date: 09/11/2023
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="standard">
    <h2>Empleado encontrado</h2>
</div>

<div class="styled-div">
    <div class="headerlistar">
        <div class="celllistar">Nombre</div>
        <div class="celllistar">DNI</div>
        <div class="celllistar">Sexo</div>
        <div class="celllistar">Categoría</div>
        <div class="celllistar">Años trabajados</div>
    </div>
    <%
        Empleado empleado = (Empleado) request.getAttribute("empleado");

        if (empleado != null) {
    %>
    <div class="rowlistar">
        <div class="celllistar"><%= empleado.nombre %>
        </div>
        <div class="celllistar"><%= empleado.dni %>
        </div>
        <div class="celllistar"><%= empleado.sexo %>
        </div>
        <div class="celllistar"><%= empleado.getCategoria() %>
        </div>
        <div class="celllistar"><%= empleado.anyos %>
        </div>
    </div>
    <% } %>
</div>
<form action="empresa" method="get">
    <input type="hidden" name="opcion" value="modificarempleadoform">
    <input type="hidden" name="dni" value="<%= empleado.dni %>">
    <input type="submit" value="Modificar Empleado">
</form>
<form action="empresa" method="post">
    <input type="hidden" name="opcion" value="bajaempleado">
    <input type="hidden" name="id" value="<%= empleado.id %>">
    <input type="hidden" name="dni" value="<%= empleado.dni %>">
    <input type="submit" value="Dar de baja Empleado">
</form>
</body>
</html>
