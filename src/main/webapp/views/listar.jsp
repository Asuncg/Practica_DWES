<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="standard">
    <h2>Listar Empleados</h2>
</div>

<div class="styled-div">
    <div class="headerlistar">
        <div class="celllistar">Nombre</div>
        <div class="celllistar">DNI</div>
        <div class="celllistar">Sexo</div>
        <div class="celllistar">Categoría</div>
        <div class="celllistar">Años trabajados</div>
    </div>

    <c:forEach var="empleado" items="${listaEmpleados}">
        <div class="rowlistar">
            <div class="celllistar">${empleado.getNombre()}</div>
            <div class="celllistar">${empleado.getDni()}</div>
            <div class="celllistar">${empleado.getSexo()}</div>
            <div class="celllistar">${empleado.categoria}</div>
            <div class="celllistar">${empleado.anyos}</div>
        </div>
    </c:forEach>
</div>


