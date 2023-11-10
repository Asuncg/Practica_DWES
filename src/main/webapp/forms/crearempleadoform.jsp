<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="standard">
<h2>Crear Empleado</h2>
</div>
<c:choose>
    <c:when test="${not empty existError}">
        <p class="error">${existError}</p>
    </c:when>
</c:choose>
<form id="formCrearEmpleado" action="empresa" method="post">
    <input type="hidden" name="opcion" value="nuevaAltaEmpleado">
    <div class="styled-div">
        <div class="row">
            <div class="header-modificar">Nombre</div>
            <div class="cell">
                <input type="text" name="nombre" required/>
            </div>
        </div>
        <div class="row">
            <div class="header-modificar">DNI</div>
            <div class="cell">
                <input type="text" name="dni" minlength="2" maxlength="50" required/>
            </div>
        </div>
        <div class="row">
            <div class="header-modificar">Sexo</div>
            <div class="cell">
                <input type="text" name="sexo" minlength="1" maxlength="1" required pattern="[FM]"/>
            </div>
        </div>
        <div class="row">
            <div class="header-modificar">Años</div>
            <div class="cell">
                <input type="text" name="anyos" required/>
            </div>
        </div>
        <div class="row">
            <div class="header-modificar">Categoría</div>
            <div class="cell">
                <input type="text" name="categoria" minlength="1" maxlength="2" pattern="[0-9]+" required/>
            </div>
        </div>
    </div>
    <p></p>
    <input type="submit" value="Crear Empleado">
</form>


