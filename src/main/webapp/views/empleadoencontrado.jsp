<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

    <c:if test="${not empty empleado}">
        <div class="rowlistar">
            <div class="celllistar">${empleado.nombre}</div>
            <div class="celllistar">${empleado.dni}</div>
            <div class="celllistar">${empleado.sexo}</div>
            <div class="celllistar">${empleado.categoria}</div>
            <div class="celllistar">${empleado.anyos}</div>
        </div>
    </c:if>
</div>

<c:if test="${not empty empleado}">
    <div class="buttons-container">
        <c:if test="${empleado.alta eq 1}">
            <form action="empresa" method="get">
                <input type="hidden" name="opcion" value="modificarempleadoform">
                <input type="hidden" name="dni" value="${empleado.dni}">
                <input type="submit" value="Modificar Empleado">
            </form>
            <form action="empresa" method="post" onsubmit="return confirmarBaja();">
                <input type="hidden" name="opcion" value="bajaempleado">
                <input type="hidden" name="id" value="${empleado.id}">
                <input type="hidden" name="dni" value="${empleado.dni}">
                <input type="submit" value="Dar de baja Empleado">
            </form>
        </c:if>

        <c:if test="${empleado.alta eq 0}">
            <span>Este empleado no está activo en este momento. ¿Desea reactivarlo?</span>
            <form action="empresa" method="post">
                <input type="hidden" name="opcion" value="reactivarEmpleado">
                <input type="hidden" name="id" value="${empleado.id}">
                <input type="hidden" name="dni" value="${empleado.dni}">
                <input type="hidden" name="categoria" value="${empleado.categoria}">
                <input type="hidden" name="anyos" value="${empleado.anyos}">
                <input type="submit" value="Reactivar Empleado">
            </form>
        </c:if>
    </div>
</c:if>

